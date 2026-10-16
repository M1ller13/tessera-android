/*
 * Copyright (c) 2026 Tessera.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE files in the repository root for full details.
 */

package ru.tesseramessenger.android.features

import com.google.common.truth.Truth.assertThat
import io.element.android.libraries.core.meta.BuildType
import io.element.android.libraries.matrix.test.core.aBuildMeta
import org.junit.Test

class TesseraFeaturesTest {
    @Test
    fun `all removed features are disabled in every build type`() {
        BuildType.entries.forEach { buildType ->
            val buildMeta = aBuildMeta(buildType = buildType)
            TesseraFeatures.entries.forEach { feature ->
                assertThat(feature.defaultValue(buildMeta)).isFalse()
            }
        }
    }

    @Test
    fun `all removed features are finished, so no user can enable them`() {
        TesseraFeatures.entries.forEach { feature ->
            assertThat(feature.isFinished).isTrue()
            assertThat(feature.isInLabs).isFalse()
        }
    }

    @Test
    fun `keys are unique and prefixed`() {
        val keys = TesseraFeatures.entries.map { it.key }
        assertThat(keys).containsNoDuplicates()
        keys.forEach { assertThat(it).startsWith("tessera.") }
    }
}
