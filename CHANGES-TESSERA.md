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
