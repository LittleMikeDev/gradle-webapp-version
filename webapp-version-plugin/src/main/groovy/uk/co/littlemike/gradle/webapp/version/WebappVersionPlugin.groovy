package uk.co.littlemike.gradle.webapp.version

import org.gradle.api.Plugin
import org.gradle.api.Project

class WebappVersionPlugin implements Plugin<Project> {
    private static final String FILENAME = 'version.json';

    @Override
    void apply(Project project) {
        File fileLocation = new File(project.buildDir, 'webapp-version');

        project.task('generate-version-json') << {
            VersionInfo versionInfo = getBuildVersionInfo()
            versionInfo.version = project.version
            writeVersionFile(fileLocation, generateVersionJson(versionInfo))
        }

        project.apply(plugin: 'war')
        project.war.dependsOn('generate-version-json')
        project.war {
            from fileLocation
            include FILENAME
        }
    }

    VersionInfo getBuildVersionInfo() {
        new VersionInfo()
    }

    String generateVersionJson(VersionInfo versionInfo) {
        getClass().getResourceAsStream('version.json').text
                .replace('@version@', versionInfo.version)
    }

    void writeVersionFile(File location, String json) {
        location.mkdirs();
        File file = new File(location, FILENAME)
        file.delete()
        file << json
    }
}
