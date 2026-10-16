/*
 * Copyright (c) 2026 Tessera.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE files in the repository root for full details.
 */

package ru.tesseramessenger.android.enterprise

import com.google.common.truth.Truth.assertThat
import io.element.android.features.location.api.BuildConfig
import io.element.android.features.location.impl.DefaultLocationService
import org.junit.Test

/**
 * Location sharing is removed (F4): the map provider has no key, so the whole feature is off in the UI.
 * The module is kept to keep merges of upstream releases cheap.
 */
class TesseraLocationRemovedTest {
    @Test
    fun `map provider has no key, even if one is set in the environment`() {
        assertThat(BuildConfig.MAPTILER_API_KEY).isEmpty()
    }

    @Test
    fun `location service is not available`() {
        assertThat(DefaultLocationService().isServiceAvailable()).isFalse()
    }
}
