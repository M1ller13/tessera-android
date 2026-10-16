/*
 * Copyright (c) 2026 Tessera.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE files in the repository root for full details.
 */

package ru.tesseramessenger.android.enterprise

import com.google.common.truth.Truth.assertThat
import io.element.android.libraries.matrix.api.auth.OAuthConfig
import org.junit.Test

/** The only OAuth client is the static `tessera-android` of our MAS (MAS-PLAN M3); nothing is registered dynamically. */
class TesseraOAuthConfigTest {
    private val prodIssuer = "https://account.tessera-messenger.ru/"
    private val standIssuer = "http://127.0.0.1:8190/"
    private val clientId = "01M4KJVV2TACDDS2M6Q3NGSXQ3"

    @Test
    fun `release knows only the Tessera MAS`() {
        assertThat(OAuthConfig.staticRegistrations(isDebug = false)).containsExactly(prodIssuer, clientId)
    }

    @Test
    fun `debug also knows the stand MAS, with the same client`() {
        assertThat(OAuthConfig.staticRegistrations(isDebug = true)).containsExactly(prodIssuer, clientId, standIssuer, clientId)
    }

    @Test
    fun `no Element client is registered`() {
        assertThat(OAuthConfig.STATIC_REGISTRATIONS.keys.joinToString()).doesNotContain("thirdroom")
        assertThat(OAuthConfig.STATIC_REGISTRATIONS.values).doesNotContain("elementx")
    }
}
