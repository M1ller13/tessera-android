# Local build image for Tessera Android (debug APK, gplay flavor).
# NOT built or run by default: the project is built in GitHub Actions.
# Run locally only with the maintainer's permission and with the stand stopped.
#
#   docker build -f tessera/docker/Dockerfile -t tessera-android-build .
#   docker run --rm --memory=6g --cpus=2 -v "$PWD:/src" -v tessera-gradle:/root/.gradle tessera-android-build
#
# Memory limits live in the image's user-level gradle.properties, so the
# repository's own gradle.properties (and merges from upstream) stay untouched.

FROM eclipse-temurin:21-jdk

ARG CMDLINE_TOOLS_VERSION=11076708

ENV ANDROID_HOME=/opt/android-sdk \
    ANDROID_SDK_ROOT=/opt/android-sdk \
    GRADLE_USER_HOME=/root/.gradle

RUN apt-get update \
    && apt-get install -y --no-install-recommends unzip curl git ca-certificates \
    && rm -rf /var/lib/apt/lists/*

RUN mkdir -p ${ANDROID_HOME}/cmdline-tools \
    && curl -fsSL -o /tmp/tools.zip \
        https://dl.google.com/android/repository/commandlinetools-linux-${CMDLINE_TOOLS_VERSION}_latest.zip \
    && unzip -q /tmp/tools.zip -d ${ANDROID_HOME}/cmdline-tools \
    && mv ${ANDROID_HOME}/cmdline-tools/cmdline-tools ${ANDROID_HOME}/cmdline-tools/latest \
    && rm /tmp/tools.zip \
    && yes | ${ANDROID_HOME}/cmdline-tools/latest/bin/sdkmanager --licenses > /dev/null

# Resource limits (16 GB dev machine shared with the Docker stand).
RUN mkdir -p ${GRADLE_USER_HOME} \
    && printf '%s\n' \
        'org.gradle.jvmargs=-Xmx3g -Dfile.encoding=UTF-8 -XX:+UseG1GC' \
        'org.gradle.workers.max=2' \
        'kotlin.daemon.jvmargs=-Xmx2g' \
        'org.gradle.parallel=false' \
        > ${GRADLE_USER_HOME}/gradle.properties

WORKDIR /src
CMD ["./gradlew", "--no-daemon", "--max-workers=2", ":app:assembleGplayDebug"]
