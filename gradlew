#!/usr/bin/env sh

# Simplified gradlew launcher (will download Gradle on first run)
set -e
APP_BASE_DIR=$(cd "$(dirname "$0")"; pwd)
WRAPPER_JAR="$APP_BASE_DIR/gradle/wrapper/gradle-wrapper.jar"
WRAPPER_PROPERTIES="$APP_BASE_DIR/gradle/wrapper/gradle-wrapper.properties"
# Download wrapper jar if missing
if [ ! -f "$WRAPPER_JAR" ]; then
  mkdir -p "$(dirname "$WRAPPER_JAR")"
  curl -sSL -o "$WRAPPER_JAR" https://repo1.maven.org/maven2/org/gradle/gradle-wrapper/8.7/gradle-wrapper-8.7.jar || true
fi
exec java -jar "$WRAPPER_JAR" "$@"
