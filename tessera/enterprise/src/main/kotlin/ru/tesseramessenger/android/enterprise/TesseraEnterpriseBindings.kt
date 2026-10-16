/*
 * Copyright (c) 2026 Tessera.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE files in the repository root for full details.
 */

package ru.tesseramessenger.android.enterprise

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesBinding
import io.element.android.features.enterprise.api.ClientBuilderEnterpriseHook
import io.element.android.features.enterprise.api.ClientEnterpriseHook
import io.element.android.features.enterprise.api.IsEnterpriseBuild
import io.element.android.features.enterprise.api.SessionEnterpriseService
import io.element.android.features.enterprise.api.remoteconfig.CustomMapTilerConfigProvider
import io.element.android.features.enterprise.api.remoteconfig.MapTilerConfig
import io.element.android.libraries.di.SessionScope
import io.element.android.libraries.matrix.api.MatrixClient
import io.element.android.libraries.matrix.api.MatrixClientBuilder
import io.element.android.libraries.matrix.api.core.SessionId

// The bindings below are the ones of `:features:enterprise:impl-foss`, which this module replaces in the app.

@ContributesBinding(AppScope::class)
class TesseraClientBuilderEnterpriseHook : ClientBuilderEnterpriseHook {
    override suspend fun beforeClientCreation(clientBuilder: MatrixClientBuilder): MatrixClientBuilder = clientBuilder

    override suspend fun beforeClientCreationWithSession(clientBuilder: MatrixClientBuilder, sessionId: SessionId): MatrixClientBuilder = clientBuilder
}

@ContributesBinding(AppScope::class)
class TesseraClientEnterpriseHook : ClientEnterpriseHook {
    override suspend fun invoke(client: MatrixClient) = Unit
}

// Not an Element Pro build: the application name comes from `ApplicationConfig`.
@ContributesBinding(AppScope::class)
class TesseraIsEnterpriseBuild : IsEnterpriseBuild {
    override fun invoke(): Boolean = false
}

@ContributesBinding(SessionScope::class)
class TesseraSessionEnterpriseService : SessionEnterpriseService {
    override suspend fun init() = Unit
    override suspend fun tweakMasUrl(url: String): String = url
    override suspend fun isElementCallAvailable(): Boolean = true
    override suspend fun isEncryptionDisabledByHomeserver(): Boolean = false
}

// The location feature is removed (F4), so there is no custom map configuration.
@ContributesBinding(SessionScope::class)
class TesseraCustomMapTilerConfigProvider : CustomMapTilerConfigProvider {
    override suspend fun get(): Result<MapTilerConfig?> = Result.success(null)
}
