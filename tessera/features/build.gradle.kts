import extension.testCommonDependencies

/*
 * Copyright (c) 2026 Tessera.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE files in the repository root for full details.
 */
plugins {
    id("io.element.android-library")
}

android {
    namespace = "ru.tesseramessenger.android.features"
}

dependencies {
    implementation(projects.libraries.core)
    api(projects.libraries.featureflag.api)

    testCommonDependencies(libs)
    testImplementation(projects.libraries.matrix.test)
}
