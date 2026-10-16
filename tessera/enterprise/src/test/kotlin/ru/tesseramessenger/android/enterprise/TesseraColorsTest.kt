/*
 * Copyright (c) 2026 Tessera.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE files in the repository root for full details.
 */

package ru.tesseramessenger.android.enterprise

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import com.google.common.truth.Truth.assertThat
import io.element.android.compound.tokens.generated.compoundColorsDark
import io.element.android.compound.tokens.generated.compoundColorsLight
import org.junit.Test

class TesseraColorsTest {
    @Test
    fun `light and dark palettes keep their flags`() {
        assertThat(TesseraColors.light.isLight).isTrue()
        assertThat(TesseraColors.dark.isLight).isFalse()
    }

    @Test
    fun `canvas colors are the ones of the prototype`() {
        assertThat(TesseraColors.light.bgCanvasDefault).isEqualTo(Color(0xFFF0EEE9))
        assertThat(TesseraColors.dark.bgCanvasDefault).isEqualTo(Color(0xFF0D1B1D))
    }

    @Test
    fun `primary button is the brand color in both themes`() {
        assertThat(TesseraColors.light.bgActionPrimaryRest).isEqualTo(Color(0xFF1B5E63))
        assertThat(TesseraColors.dark.bgActionPrimaryRest).isEqualTo(Color(0xFF1B5E63))
    }

    @Test
    fun `palette differs from the Compound defaults`() {
        assertThat(TesseraColors.light).isNotEqualTo(compoundColorsLight)
        assertThat(TesseraColors.dark).isNotEqualTo(compoundColorsDark)
    }

    @Test
    fun `text on the primary button has contrast of at least 4_5`() {
        assertThat(contrast(TesseraColors.light.textOnSolidPrimary, TesseraColors.light.bgActionPrimaryRest)).isAtLeast(4.5)
        assertThat(contrast(TesseraColors.dark.textOnSolidPrimary, TesseraColors.dark.bgActionPrimaryRest)).isAtLeast(4.5)
    }

    @Test
    fun `primary text has contrast of at least 4_5 on the canvas`() {
        assertThat(contrast(TesseraColors.light.textPrimary, TesseraColors.light.bgCanvasDefault)).isAtLeast(4.5)
        assertThat(contrast(TesseraColors.dark.textPrimary, TesseraColors.dark.bgCanvasDefault)).isAtLeast(4.5)
    }

    @Test
    fun `secondary text and accent text are readable on the canvas`() {
        assertThat(contrast(TesseraColors.light.textSecondary, TesseraColors.light.bgCanvasDefault)).isAtLeast(4.5)
        assertThat(contrast(TesseraColors.dark.textSecondary, TesseraColors.dark.bgCanvasDefault)).isAtLeast(4.5)
        assertThat(contrast(TesseraColors.light.textActionAccent, TesseraColors.light.bgCanvasDefault)).isAtLeast(4.5)
        assertThat(contrast(TesseraColors.dark.textActionAccent, TesseraColors.dark.bgCanvasDefault)).isAtLeast(4.5)
    }

    @Test
    fun `critical text is readable on the canvas`() {
        assertThat(contrast(TesseraColors.light.textCriticalPrimary, TesseraColors.light.bgCanvasDefault)).isAtLeast(4.5)
        assertThat(contrast(TesseraColors.dark.textCriticalPrimary, TesseraColors.dark.bgCanvasDefault)).isAtLeast(4.5)
    }

    @Test
    fun `avatar initials are readable on every avatar color`() {
        listOf(TesseraColors.light, TesseraColors.dark).forEach { colors ->
            listOf(
                colors.bgDecorative1 to colors.textDecorative1,
                colors.bgDecorative2 to colors.textDecorative2,
                colors.bgDecorative3 to colors.textDecorative3,
                colors.bgDecorative4 to colors.textDecorative4,
                colors.bgDecorative5 to colors.textDecorative5,
                colors.bgDecorative6 to colors.textDecorative6,
            ).forEach { (background, text) ->
                // The initial is big and bold: the large-text threshold of WCAG (3) applies, 4,2 is what the prototype has at worst.
                assertThat(contrast(text, background)).isAtLeast(4.2)
            }
        }
    }

    private fun contrast(a: Color, b: Color): Double {
        val l1 = a.luminance().toDouble()
        val l2 = b.luminance().toDouble()
        return (maxOf(l1, l2) + 0.05) / (minOf(l1, l2) + 0.05)
    }
}
