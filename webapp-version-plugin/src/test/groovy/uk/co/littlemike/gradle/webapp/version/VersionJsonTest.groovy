package uk.co.littlemike.gradle.webapp.version

import groovy.json.JsonSlurper
import org.junit.Test
import uk.co.littlemike.gradle.build.version.BuildInfo

import java.text.SimpleDateFormat

import static org.hamcrest.CoreMatchers.equalTo
import static org.hamcrest.CoreMatchers.notNullValue
import static org.hamcrest.MatcherAssert.assertThat

class VersionJsonTest {

    @Test
    void versionJsonShouldContainAllVersionInformation() {
        // Given
        Date date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse("20-12-2015 01:02:03")
        BuildInfo buildInfo = new BuildInfo(
                buildId: 'A build',
                buildTime: date,
                revision: 'A revision'
        )
        VersionInfo versionInfo = new VersionInfo(
                buildInfo: buildInfo,
                projectVersion: 'A version'
        )

        // When
        String jsonString = versionInfo.asJson()

        // Then
        Object json = new JsonSlurper().parseText(jsonString)
        assertThat(json, notNullValue())
        assertThat(json.version, equalTo('A version'))
        assertThat(json.build, equalTo('A build'))
        assertThat(json.built, equalTo('2015-12-20T01:02:03Z'))
        assertThat(json.revision, equalTo('A revision'))
    }
}
