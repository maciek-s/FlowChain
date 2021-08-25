package com.masiad.library

import kotlinx.coroutines.flow.Flow

actual class FlowChain {

    actual fun getValue(
        key: String,
        defaultValue: String?
    ): Flow<String?> {
        TODO("Not yet implemented")
    }

    actual suspend fun setValue(key: String, value: String) {
        TODO("Not yet implemented")
    }

}