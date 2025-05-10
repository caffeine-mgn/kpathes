import pw.binom.publish.getExternalVersion

plugins {
    id("com.github.gmazzo.buildconfig") version "3.0.3" apply false
    id("pw.binom.publish") version "0.1.18" apply false
    kotlin("multiplatform") version "2.1.20" apply false
}

allprojects {
    version = getExternalVersion()
    group = "pw.binom"

    repositories {
        mavenLocal()
        maven(url = "https://repo.binom.pw")
        mavenCentral()
        gradlePluginPortal()
    }
}

tasks {
    val publishToMavenLocal by creating {
        val self = this
        getTasksByName("publishToMavenLocal", true).forEach {
            if (it !== self) {
                dependsOn(it)
            }
        }
    }

    val publish by creating {
        val self = this
        getTasksByName("publish", true).forEach {
            if (it !== self) {
                dependsOn(it)
            }
        }
    }
}
