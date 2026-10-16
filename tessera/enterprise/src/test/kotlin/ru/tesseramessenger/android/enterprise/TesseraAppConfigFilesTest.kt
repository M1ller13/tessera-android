/*
 * Copyright (c) 2026 Tessera.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE files in the repository root for full details.
 */

package ru.tesseramessenger.android.enterprise

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.io.File

/**
 * Guards the app files that decide where the app can be opened from and which servers it talks to (F2).
 * The working directory of a unit test is the module directory (`tessera/enterprise`).
 */
class TesseraAppConfigFilesTest {
    private val manifest = File("../../app/src/main/AndroidManifest.xml").readText()
    private val mainNetworkConfig = File("../../app/src/main/res/xml/network_security_config.xml").readText()
    private val debugNetworkConfig = File("../../app/src/debug/res/xml/network_security_config.xml").readText()

    /** The manifest without comments: the comments may name the removed hosts. */
    private val manifestCode = manifest.replace(Regex("<!--.*?-->", RegexOption.DOT_MATCHES_ALL), "")

    @Test
    fun `App Links point only to the Tessera site`() {
        val hosts = Regex("""android:host="([^"]+)"""").findAll(manifestCode).map { it.groupValues[1] }.toList()
        // `open` and `room` / `user` are the hosts of the custom schemes, the rest are web hosts
        assertThat(hosts).contains("tessera-messenger.ru")
        assertThat(hosts.filter { "." in it }).containsExactly("tessera-messenger.ru")
    }

    @Test
    fun `no Element or Matrix links are handled`() {
        assertThat(manifestCode).doesNotContain("element.io")
        assertThat(manifestCode).doesNotContain("android:scheme=\"matrix\"")
        assertThat(manifestCode).doesNotContain("android:scheme=\"elementx\"")
    }

    @Test
    fun `release talks only HTTPS and trusts only the system certificates`() {
        assertThat(mainNetworkConfig).contains("cleartextTrafficPermitted=\"false\"")
        assertThat(mainNetworkConfig).doesNotContain("cleartextTrafficPermitted=\"true\"")
        assertThat(mainNetworkConfig).doesNotContain("<domain ")
        assertThat(mainNetworkConfig).doesNotContain("src=\"user\"")
    }

    @Test
    fun `debug allows clear text only on the loopback`() {
        val domains = Regex("""<domain [^>]*>([^<]+)</domain>""").findAll(debugNetworkConfig).map { it.groupValues[1] }.toList()
        assertThat(domains).containsExactly("127.0.0.1", "localhost")
        assertThat(debugNetworkConfig).contains("<base-config cleartextTrafficPermitted=\"false\">")
    }

    @Test
    fun `the OAuth redirect scheme of the static client is the same in every build type`() {
        val gradle = File("../../app/build.gradle.kts").readText()
        assertThat(gradle).doesNotContain("\$oAuthRedirectSchemeBase.")
    }
}
