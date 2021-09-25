package com.masiad.library

import androidx.test.core.app.ApplicationProvider
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
actual class SecurityHelperTest :
    AbstractSecurityHelperTest(SecurityHelper(ApplicationProvider.getApplicationContext()))