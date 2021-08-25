object Libs {
    object Kotlin {
        const val version = "1.5.21"

        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    }

    object Kotlinx {
        object Coroutines {
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1"
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
}