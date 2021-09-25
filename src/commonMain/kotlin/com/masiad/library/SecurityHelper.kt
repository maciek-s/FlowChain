package com.masiad.library

expect class SecurityHelper {

    fun encrypt(text: String): String

    fun decrypt(encryptedText: String): String
}