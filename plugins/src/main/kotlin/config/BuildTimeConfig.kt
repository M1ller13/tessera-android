/*
 * Copyright (c) 2025 Element Creations Ltd.
 * Copyright 2025 New Vector Ltd.
 *
 * SPDX-License-Identifier: AGPL-3.0-only OR LicenseRef-Element-Commercial.
 * Please see LICENSE files in the repository root for full details.
 */

package config

object BuildTimeConfig {
    const val APPLICATION_ID = "ru.tesseramessenger.android"
    const val APPLICATION_NAME = "Tessera"
    val METADATA_HOST_REVERSED: String? = null
    val OAUTH_CLIENT_URL_PATH: String? = "apps/android"

    // Tessera pages: placeholders until the site is published (the same host as the Keycloak theme).
    val URL_WEBSITE: String? = "https://tessera-messenger.ru"
    val URL_LOGO: String? = "https://tessera-messenger.ru/logo.png"
    val URL_COPYRIGHT: String? = "https://tessera-messenger.ru/copyright"
    val URL_ACCEPTABLE_USE: String? = "https://tessera-messenger.ru/terms"
    val URL_PRIVACY: String? = "https://tessera-messenger.ru/privacy"
    val URL_POLICY: String? = "https://tessera-messenger.ru/privacy"
    val URL_SUPPORT: String? = "https://tessera-messenger.ru/support"
    val SERVICES_MAPTILER_BASE_URL: String? = null
    val SERVICES_MAPTILER_APIKEY: String? = null
    val SERVICES_MAPTILER_LIGHT_MAPID: String? = null
    val SERVICES_MAPTILER_DARK_MAPID: String? = null

    // Analytics and error reports are off: the privacy policy promises none.
    val SERVICES_POSTHOG_HOST: String? = null
    val SERVICES_POSTHOG_APIKEY: String? = null
    val SERVICES_SENTRY_DSN: String? = null
    val SERVICES_SENTRY_DSN_RUST: String? = null
    val BUG_REPORT_URL: String? = null
    val BUG_REPORT_APP_NAME: String? = null
    const val PUSH_CONFIG_INCLUDE_FIREBASE: Boolean = true
    const val PUSH_CONFIG_INCLUDE_UNIFIED_PUSH: Boolean = false

    // Push gateway (Sygnal) for Firebase; a stub until the real one is deployed.
    val PUSH_GATEWAY_URL: String? = "https://push.tessera-messenger.ru/_matrix/push/v1/notify"
    val PUSHER_APP_ID_RELEASE: String? = null
    val PUSHER_APP_ID_DEBUG: String? = null
    val PUSHER_APP_ID_NIGHTLY: String? = null
}
