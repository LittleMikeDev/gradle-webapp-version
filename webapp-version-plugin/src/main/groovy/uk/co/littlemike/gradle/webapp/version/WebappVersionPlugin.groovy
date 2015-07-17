package uk.co.littlemike.gradle.webapp.version

import org.gradle.api.Plugin
import org.gradle.api.Project

class WebappVersionPlugin implements Plugin<Project> {
    private static final String FILENAME = 'version.json';

    @Override
    void apply(Project project) {
        File fileLocation = new File(project.buildDir, 'webapp-version');

        project.apply(plugin: 'uk.co.littlemike.build-version-plugin')
        project.task('generate-version-json') << {
            writeVersionFile(fileLocation, new VersionInfo(
                    buildInfo: project.buildInfo,
                    projectVersion: project.version))
        }

        project.apply(plugin: 'war')
        project.war.dependsOn('generate-version-json')
        project.war {
            from(fileLocation) {
                include FILENAME
            }
        }
    }

    void writeVersionFile(File location, VersionInfo versionInfo) {
        location.mkdirs();
        File file = new File(location, FILENAME)
        file.delete()
        file << versionInfo.asJson()
    }
}
