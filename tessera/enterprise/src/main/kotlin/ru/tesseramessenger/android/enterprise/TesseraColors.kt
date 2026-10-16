/*
 * Copyright (c) 2026 Tessera.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE files in the repository root for full details.
 */

package ru.tesseramessenger.android.enterprise

import androidx.compose.ui.graphics.Color
import io.element.android.compound.colors.SemanticColorsLightDark
import io.element.android.compound.tokens.generated.SemanticColors
import io.element.android.compound.tokens.generated.compoundColorsDark
import io.element.android.compound.tokens.generated.compoundColorsLight

/**
 * Tessera palette, taken from `prototype/src/styles/theme.css` of the main repository.
 *
 * The generated Compound files are not modified: the palette is built from the Compound defaults
 * with [SemanticColors.copy] and handed over through `EnterpriseService.semanticColorsFlow()`.
 */
object TesseraColors {
    // Brand.
    val brand = Color(0xFF1B5E63)
    val brandPaper = Color(0xFFF0EEE9)
    val brandInk = Color(0xFF0D1B1D)
    val brandAccent = Color(0xFF0E7972)

    // Light surfaces and text.
    private val lightBackground = Color(0xFFF0EEE9)
    private val lightSurface = Color(0xFFFBFAF7)
    private val lightSurface2 = Color(0xFFE6E2DA)
    private val lightSurface3 = Color(0xFFD6D1C7)
    private val lightBorder = Color(0xFFDCD7CE)
    private val lightBorderStrong = Color(0xFFC4BEB3)
    private val lightForegroundMuted = Color(0xFF465659)
    private val lightForegroundSubtle = Color(0xFF5E6D70)
    private val lightError = Color(0xFFB83A2E)
    private val lightSuccess = Color(0xFF17774D)
    private val lightWarning = Color(0xFF8E5A0B)
    private val lightPrimaryHover = Color(0xFF164D51)
    private val lightPrimaryPressed = Color(0xFF123F43)

    // Dark surfaces and text.
    private val darkBackground = Color(0xFF0D1B1D)
    private val darkSurface = Color(0xFF132628)
    private val darkSurface2 = Color(0xFF1B3134)
    private val darkSurface3 = Color(0xFF284447)
    private val darkBorder = Color(0xFF1E3538)
    private val darkBorderStrong = Color(0xFF2E4A4D)
    private val darkForegroundMuted = Color(0xFFA6B6B7)
    private val darkForegroundSubtle = Color(0xFF84989A)
    private val darkError = Color(0xFFEF6B5E)
    private val darkSuccess = Color(0xFF3CC48A)
    private val darkWarning = Color(0xFFE0A33C)
    private val darkPrimaryHover = Color(0xFF226F75)
    private val darkPrimaryPressed = Color(0xFF164D51)
    private val darkPrimaryText = Color(0xFF7CC3BC)

    val light: SemanticColors = compoundColorsLight.copy(
        bgAccentHovered = lightPrimaryHover,
        bgAccentPressed = lightPrimaryPressed,
        bgAccentRest = brand,
        bgAccentSelected = brand.copy(alpha = 0.16f),
        bgAccentSubtle = Color(0xFFD9E6E4),
        bgActionPrimaryDisabled = lightBorderStrong,
        bgActionPrimaryHovered = lightPrimaryHover,
        bgActionPrimaryPressed = lightPrimaryPressed,
        bgActionPrimaryRest = brand,
        bgActionSecondaryHovered = brandInk.copy(alpha = 0.06f),
        bgActionSecondaryPressed = brandInk.copy(alpha = 0.12f),
        bgActionSecondaryRest = lightBackground,
        bgActionTertiaryHovered = lightSurface2,
        bgActionTertiaryRest = lightBackground,
        bgActionTertiarySelected = lightSurface3,
        bgBadgeAccent = Color(0xFFD9E6E4),
        bgBadgeCritical = Color(0xFFF3DEDB),
        bgBadgeDefault = lightBackground,
        bgBadgePrimary = brandInk,
        bgBadgeSecondary = lightSurface3,
        bgCanvasDefault = lightBackground,
        bgCanvasDefaultLevel1 = lightSurface,
        bgCanvasDisabled = lightSurface2,
        bgCriticalHovered = Color(0xFF9C2F25),
        bgCriticalPrimary = lightError,
        bgCriticalSubtle = Color(0xFFF3DEDB),
        bgCriticalSubtleHovered = Color(0xFFEBCBC7),
        bgSubtlePrimary = lightSurface3,
        bgSubtleSecondary = lightSurface2,
        bgSubtleSecondaryLevel0 = lightSurface2,
        bgSubtleTertiary = lightSurface,
        bgSuccessHovered = Color(0xFF12603E),
        bgSuccessPressed = Color(0xFF0E4D32),
        bgSuccessRest = lightSuccess,
        bgSuccessSubtle = Color(0xFFD9ECE2),
        borderAccentPrimary = brand,
        borderAccentSubtle = Color(0xFF8DB3B0),
        borderCriticalHovered = Color(0xFF9C2F25),
        borderCriticalPrimary = lightError,
        borderCriticalSubtle = Color(0xFFDDA7A1),
        borderDisabled = lightBorder,
        borderFocused = brandAccent,
        borderInteractiveHovered = lightForegroundSubtle,
        borderInteractivePrimary = lightBorderStrong,
        borderInteractiveSecondary = lightBorder,
        borderSuccessPrimary = lightSuccess,
        borderSuccessSubtle = Color(0xFF9CC9B2),
        gradientActionStop1 = brandAccent,
        gradientActionStop2 = brand,
        gradientActionStop3 = lightPrimaryHover,
        gradientActionStop4 = lightPrimaryPressed,
        gradientSubtleStop1 = brand.copy(alpha = 0.20f),
        gradientSubtleStop2 = brand.copy(alpha = 0.16f),
        gradientSubtleStop3 = brand.copy(alpha = 0.12f),
        gradientSubtleStop4 = brand.copy(alpha = 0.08f),
        gradientSubtleStop5 = brand.copy(alpha = 0.04f),
        iconAccentPrimary = brand,
        iconAccentTertiary = brandAccent,
        iconCriticalPrimary = lightError,
        iconDisabled = Color(0xFF8A9799),
        iconOnSolidPrimary = brandPaper,
        iconPrimary = brandInk,
        iconPrimaryAlpha = brandInk.copy(alpha = 0.92f),
        iconQuaternary = Color(0xFF8A9799),
        iconQuaternaryAlpha = brandInk.copy(alpha = 0.48f),
        iconSecondary = lightForegroundMuted,
        iconSecondaryAlpha = brandInk.copy(alpha = 0.72f),
        iconSuccessPrimary = lightSuccess,
        iconTertiary = lightForegroundSubtle,
        iconTertiaryAlpha = brandInk.copy(alpha = 0.64f),
        iconWarningPrimary = lightWarning,
        separatorPrimary = lightBorderStrong,
        separatorSecondary = lightBorder,
        textActionAccent = brand,
        textActionPrimary = brandInk,
        textActionSuccess = lightSuccess,
        textBadgeAccent = lightPrimaryPressed,
        textCriticalPrimary = lightError,
        textDisabled = Color(0xFF7B898B),
        textLinkExternal = brand,
        textOnSolidPrimary = brandPaper,
        textPrimary = brandInk,
        textSecondary = lightForegroundMuted,
        textSuccessPrimary = lightSuccess,
        textWarningPrimary = lightWarning,
    )

    val dark: SemanticColors = compoundColorsDark.copy(
        bgAccentHovered = darkPrimaryHover,
        bgAccentPressed = darkPrimaryPressed,
        bgAccentRest = brand,
        bgAccentSelected = darkPrimaryText.copy(alpha = 0.20f),
        bgAccentSubtle = Color(0xFF16393C),
        bgActionPrimaryDisabled = darkSurface3,
        bgActionPrimaryHovered = darkPrimaryHover,
        bgActionPrimaryPressed = darkPrimaryPressed,
        bgActionPrimaryRest = brand,
        bgActionSecondaryHovered = brandPaper.copy(alpha = 0.08f),
        bgActionSecondaryPressed = brandPaper.copy(alpha = 0.14f),
        bgActionSecondaryRest = darkBackground,
        bgActionTertiaryHovered = darkSurface2,
        bgActionTertiaryRest = darkBackground,
        bgActionTertiarySelected = darkSurface3,
        bgBadgeAccent = Color(0xFF16393C),
        bgBadgeCritical = Color(0xFF3A1E1B),
        bgBadgeDefault = darkBackground,
        bgBadgePrimary = brandPaper,
        bgBadgeSecondary = darkSurface3,
        bgCanvasDefault = darkBackground,
        bgCanvasDefaultLevel1 = darkSurface,
        bgCanvasDisabled = darkSurface2,
        bgCriticalHovered = Color(0xFFF28478),
        bgCriticalPrimary = darkError,
        bgCriticalSubtle = Color(0xFF3A1E1B),
        bgCriticalSubtleHovered = Color(0xFF4A2622),
        bgSubtlePrimary = darkSurface3,
        bgSubtleSecondary = darkSurface2,
        bgSubtleSecondaryLevel0 = darkSurface,
        bgSubtleTertiary = darkSurface,
        bgSuccessHovered = Color(0xFF57D19C),
        bgSuccessPressed = Color(0xFF2FA975),
        bgSuccessRest = darkSuccess,
        bgSuccessSubtle = Color(0xFF173A2C),
        borderAccentPrimary = darkPrimaryText,
        borderAccentSubtle = Color(0xFF2F7A7A),
        borderCriticalHovered = Color(0xFFF28478),
        borderCriticalPrimary = darkError,
        borderCriticalSubtle = Color(0xFF8A3A33),
        borderDisabled = darkBorder,
        borderFocused = darkPrimaryText,
        borderInteractiveHovered = darkForegroundSubtle,
        borderInteractivePrimary = darkBorderStrong,
        borderInteractiveSecondary = darkBorder,
        borderSuccessPrimary = darkSuccess,
        borderSuccessSubtle = Color(0xFF2A6B4D),
        gradientActionStop1 = darkPrimaryText,
        gradientActionStop2 = brandAccent,
        gradientActionStop3 = brand,
        gradientActionStop4 = darkPrimaryPressed,
        gradientSubtleStop1 = darkPrimaryText.copy(alpha = 0.24f),
        gradientSubtleStop2 = darkPrimaryText.copy(alpha = 0.19f),
        gradientSubtleStop3 = darkPrimaryText.copy(alpha = 0.14f),
        gradientSubtleStop4 = darkPrimaryText.copy(alpha = 0.09f),
        gradientSubtleStop5 = darkPrimaryText.copy(alpha = 0.04f),
        iconAccentPrimary = darkPrimaryText,
        iconAccentTertiary = brandAccent,
        iconCriticalPrimary = darkError,
        iconDisabled = Color(0xFF5B6E70),
        iconOnSolidPrimary = brandPaper,
        iconPrimary = brandPaper,
        iconPrimaryAlpha = brandPaper.copy(alpha = 0.92f),
        iconQuaternary = Color(0xFF5B6E70),
        iconQuaternaryAlpha = brandPaper.copy(alpha = 0.40f),
        iconSecondary = darkForegroundMuted,
        iconSecondaryAlpha = brandPaper.copy(alpha = 0.68f),
        iconSuccessPrimary = darkSuccess,
        iconTertiary = darkForegroundSubtle,
        iconTertiaryAlpha = brandPaper.copy(alpha = 0.52f),
        iconWarningPrimary = darkWarning,
        separatorPrimary = darkBorderStrong,
        separatorSecondary = darkBorder,
        textActionAccent = darkPrimaryText,
        textActionPrimary = brandPaper,
        textActionSuccess = darkSuccess,
        textBadgeAccent = darkPrimaryText,
        textCriticalPrimary = darkError,
        textDisabled = Color(0xFF5B6E70),
        textLinkExternal = darkPrimaryText,
        textOnSolidPrimary = brandPaper,
        textPrimary = brandPaper,
        textSecondary = darkForegroundMuted,
        textSuccessPrimary = darkSuccess,
        textWarningPrimary = darkWarning,
    )

    val lightDark = SemanticColorsLightDark(light = light, dark = dark)
}
