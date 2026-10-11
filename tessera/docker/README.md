# Local build in Docker

The project is built in GitHub Actions (`.github/workflows/tessera-ci.yml`). This image is only a fallback.

- Limits (in the image's `~/.gradle/gradle.properties`): `org.gradle.jvmargs=-Xmx3g`, `org.gradle.workers.max=2`, `kotlin.daemon.jvmargs=-Xmx2g`.
- Run it only with the maintainer's permission, after `docker compose stop` of the stand (not `down -v`).
- Command lines: see the header of `Dockerfile`.
- The APK appears in `app/build/outputs/apk/gplay/debug/`.
