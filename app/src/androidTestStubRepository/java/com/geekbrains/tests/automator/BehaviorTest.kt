package com.geekbrains.tests.automator

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.UiDevice
import com.geekbrains.tests.TEST_NUMBER_OF_RESULTS_14
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class BehaviorTest {
    private val uiDevice: UiDevice = UiDevice.getInstance(getInstrumentation())
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val packageName = context.packageName

    @Before
    fun setup() = setupBehavior(uiDevice, context, packageName)

    @Test
    fun test_MainActivityIsStarted() = test_MainActivityIsStarted_Behavior(uiDevice, packageName)

    @Test
    fun test_SearchIsPositive() =
        test_SearchIsPositive_Behavior(uiDevice, packageName, TEST_NUMBER_OF_RESULTS_14)
}
