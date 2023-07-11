plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("co.touchlab.faktory.kmmbridge") version "0.3.7"
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
//    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
    kmmbridge {
        mavenPublishArtifacts()
        githubReleaseVersions()
        cocoapods("git@github.com:touchlab/PublicPodSpecs.git")
        versionPrefix.set("0.0.1")
    }
}

android {
    namespace = "com.ddanilov.kmmdemo"
    compileSdk = 33
    defaultConfig {
        minSdk = 28
    }
}
