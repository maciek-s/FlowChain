object Libs {
    object Kotlin {
        const val version = "1.5.31"

        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    }

    object Kotlinx {
        object Coroutines {
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2"
        }
    }

    object DataStore {
        const val preferences = "androidx.datastore:datastore-preferences:1.0.0"
    }

    object Crypto {
        const val tink = "com.google.crypto.tink:tink-android:1.6.1"
    }

    object JUnit {
        const val junit = "junit:junit:4.13.2"
    }

    object RoboElectrics {
        const val robolectric = "org.robolectric:robolectric:4.4"
    }

    object AndroidXTest {
        const val core = "androidx.test:core:1.4.0"
    }
}