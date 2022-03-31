package com.geekbrains.tests.automator

import android.content.Context
import android.content.Intent
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import com.geekbrains.tests.TIMEOUT
import org.junit.Assert

fun setupBehavior(uiDevice: UiDevice, context: Context, packageName: String) {
    uiDevice.pressHome()
    val intent = context.packageManager.getLaunchIntentForPackage(packageName)
    intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
    context.startActivity(intent)
    uiDevice.wait(Until.hasObject(By.pkg(packageName).depth(0)), TIMEOUT)
}

fun test_MainActivityIsStarted_Behavior(uiDevice: UiDevice, packageName: String) {
    val editText = uiDevice.findObject(By.res(packageName, "searchEditText"))
    Assert.assertNotNull(editText)
}

fun test_SearchIsPositive_Behavior(
    uiDevice: UiDevice,
    packageName: String,
    NUMBER_OF_RESULTS: String
) {
    val editText = uiDevice.findObject(By.res(packageName, "searchEditText"))
    editText.text = "saityan"
    val searchButton = uiDevice.findObject(By.res(packageName, "searchRepositoryButton"))
    searchButton.click()
    val changedText =
        uiDevice.wait(
            Until.findObject(By.res(packageName, "totalCountTextView")),
            TIMEOUT
        )
    Assert.assertEquals(changedText.text.toString(), NUMBER_OF_RESULTS)
}
