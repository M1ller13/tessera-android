/*
 * Copyright (c) 2026 Tessera.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE files in the repository root for full details.
 */

package ru.tesseramessenger.android.enterprise

import androidx.compose.ui.graphics.Color
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesBinding
import io.element.android.compound.colors.SemanticColorsLightDark
import io.element.android.features.enterprise.api.BugReportUrl
import io.element.android.features.enterprise.api.EnterpriseService
import io.element.android.libraries.matrix.api.ClientUrlContentFetcher
import io.element.android.libraries.matrix.api.accountprovider.AccountProvider
import io.element.android.libraries.matrix.api.core.SessionId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/**
 * Tessera implementation of [EnterpriseService]: the brand palette, the push gateway from the build
 * config, no bug reports. The user can sign in only to the servers of [TesseraAccountProviders]:
 * the Tessera one in release, plus the dev stand in debug builds.
 */
@ContributesBinding(AppScope::class)
class TesseraEnterpriseService : EnterpriseService {
    private val allowList: List<AccountProvider> = TesseraAccountProviders.allowList(isDebug = BuildConfig.DEBUG)

    override suspend fun isEnterpriseUser(sessionId: SessionId) = false
    override suspend fun tweakMasUrl(url: String, urlContentFetcher: ClientUrlContentFetcher) = url
    override fun accountProviderAllowList(): List<AccountProvider> = allowList
    override fun canConnectToAnyAccountProvider(): Boolean = false
    override suspend fun isAllowedToConnectToAccountProvider(accountProvider: AccountProvider) =
        allowList.any { it.matches(accountProvider) }

    // Tessera is not Element Pro and never asks the homeserver about it.
    override suspend fun isElementProEnforced(serverName: String): Boolean = false

    override suspend fun overrideBrandColor(sessionId: SessionId?, brandColor: String?) = Unit

    override fun brandColorsFlow(sessionId: SessionId?): Flow<Color?> = flowOf(TesseraColors.brand)

    override fun semanticColorsFlow(sessionId: SessionId?): Flow<SemanticColorsLightDark> = flowOf(TesseraColors.lightDark)

    override fun firebasePushGateway(): String? = BuildConfig.PUSH_GATEWAY_URL.takeIf { it.isNotEmpty() }

    // UnifiedPush is not built into the app.
    override fun unifiedPushDefaultPushGateway(): String? = null

    override fun bugReportUrlFlow(sessionId: SessionId?): Flow<BugReportUrl> = flowOf(BugReportUrl.Disabled)

    override fun getNoisyNotificationChannelId(sessionId: SessionId): String? = null
}
