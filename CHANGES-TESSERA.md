# Changes made by Tessera

Modified version of [element-hq/element-x-android](https://github.com/element-hq/element-x-android)
(AGPL-3.0 section 5(a)). Base: tag `v26.09.4`.

Branches: `upstream` — mirror of upstream release tags, never edited; `main` — Tessera.
History is not rewritten, force push to `main` is forbidden.

Every change to an upstream file gets its own commit with the `tessera:` prefix and a row below
(where and why), so a merge conflict is easy to locate.

## Changes to upstream files

| Date | File | Change | Why |
|---|---|---|---|
| 2026-10-11 | `plugins/src/main/kotlin/config/BuildTimeConfig.kt` | `APPLICATION_ID` = `ru.tesseramessenger.android` | own application id (ADR-0020); the Gradle `namespace` stays `io.element.android.x` |
| 2026-10-16 | `README.md` | note about the fork and `GIT_LFS_SKIP_SMUDGE=1` at the top | AGPL notice; LFS screenshots are not needed to build |
| 2026-10-16 | `plugins/src/main/kotlin/config/BuildTimeConfig.kt` | name `Tessera`, `URL_*` → `tessera-messenger.ru` (placeholders), `URL_SUPPORT`, `PUSH_GATEWAY_URL` added, `PUSH_CONFIG_INCLUDE_UNIFIED_PUSH` = false | F1: brand, links, push gateway stub, no UnifiedPush. Test: `TesseraBuildConfigTest` |
| 2026-10-16 | `plugins/src/main/kotlin/ModulesConfig.kt` | the analytics config is driven by the keys for every build, not only for `isEnterpriseBuild` | F1: keys are empty, so Posthog and Sentry are not compiled in (`services:analytics:noop`) |
| 2026-10-16 | `appconfig/build.gradle.kts` | `URL_POLICY`, `BUG_REPORT_URL`, `BUG_REPORT_APP_NAME` come from `BuildTimeConfig` in all builds | F1: no fallback to the Element rageshake server. Test: `TesseraBuildConfigTest` |
| 2026-10-16 | `appconfig/.../ApplicationConfig.kt` | `PRODUCTION_APPLICATION_NAME`, `DESKTOP_APPLICATION_NAME` = `Tessera` | F1: the name in strings. Test: `TesseraBuildConfigTest` |
| 2026-10-16 | `tests/konsist/.../KonsistLicenseTest.kt` | files of the modules `tessera/*` and `appicon/tessera` are exempt from the Element header check | our files carry the Tessera AGPL header; the tests stay green |
| 2026-10-16 | `tests/konsist/.../KonsistClassNameTest.kt` | `Tessera` added to the allowed prefixes of `@ContributesBinding` classes | `TesseraEnterpriseService` and the other bindings of `tessera/enterprise` |
| 2026-10-16 | `features/home/impl/**` (`build.gradle.kts`, `HomePresenter`, `HomeState`, `HomeStatePreviewParam`, `HomeView`) | F4: the Spaces tab is hidden by `TesseraFeatures.Spaces`; `HomeState.isSpacesEnabled`, `navigationBarItems`; the presenter falls back to the chats. Tests: `HomePresenterTest` (the flag off by default, the flag on), preview `aHomeState(isSpacesEnabled = false)` |
| 2026-10-16 | `features/preferences/impl/**` (`build.gradle.kts`, `PreferencesRootPresenter`, `PreferencesAccountPresenter`, `ShowDeveloperSettingsProvider`) | F4: Labs hidden by `TesseraFeatures.Labs`; deactivation with a password hidden by `TesseraFeatures.PasswordDeactivation`; the 7-tap unlock of the developer settings is a no-op in release builds. Tests: `PreferencesRootPresenterTest`, `PreferencesAccountPresenterTest`, `ShowDeveloperSettingsProviderTest` |
| 2026-10-16 | `features/location/api/build.gradle.kts` | `MAPTILER_API_KEY` only from `BuildTimeConfig` (empty), not from the environment or `local.properties` | F4: location sharing is off in every build (`LocationService.isServiceAvailable()` is false). Test: `TesseraLocationRemovedTest` |
| 2026-10-16 | `settings.gradle.kts` | includes `appicon:tessera` and the modules of `tessera/` | F1: the new modules |
| 2026-10-16 | `app/build.gradle.kts` | `projects.tessera.enterprise` instead of `features.enterprise.implFoss`, `projects.appicon.tessera` instead of `appicon.element` | F1: own EnterpriseService and icon (plan, section 2.2). Test: `TesseraEnterpriseServiceTest`, CI assembles the APK |
| 2026-10-16 | `app/src/main/res/values{,-night}/themes.xml`, `values/colors.xml` | splash icon `splash_logo`, splash background = Tessera canvas colors | F1: splash screen |

## Added files

| Date | File | Purpose |
|---|---|---|
| 2026-10-11 | `NOTICE`, `CHANGES-TESSERA.md` | AGPL-3.0 section 5(a) notices |
| 2026-10-11 | `.github/workflows/tessera-ci.yml` | fork CI: debug APK (gplay), upstream unit tests, weekly trial merge of the latest upstream tag |
| 2026-10-11 | `tessera/docker/Dockerfile`, `tessera/docker/README.md` | optional local build with memory limits (never run by default) |
| 2026-10-16 | `appicon/tessera/**` | F1: adaptive launcher icon with a monochrome layer (vector, `TesseraLogo` of the prototype), `onboarding_logo` (sign-in screen, light and dark), `splash_logo` |
| 2026-10-16 | `tessera/features/**` | F4: `TesseraFeatures` — flags of removed features (Spaces, Labs, deactivation with a password); `isFinished = true` and the default `false`, so nobody can switch them on. Test: `TesseraFeaturesTest` |
| 2026-10-16 | `tessera/enterprise/**` | F1: module that replaces `features/enterprise/impl-foss`: `TesseraEnterpriseService` (palette through `semanticColorsFlow`, push gateway from `BuildTimeConfig`, bug reports off), the other `*EnterpriseService`/hook bindings, `TesseraColors`, tests |

## Not changed yet (planned)

Branding, colors, fonts, links, push gateway — F1. Server allow-list and login — F2.
Matrix ID hiding — F3. Removal of unused features — F4.

## Signing

Only the debug key is used. The release key is created and stored outside the repository by the maintainer.
