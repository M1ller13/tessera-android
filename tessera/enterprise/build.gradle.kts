import config.BuildTimeConfig
import extension.buildConfigFieldStr
import extension.setupDependencyInjection
import extension.testCommonDependencies

/*
 * Copyright (c) 2026 Tessera.
 *
 * SPDX-License-Identifier: AGPL-3.0-only
 * Please see LICENSE files in the repository root for full details.
 */
plugins {
    id("io.element.android-compose-library")
}

android {
    namespace = "ru.tesseramessenger.android.enterprise"

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        // Links and the push gateway come from BuildTimeConfig (plugins/), the single place to change them.
        buildConfigFieldStr(
            name = "PUSH_GATEWAY_URL",
            value = BuildTimeConfig.PUSH_GATEWAY_URL ?: "",
        )
        buildConfigFieldStr(
            name = "URL_WEBSITE",
            value = BuildTimeConfig.URL_WEBSITE ?: "",
        )
        buildConfigFieldStr(
            name = "URL_PRIVACY",
            value = BuildTimeConfig.URL_PRIVACY ?: "",
        )
        buildConfigFieldStr(
            name = "URL_SUPPORT",
            value = BuildTimeConfig.URL_SUPPORT ?: "",
        )
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

setupDependencyInjection()

// Replaces :features:enterprise:impl-foss in the app: this module contributes the same bindings.
dependencies {
    implementation(projects.libraries.compound)
    api(projects.features.enterprise.api)
    implementation(projects.libraries.architecture)
    implementation(projects.libraries.androidutils)
    implementation(projects.libraries.matrix.api)

    testCommonDependencies(libs)
    testImplementation(projects.appconfig)
    testImplementation(projects.libraries.matrix.test)
}
