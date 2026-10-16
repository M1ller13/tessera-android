/*
 * Copyright (c) 2026 Tessera.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE files in the repository root for full details.
 */

package ru.tesseramessenger.android.enterprise

import io.element.android.libraries.matrix.api.accountprovider.AccountProvider

/**
 * The servers the app may sign in to (ELEMENT-X-FORK-PLAN, section 3).
 *
 * Release: only the Tessera server, found by its name through `.well-known`.
 * Debug: the dev stand first (it is the default, reached over `adb reverse`), then the Tessera server.
 */
object TesseraAccountProviders {
    val production = AccountProvider.Managed(
        serverName = BuildConfig.SERVER_NAME,
        baseUrl = "https://${BuildConfig.SERVER_NAME}",
        canUseServerName = true,
    )

    val stand = AccountProvider.Managed(
        serverName = BuildConfig.STAND_HOMESERVER_URL,
        baseUrl = BuildConfig.STAND_HOMESERVER_URL,
        canUseServerName = false,
    )

    fun allowList(isDebug: Boolean): List<AccountProvider> = if (isDebug) listOf(stand, production) else listOf(production)
}
