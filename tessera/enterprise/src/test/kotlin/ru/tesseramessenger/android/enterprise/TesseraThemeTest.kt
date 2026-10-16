/*
 * Copyright (c) 2026 Tessera.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE files in the repository root for full details.
 */

@file:OptIn(ExperimentalTestApi::class)

package ru.tesseramessenger.android.enterprise

import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.v2.runAndroidComposeUiTest
import com.google.common.truth.Truth.assertThat
import io.element.android.compound.colors.SemanticColorsLightDark
import io.element.android.compound.theme.ElementTheme
import io.element.android.compound.theme.LocalDefaultSemanticColors
import io.element.android.compound.theme.Theme
import io.element.android.compound.tokens.generated.SemanticColors
import io.element.android.tests.testutils.robolectric.RobolectricTest
import org.junit.Test

class TesseraThemeTest : RobolectricTest() {
    @Test
    fun `ElementTheme without colors uses the Compound palette by default`() = runAndroidComposeUiTest<ComponentActivity> {
        var colors: SemanticColors? = null
        setContent {
            ElementTheme(theme = Theme.Light, applySystemBarsUpdate = false) {
                colors = ElementTheme.colors
            }
        }
        assertThat(colors).isEqualTo(SemanticColorsLightDark.default.light)
    }

    @Test
    fun `ElementTheme without colors uses the palette from LocalDefaultSemanticColors`() = runAndroidComposeUiTest<ComponentActivity> {
        val seen = mutableMapOf<Theme, SemanticColors>()
        setContent {
            CompositionLocalProvider(LocalDefaultSemanticColors provides TesseraColors.lightDark) {
                ElementTheme(theme = Theme.Light, applySystemBarsUpdate = false) {
                    seen[Theme.Light] = ElementTheme.colors
                }
                ElementTheme(theme = Theme.Dark, applySystemBarsUpdate = false) {
                    seen[Theme.Dark] = ElementTheme.colors
                }
            }
        }
        assertThat(seen[Theme.Light]).isEqualTo(TesseraColors.light)
        assertThat(seen[Theme.Dark]).isEqualTo(TesseraColors.dark)
    }

    @Test
    fun `colors passed to ElementTheme win over LocalDefaultSemanticColors`() = runAndroidComposeUiTest<ComponentActivity> {
        var colors: SemanticColors? = null
        setContent {
            CompositionLocalProvider(LocalDefaultSemanticColors provides TesseraColors.lightDark) {
                ElementTheme(
                    theme = Theme.Light,
                    applySystemBarsUpdate = false,
                    compoundLight = SemanticColorsLightDark.default.light,
                ) {
                    colors = ElementTheme.colors
                }
            }
        }
        assertThat(colors).isEqualTo(SemanticColorsLightDark.default.light)
    }
}
