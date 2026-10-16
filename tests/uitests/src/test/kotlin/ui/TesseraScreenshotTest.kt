/*
 * Copyright (c) 2026 Element Creations Ltd.
 * Copyright 2026 Tessera.
 *
 * SPDX-License-Identifier: AGPL-3.0-only OR LicenseRef-Element-Commercial.
 * Please see LICENSE files in the repository root for full details.
 */

package ui

import android.content.res.Configuration
import android.os.LocaleList
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import base.ComposablePreviewProvider
import com.android.resources.NightMode
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import io.element.android.compound.theme.ElementTheme
import io.element.android.compound.theme.LocalDefaultSemanticColors
import io.element.android.compound.theme.Theme
import io.element.android.features.login.impl.screens.onboarding.OnBoardingView
import io.element.android.features.login.impl.screens.onboarding.anOnBoardingState
import io.element.android.libraries.matrix.ui.media.contentvalidation.LocalEventContentValidationState
import io.element.android.libraries.matrix.ui.media.contentvalidation.NoopEventContentValidationCache
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.tesseramessenger.android.enterprise.TesseraColors
import java.util.Locale
import ru.tesseramessenger.android.appicon.R as TesseraR

/**
 * Screenshots of the main screens with the Tessera palette, in Russian: splash, sign-in, chat list.
 * The CI job "Tessera screenshots" records them and attaches the PNG files to the run.
 * Run: `./gradlew :tests:uitests:recordPaparazziDebug --tests 'ui.TesseraScreenshotTest'`.
 */
@RunWith(TestParameterInjector::class)
class TesseraScreenshotTest(
    @TestParameter private val screen: TesseraScreen,
    @TestParameter private val night: Boolean,
) {
    enum class TesseraScreen {
        Splash,
        SignIn,
        ChatList,
        LauncherIcon,
    }

    @get:Rule(order = 0)
    val layoutLibErrorFilterStatement = LayoutLibErrorFilterStatement()

    @get:Rule(order = 1)
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.NEXUS_5.copy(
            nightMode = if (night) NightMode.NIGHT else NightMode.NOTNIGHT,
            locale = "ru",
            softButtons = false,
        ),
        maxPercentDifference = 0.01,
    )

    @Test
    fun snapshot() {
        val locale = Locale.Builder().setLanguage("ru").build()
        Locale.setDefault(locale)
        paparazzi.snapshot(name = "${screen.name}_${if (night) "night" else "day"}") {
            CompositionLocalProvider(
                LocalInspectionMode provides true,
                // The palette of the app: previews and views call ElementTheme without colors.
                LocalDefaultSemanticColors provides TesseraColors.lightDark,
                LocalConfiguration provides Configuration().apply {
                    setLocales(LocaleList(locale))
                    uiMode = if (night) Configuration.UI_MODE_NIGHT_YES else Configuration.UI_MODE_NIGHT_NO
                },
                LocalEventContentValidationState provides NoopEventContentValidationCache(),
            ) {
                ElementTheme(theme = if (night) Theme.Dark else Theme.Light) {
                    Box(modifier = Modifier.background(ElementTheme.colors.bgCanvasDefault)) {
                        when (screen) {
                            TesseraScreen.Splash -> Splash()
                            TesseraScreen.SignIn -> SignIn()
                            TesseraScreen.ChatList -> ChatList()
                            TesseraScreen.LauncherIcon -> LauncherIcon()
                        }
                    }
                }
            }
        }
    }

    /** The system splash screen is not a composable: draw its background and icon the way Android does. */
    @Composable
    private fun Splash() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(ElementTheme.colors.bgCanvasDefault),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = TesseraR.drawable.splash_logo),
                contentDescription = null,
            )
        }
    }

    /** The adaptive launcher icon the way a launcher masks it: squircle, circle, and the themed (monochrome) layer. */
    @Composable
    private fun LauncherIcon() {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(ElementTheme.colors.bgCanvasDefault)
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconLayers(Modifier.size(88.dp).clip(RoundedCornerShape(28.dp)))
            IconLayers(Modifier.size(88.dp).clip(CircleShape))
            Box(
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape)
                    .background(ElementTheme.colors.bgSubtlePrimary),
            ) {
                Image(
                    painter = painterResource(id = TesseraR.drawable.ic_launcher_monochrome),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(ElementTheme.colors.textPrimary),
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }

    @Composable
    private fun IconLayers(modifier: Modifier) {
        Box(modifier = modifier) {
            Image(
                painter = painterResource(id = TesseraR.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
            )
            Image(
                painter = painterResource(id = TesseraR.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
            )
        }
    }

    @Composable
    private fun SignIn() {
        OnBoardingView(
            // The same resource the app finds by name (`onboarding_logo`) in DefaultOnBoardingLogoResIdProvider.
            state = anOnBoardingState(
                productionApplicationName = "Tessera",
                customLogoResId = TesseraR.drawable.onboarding_logo,
            ),
            onBackClick = {},
            onDeveloperSettingsClick = {},
            onSignInWithQrCode = {},
            onSignIn = {},
            onCreateAccount = {},
            onOAuthDetails = {},
            onNeedLoginPassword = {},
            onLearnMoreClick = {},
            onReportProblem = {},
        )
    }

    /** `HomeViewPreview` with index 5 of `HomeStatePreviewParam`: many chats, Spaces removed. */
    @Composable
    private fun ChatList() {
        val preview = ComposablePreviewProvider.values
            .map { it.value }
            .single {
                it.methodName == "HomeViewPreview" &&
                    it.previewIndex == 5 &&
                    it.previewInfo.name == if (night) "Night" else "Day"
            }
        preview()
    }
}
