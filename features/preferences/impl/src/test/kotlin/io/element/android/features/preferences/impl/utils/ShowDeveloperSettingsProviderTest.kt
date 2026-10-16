/*
 * Copyright (c) 2026 Element Creations Ltd.
 * Copyright 2026 Tessera.
 *
 * SPDX-License-Identifier: AGPL-3.0-only OR LicenseRef-Element-Commercial.
 * Please see LICENSE files in the repository root for full details.
 */

package io.element.android.features.preferences.impl.utils

import com.google.common.truth.Truth.assertThat
import io.element.android.libraries.core.meta.BuildType
import io.element.android.libraries.matrix.test.core.aBuildMeta
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ShowDeveloperSettingsProviderTest {
    @Test
    fun `developer settings are shown in debug builds`() {
        val provider = ShowDeveloperSettingsProvider(aBuildMeta(BuildType.DEBUG))
        assertThat(provider.showDeveloperSettings.value).isTrue()
    }

    @Test
    fun `developer settings are hidden in release builds`() {
        val provider = ShowDeveloperSettingsProvider(aBuildMeta(BuildType.RELEASE))
        assertThat(provider.showDeveloperSettings.value).isFalse()
    }

    @Test
    fun `developer settings can not be unlocked by taps in release builds`() = runTest {
        val provider = ShowDeveloperSettingsProvider(aBuildMeta(BuildType.RELEASE))
        repeat(ShowDeveloperSettingsProvider.DEVELOPER_SETTINGS_COUNTER * 3) {
            provider.unlockDeveloperSettings(backgroundScope)
        }
        assertThat(provider.showDeveloperSettings.value).isFalse()
    }
}
