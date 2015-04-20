Gradle webapp version plugin
============================

This plugin builds a static `version.json` file containing project build & version information and includes it in the
.war distributable.

Supported CI systems
--------------------

* TeamCity

How to use
----------

[Apply the plugin](https://plugins.gradle.org/plugin/uk.co.littlemike.webapp-version-plugin)

A `version.json` file with the following format will be included in the root of your .war file. Note that the `"version"`
is taken from the `project.version` property at the moment the `war` task is called.

```
{
    "version": "0.6",
    "build": "12",
    "built": "2015-04-19T21:18:03Z",
    "revision": "0a4f58f9dd476d8a28737e51a46d3eab0ce9e288"
}
```