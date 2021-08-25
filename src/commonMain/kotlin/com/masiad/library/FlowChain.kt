package com.masiad.library

import kotlinx.coroutines.flow.Flow

expect class FlowChain {

    fun getValue(
        key: String,
        defaultValue: String? = null
    ): Flow<String?>

    suspend fun setValue(
        key: String,
        value: String
    )
}