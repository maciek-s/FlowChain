plugins {
    kotlin("multiplatform") version Libs.Kotlin.version
    id("com.android.library")
    id("maven-publish")
}

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
                baseName = "library"
            }
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
            }
        }
        val iosMain by getting
        val iosTest by getting
    }
}

android {
    compileSdkVersion(Application.compileSdk)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(Application.minSdk)
        targetSdkVersion(Application.targetSdk)
    }
}