package com.masiad.library

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

expect class SecurityHelperTest : AbstractSecurityHelperTest

abstract class AbstractSecurityHelperTest(private val securityHelper: SecurityHelper) {

    private val stringToEncrypt = "Some string"

    @Test
    fun encryptTest() {
        val encrypted = securityHelper.encrypt(stringToEncrypt)
        assertNotEquals(encrypted, stringToEncrypt, "$encrypted should not equals $stringToEncrypt")
    }

    @Test
    fun decryptTest() {
        val encrypted = securityHelper.encrypt(stringToEncrypt)
        val decrypted = securityHelper.decrypt(encrypted)
        assertEquals(decrypted, stringToEncrypt, "$decrypted should be equals $stringToEncrypt")
    }
}