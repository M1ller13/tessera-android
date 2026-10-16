/*
 * Copyright (c) 2026 Tessera.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE files in the repository root for full details.
 */

package ru.tesseramessenger.android.features

import io.element.android.libraries.core.meta.BuildMeta
import io.element.android.libraries.featureflag.api.Feature

/**
 * Features of Element X that Tessera does not ship.
 *
 * They are flags instead of deleted code, so merging a new upstream release stays cheap.
 * Every flag is *finished* (`isFinished = true`): `FeatureFlagService` then always returns the default value,
 * so a user can not switch them on in the developer options or in the labs.
 */
enum class TesseraFeatures(
    override val key: String,
    override val title: String,
    override val description: String,
    override val defaultValue: (BuildMeta) -> Boolean = { false },
) : Feature {
    /** The "Spaces" tab of the home screen and everything reached from it. */
    Spaces(
        key = "tessera.spaces",
        title = "Spaces",
        description = "The Spaces tab of the home screen. Not part of Tessera.",
    ),

    /** The "Labs" screen in the settings. */
    Labs(
        key = "tessera.labs",
        title = "Labs",
        description = "The Labs screen of the settings. Not part of Tessera.",
    ),

    /**
     * Deactivation of the account with a password inside the app.
     * Replaced by the account deletion page of the site (ADR-0011).
     */
    PasswordDeactivation(
        key = "tessera.password_deactivation",
        title = "Deactivate account with a password",
        description = "Not part of Tessera: the account is deleted on the site.",
    );

    override val isFinished: Boolean = true
    override val isInLabs: Boolean = false
}
