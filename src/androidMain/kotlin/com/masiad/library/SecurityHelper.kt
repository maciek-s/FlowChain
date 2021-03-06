package com.masiad.library


import android.content.Context
import android.util.Base64
import com.google.crypto.tink.Aead
import com.google.crypto.tink.KeyTemplates
import com.google.crypto.tink.aead.AeadConfig
import com.google.crypto.tink.integration.android.AndroidKeysetManager

actual class SecurityHelper(
    applicationContext: Context
) {

    private val aead by lazy {
        try {
            AeadConfig.register()
            AndroidKeysetManager.Builder()
                .withSharedPref(
                    applicationContext,
                    "flow_chain_shared_pref_keyset",
                    "flow_chain_pref"
                )
                .withKeyTemplate(KeyTemplates.get("AES256_GCM"))
                .withMasterKeyUri("android-keystore://flow_chain_master_key")
                .build()
                .keysetHandle
                .getPrimitive(Aead::class.java)
        } catch (e: Exception) {
            throw SecurityException(e.message, e)
        }
    }

    @Throws(SecurityException::class)
    actual fun encrypt(text: String): String {
        try {
            val encryptedData = aead.encrypt(text.toByteArray(charset = Charsets.UTF_8), null)
            return Base64.encodeToString(encryptedData, Base64.DEFAULT)
        } catch (e: Exception) {
            throw SecurityException(e.message, e)
        }
    }

    @Throws(SecurityException::class)
    actual fun decrypt(encryptedText: String): String {
        try {
            val encryptedData = Base64.decode(encryptedText, Base64.DEFAULT)
            val decryptedData = aead.decrypt(encryptedData, null)
            return decryptedData.toString(charset = Charsets.UTF_8)
        } catch (e: Exception) {
            throw SecurityException(e.message, e)
        }
    }
}