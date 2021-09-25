plugins {
    kotlin("multiplatform") version Libs.Kotlin.version
    id("com.android.library")
    kotlin("native.cocoapods") version Libs.Kotlin.version
    id("maven-publish")
}

// CocoaPods requires the podspec to have a version.
version = "1.0"

group = Application.group
version = Application.version

repositories {
    google()
    mavenCentral()
}

kotlin {
    android {
        publishLibraryVariants("debug", "release")
    }
    iosX64("ios") {
        binaries {
            framework {
                baseName = "FlowChain"
            }
        }
    }
//    ios()
    cocoapods {
        framework {
            ios.deploymentTarget = "14.1"
            summary = "Some description for a Kotlin/Native module"
            homepage = "Link to a Kotlin/Native module homepage"
            baseName = "FlowChain"
        }
        pod("Tink") {
            version = "~> 1.6.1"
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Libs.Kotlinx.Coroutines.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Libs.DataStore.preferences)
                implementation(Libs.Crypto.tink)

            }
        }
        val androidTest by getting {
            dependencies {
                implementation(Libs.JUnit.junit)
                implementation(Libs.RoboElectrics.robolectric)
                implementation(Libs.AndroidXTest.core)
            }
        }
        val iosMain by getting
        val iosTest by getting
    }
}

android {
    compileSdk = Application.compileSdk
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = Application.minSdk
        targetSdk = Application.targetSdk
    }
}