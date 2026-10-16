/*
 * Copyright (c) 2026 Tessera.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE files in the repository root for full details.
 */

package ru.tesseramessenger.android.enterprise

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import io.element.android.features.enterprise.api.BugReportUrl
import io.element.android.libraries.matrix.test.A_SESSION_ID
import kotlinx.coroutines.test.runTest
import org.junit.Test

class TesseraEnterpriseServiceTest {
    private val service = TesseraEnterpriseService()

    @Test
    fun `semanticColorsFlow emits the Tessera palette`() = runTest {
        service.semanticColorsFlow(null).test {
            assertThat(awaitItem()).isEqualTo(TesseraColors.lightDark)
            awaitComplete()
        }
    }

    @Test
    fun `semanticColorsFlow emits the same palette for a session`() = runTest {
        service.semanticColorsFlow(A_SESSION_ID).test {
            assertThat(awaitItem()).isEqualTo(TesseraColors.lightDark)
            awaitComplete()
        }
    }

    @Test
    fun `brandColorsFlow emits the brand color`() = runTest {
        service.brandColorsFlow(null).test {
            assertThat(awaitItem()).isEqualTo(TesseraColors.brand)
            awaitComplete()
        }
    }

    @Test
    fun `bug reports are disabled`() = runTest {
        service.bugReportUrlFlow(A_SESSION_ID).test {
            assertThat(awaitItem()).isEqualTo(BugReportUrl.Disabled)
            awaitComplete()
        }
    }

    @Test
    fun `firebase push gateway comes from the build config and is not Element's`() {
        val gateway = service.firebasePushGateway()
        assertThat(gateway).isEqualTo(BuildConfig.PUSH_GATEWAY_URL)
        assertThat(gateway).startsWith("https://")
        assertThat(gateway).doesNotContain("matrix.org")
        assertThat(gateway).doesNotContain("element")
    }

    @Test
    fun `unified push has no gateway`() {
        assertThat(service.unifiedPushDefaultPushGateway()).isNull()
    }

    @Test
    fun `Element Pro is never enforced and no request is made`() = runTest {
        assertThat(service.isElementProEnforced("any.example")).isFalse()
    }

    @Test
    fun `brand can not be overridden`() = runTest {
        service.overrideBrandColor(A_SESSION_ID, "#FF0000")
        service.brandColorsFlow(A_SESSION_ID).test {
            assertThat(awaitItem()).isEqualTo(TesseraColors.brand)
            awaitComplete()
        }
    }
}
