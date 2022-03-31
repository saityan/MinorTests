package com.geekbrains.tests.automator

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until
import com.geekbrains.tests.TEST_NUMBER_OF_RESULTS_2
import com.geekbrains.tests.TIMEOUT
import org.junit.Assert
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
        test_SearchIsPositive_Behavior(uiDevice, packageName, TEST_NUMBER_OF_RESULTS_2)

    @Test
    fun test_OpenDetailsScreenWithSearchResults() {
        val editText = uiDevice.findObject(By.res(packageName, "searchEditText"))
        editText.text = "saityan"
        val searchButton = uiDevice.findObject(By.res(packageName, "searchRepositoryButton"))
        searchButton.click()
        val mainText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountTextView")),
                TIMEOUT
            ).text
        val toDetails: UiObject2 = uiDevice.findObject(
            By.res(
                packageName,
                "toDetailsActivityButton"
            )
        )
        toDetails.click()
        val detailsText =
            uiDevice.wait(
                Until.findObject(By.res(packageName, "totalCountTextViewDetails")),
                TIMEOUT
            ).text
        Assert.assertEquals(mainText, detailsText)
    }
}
