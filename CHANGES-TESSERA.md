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

## Added files

| Date | File | Purpose |
|---|---|---|
| 2026-10-11 | `NOTICE`, `CHANGES-TESSERA.md` | AGPL-3.0 section 5(a) notices |
| 2026-10-11 | `.github/workflows/tessera-ci.yml` | fork CI: debug APK (gplay), upstream unit tests, weekly trial merge of the latest upstream tag |
| 2026-10-11 | `tessera/docker/Dockerfile`, `tessera/docker/README.md` | optional local build with memory limits (never run by default) |

## Not changed yet (planned)

Branding, colors, fonts, links, push gateway — F1. Server allow-list and login — F2.
Matrix ID hiding — F3. Removal of unused features — F4.

## Signing

Only the debug key is used. The release key is created and stored outside the repository by the maintainer.
