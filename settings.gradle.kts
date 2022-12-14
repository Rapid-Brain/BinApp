pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "BinApp"
include(
    ":app",
    ":core",
    ":data:exchangerate",
    ":ui:home",
    ":ui:detail",
    ":data:api",
    ":ui:search",
    ":ui:common",
)