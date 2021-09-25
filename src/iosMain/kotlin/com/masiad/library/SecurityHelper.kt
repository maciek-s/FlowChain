package com.masiad.library

import cocoapods.Tink.*
import platform.Foundation.*

@Suppress("CAST_NEVER_SUCCEEDS")
actual class SecurityHelper {

    private val aead by lazy {
        val aeadConfig = TINKAeadConfig()
        TINKConfig.registerConfig(aeadConfig, null)

        val keyTemplate = TINKAeadKeyTemplate(TINKAes256Gcm, null)
        val keySetName = "flow_chain_key_chain_keyset"
        try {
            val handle = TINKKeysetHandle(keySetName, null)
            TINKAeadFactory.primitiveWithKeysetHandle(handle, null)
        } catch (e: Exception) {
            val handle = TINKKeysetHandle(keyTemplate, null).also {
                it.writeToKeychainWithName(keySetName, false, null)
            }
            TINKAeadFactory.primitiveWithKeysetHandle(handle, null)
        } ?: throw IllegalStateException()
    }

    @kotlin.Throws(SecurityException::class)
    actual fun encrypt(text: String): String {
        try {
            val data = (text as NSString).dataUsingEncoding(NSUTF8StringEncoding)
            requireNotNull(data)
            val encryptedData = aead.encrypt(data, null, null)
            requireNotNull(encryptedData)
            return encryptedData.base64Encoding()
        } catch (e: Exception) {
            throw SecurityException(e.message, e)
        }
    }

    @kotlin.Throws(SecurityException::class)
    actual fun decrypt(encryptedText: String): String {
        try {
            val encryptedData = NSData.create(base64Encoding = encryptedText)
            requireNotNull(encryptedData)
            val decryptedData = aead.decrypt(encryptedData, null, null)
            requireNotNull(decryptedData)
            return NSString.create(decryptedData, NSUTF8StringEncoding) as String
        } catch (e: Exception) {
            throw SecurityException(e.message, e)
        }
    }

}