package uk.co.littlemike.gradle.webapp.version

import uk.co.littlemike.gradle.build.version.BuildInfo

class VersionInfo {
    BuildInfo buildInfo;
    String projectVersion;

    String asJson() {
"""{
    "version": "${projectVersion}",
    "build": "${buildInfo.buildId}",
    "built": "${buildInfo.buildTime.format("yyyy-MM-dd'T'HH:mm:ss'Z'")}",
    "revision": "${buildInfo.revision}"
}"""
    }
}
