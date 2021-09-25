package com.masiad.library

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import platform.Foundation.NSNotificationCenter
import platform.Foundation.NSUserDefaults
import platform.Foundation.NSUserDefaultsDidChangeNotification

actual class FlowChain {

    private val userDefaults by lazy {
        NSUserDefaults(suiteName = "FlowChainUserDefaults")
    }
    private val securityHelper by lazy {
        SecurityHelper()
    }

    @ExperimentalCoroutinesApi
    @kotlin.Throws(SecurityException::class)
    actual fun getValue(
        key: String,
        defaultValue: String?
    ): Flow<String?> {
        return callbackFlow<String?> {
            fun getKeyValue(): String? {
                return userDefaults.stringForKey(key)?.let { encrypted ->
                    securityHelper.decrypt(encrypted)
                } ?: defaultValue
            }

            var previousValue: String? = getKeyValue()
            trySend(previousValue)
            NSNotificationCenter.defaultCenter.addObserverForName(
                NSUserDefaultsDidChangeNotification,
                userDefaults,
                null
            ) {
                val currentValue = getKeyValue()
                if (previousValue != currentValue) {
                    previousValue = currentValue
                    trySend(currentValue)
                }
            }

            awaitClose { NSNotificationCenter.defaultCenter.removeObserver(userDefaults) }
        }
    }

    @kotlin.Throws(SecurityException::class, CancellationException::class)
    actual suspend fun setValue(key: String, value: String) {
        userDefaults.setObject(securityHelper.encrypt(value), key)
    }

}