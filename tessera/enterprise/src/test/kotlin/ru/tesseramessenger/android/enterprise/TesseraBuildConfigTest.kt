/*
 * Copyright (c) 2026 Tessera.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE files in the repository root for full details.
 */

package ru.tesseramessenger.android.enterprise

import com.google.common.truth.Truth.assertThat
import io.element.android.appconfig.AnalyticsConfig
import io.element.android.appconfig.ApplicationConfig
import io.element.android.appconfig.RageshakeConfig
import org.junit.Test

/**
 * Guards the values that `plugins/.../BuildTimeConfig.kt` feeds into the build.
 * A merge of a new upstream release must not bring Element links or names back.
 */
class TesseraBuildConfigTest {
    @Test
    fun `application name used in strings is Tessera`() {
        assertThat(ApplicationConfig.PRODUCTION_APPLICATION_NAME).isEqualTo("Tessera")
        assertThat(ApplicationConfig.DESKTOP_APPLICATION_NAME).isEqualTo("Tessera")
    }

    @Test
    fun `links point to the Tessera site`() {
        listOf(BuildConfig.URL_WEBSITE, BuildConfig.URL_PRIVACY, BuildConfig.URL_SUPPORT, AnalyticsConfig.POLICY_LINK).forEach { url ->
            assertThat(url).startsWith("https://tessera-messenger.ru")
        }
    }

    @Test
    fun `no link points to Element`() {
        listOf(BuildConfig.URL_WEBSITE, BuildConfig.URL_PRIVACY, BuildConfig.URL_SUPPORT, BuildConfig.PUSH_GATEWAY_URL, AnalyticsConfig.POLICY_LINK)
            .forEach { url ->
                assertThat(url.lowercase()).doesNotContain("element")
                assertThat(url.lowercase()).doesNotContain("matrix.org")
            }
    }

    @Test
    fun `bug reports have no default server`() {
        assertThat(RageshakeConfig.BUG_REPORT_URL).isEmpty()
    }
}
