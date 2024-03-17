pluginManagement {
    repositories {
        mavenLocal()
        maven(url = "https://repo.binom.pw")
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "BinomPathes"

include(":url")
