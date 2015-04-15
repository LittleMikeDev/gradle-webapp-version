package uk.co.littlemike.gradle.webapp.version

import org.gradle.api.Plugin
import org.gradle.api.Project

class WebappVersionPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.task('hello') << {
            println "Hello world!"
        }
    }
}
