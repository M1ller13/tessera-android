/*
 * Copyright (c) 2026 Element Creations Ltd.
 * Copyright 2026 Tessera.
 *
 * SPDX-License-Identifier: AGPL-3.0-only OR LicenseRef-Element-Commercial.
 * Please see LICENSE files in the repository root for full details.
 */

package io.element.android.x

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.io.File

/**
 * Guards `values{,-ru}/tessera_strings.xml`: the strings of the generated `localazy.xml` files that name Element
 * in the interface (AGPL section 7e) are overridden by the app module.
 * The test reads the sources: the working directory of a unit test is the module directory (`app/`).
 */
class TesseraStringsTest {
    private val stringRegex = Regex("""<string name="([^"]+)">"(.*?)"</string>""", RegexOption.DOT_MATCHES_ALL)

    /** Overridden name to the upstream file that defines it. */
    private val overrides = mapOf(
        "call_invalid_audio_device_bluetooth_devices_disabled" to "../features/call/impl/src/main/res/values/localazy.xml",
        "screen_incoming_call_subtitle_android" to "../features/call/impl/src/main/res/values/localazy.xml",
        "screen_room_timeline_legacy_call" to "../features/messages/impl/src/main/res/values/localazy.xml",
        "screen_notification_settings_sound_element_default" to "../features/preferences/impl/src/main/res/values/localazy.xml",
        "screen_notification_settings_sound_element_fade" to "../features/preferences/impl/src/main/res/values/localazy.xml",
    )

    /** Strings that name Element but are not shown in Tessera: the flows are hidden or for developers only. */
    private val notShown = setOf(
        // Element Pro and Element Classic flows of the login (the server is fixed, Classic is not installed).
        "screen_change_server_error_element_pro_required_message",
        "screen_change_server_error_element_pro_required_title",
        "screen_missing_key_backup_open_element_classic",
        "screen_missing_key_backup_step_1",
        "screen_server_confirmation_message_login_element_dot_io",
        // The developer settings: not reachable in release builds.
        "screen_advanced_settings_element_call_base_url",
        "screen_advanced_settings_element_call_base_url_description",
    )

    private fun strings(file: File): Map<String, String> =
        stringRegex.findAll(file.readText()).associate { it.groupValues[1] to it.groupValues[2] }

    @Test
    fun `English and Russian overrides define the same names`() {
        val en = strings(File("src/main/res/values/tessera_strings.xml"))
        val ru = strings(File("src/main/res/values-ru/tessera_strings.xml"))
        assertThat(en.keys).containsExactlyElementsIn(overrides.keys)
        assertThat(ru.keys).containsExactlyElementsIn(overrides.keys)
    }

    @Test
    fun `overridden strings do not name Element`() {
        listOf("values", "values-ru").forEach { dir ->
            strings(File("src/main/res/$dir/tessera_strings.xml")).forEach { (name, value) ->
                assertThat(value).doesNotContain("Element")
                assertThat(name).isNotEmpty()
            }
        }
    }

    @Test
    fun `every override replaces an existing upstream string`() {
        overrides.forEach { (name, upstream) ->
            assertThat(File(upstream).readText()).contains("name=\"$name\"")
        }
    }

    @Test
    fun `no new upstream string names Element without a decision`() {
        val namingElement = listOf("../features", "../libraries", "../services", "../app")
            .map { File(it) }
            .flatMap { root -> root.walkTopDown().onEnter { it.name != "build" }.toList() }
            .filter { it.isFile && it.name == "localazy.xml" && it.parentFile.name == "values" }
            .flatMap { file -> strings(file).filterValues { "Element" in it }.keys }
            .toSet()
        // A new upstream string that names Element: override it in tessera_strings.xml or add it to `notShown`.
        assertThat(namingElement).containsExactlyElementsIn(overrides.keys + notShown)
    }
}
