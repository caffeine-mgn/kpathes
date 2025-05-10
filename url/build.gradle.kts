import pw.binom.publish.allTargets
import pw.binom.publish.applyDefaultHierarchyBinomTemplate
import pw.binom.publish.dependsOn

import pw.binom.publish.plugins.PublicationPomInfoExtension

plugins {
    kotlin("multiplatform")
    id("maven-publish")
    id("pw.binom.publish")
}
kotlin {
    allTargets()
    js {
        browser {
            testTask {
                useKarma {
                    useFirefoxHeadless()
                }
            }
        }
    }
    wasmJs()
    wasmWasi{
        binaries.library()
        nodejs()
    }
    targets.all {
        compilations.all {
            kotlinOptions.freeCompilerArgs += listOf("-Xexpect-actual-classes")
        }
    }
    applyDefaultHierarchyBinomTemplate()
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(kotlin("stdlib"))
            }
        }
        val commonTest by getting {
            dependencies {
                api(kotlin("test"))
                api(kotlin("test-common"))
                api(kotlin("test-annotations-common"))
            }
        }
        dependsOn("wasm*Main", "nativeMain")
//        val jsMain by getting {
//            dependsOn(commonMain)
//            dependencies {
//                api(kotlin("stdlib-js"))
//            }
//        }
//        jvmTest.dependencies {
//            implementation("org.junit.jupiter:junit-jupiter-api:5.7.1")
//            implementation("org.junit.jupiter:junit-jupiter-engine:5.7.1")
//        }
//        val fakeImplMain by creating
//        val jsMain by getting {
//            dependsOn(fakeImplMain)
//        }
//        this.findByName("wasmJsMain")?.dependsOn(fakeImplMain)
//        this.findByName("wasmWasiMain")?.dependsOn(fakeImplMain)
    }
}

val publicationPomInfoExtension =
    extensions.findByType(PublicationPomInfoExtension::class.java)?.apply {
        useApache2License()
        gitScm("https://github.com/caffeine-mgn/atomic")
        author(
            id = "subochev",
            name = "Anton Subochev",
            email = "caffeine.mgn@gmail.com",
        )
    }
