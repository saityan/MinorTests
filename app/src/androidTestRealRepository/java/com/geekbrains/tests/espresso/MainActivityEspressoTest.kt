package com.geekbrains.tests.espresso

import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.geekbrains.tests.R
import com.geekbrains.tests.view.search.MainActivity
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityEspressoTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun activity_IsResumed() {
        TestCase.assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }

    @Test
    fun activityTextView_NotNull() {
        scenario.onActivity {
            val totalCountTextView = it.findViewById<TextView>(R.id.totalCountTextViewDetails)
            TestCase.assertNotNull(totalCountTextView)
        }
    }

    @Test
    fun activityEditText_NotNull() {
        scenario.onActivity {
            val searchEditText = it.findViewById<TextView>(R.id.searchEditText)
            TestCase.assertNotNull(searchEditText)
        }
    }

    @Test
    fun activityButton_NotNull() {
        scenario.onActivity {
            val toDetailsActivityButton = it.findViewById<TextView>(R.id.toDetailsActivityButton)
            TestCase.assertNotNull(toDetailsActivityButton)
        }
    }

    @Test
    fun activityTextView_HasText() {
        val assertion = matches(withText("TO DETAILS"))
        onView(withId(R.id.toDetailsActivityButton)).check(assertion)
    }

    @Test
    fun activityEditText_HasText() {
        val assertion = matches(withText(""))
        onView(withId(R.id.searchEditText)).check(assertion)
    }

    @After
    fun close() {
        scenario.close()
    }
}
