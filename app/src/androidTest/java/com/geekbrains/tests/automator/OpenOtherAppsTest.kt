package com.geekbrains.tests.automator

import android.widget.TextView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiScrollable
import androidx.test.uiautomator.UiSelector
import com.geekbrains.tests.PACKAGE_NAME
import com.geekbrains.tests.SETTINGS_NAME
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class OpenOtherAppsTest {
    private val uiDevice: UiDevice = UiDevice.getInstance(getInstrumentation())

    @Test
    fun test_OpenSettings() {
        uiDevice.pressHome()

        //!!! for Pixel 5 !!!
        uiDevice.swipe(500, 2200, 500, 0, 4)

        val appViews = UiScrollable(UiSelector().scrollable(false))
        val settingsApp = appViews
            .getChildByText(
                UiSelector()
                    .className(TextView::class.java.name),
                SETTINGS_NAME
            )
        settingsApp.clickAndWaitForNewWindow()

        val settingsValidation =
            uiDevice.findObject(UiSelector().packageName(PACKAGE_NAME))
        Assert.assertTrue(settingsValidation.exists())
    }
}
