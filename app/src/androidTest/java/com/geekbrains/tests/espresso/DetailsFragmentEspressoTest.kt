package com.geekbrains.tests.espresso

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.geekbrains.tests.R
import com.geekbrains.tests.TEST_NUMBER_OF_RESULTS_PLUS_1
import com.geekbrains.tests.view.details.DetailsFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailsFragmentEspressoTest {
    private lateinit var scenario: FragmentScenario<DetailsFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer()
    }

    @Test
    fun fragment_testBundle() {
        val fragmentArgs = bundleOf("TOTAL_COUNT_EXTRA" to 2)
        val scenario = launchFragmentInContainer<DetailsFragment>(fragmentArgs)
        scenario.moveToState(Lifecycle.State.RESUMED)
        val assertion = ViewAssertions.matches(ViewMatchers.withText("Number of results: 2"))
        Espresso.onView(ViewMatchers.withId(R.id.totalCountTextViewDetails)).check(assertion)
    }

    @Test
    fun fragment_testSetCountMethod() {
        scenario.onFragment { fragment ->
            fragment.setCount(12)
        }
        val assertion = ViewAssertions.matches(ViewMatchers.withText("Number of results: 12"))
        Espresso.onView(ViewMatchers.withId(R.id.totalCountTextViewDetails)).check(assertion)
    }

    @Test
    fun fragment_testIncrementButton() {
        Espresso.onView(ViewMatchers.withId(R.id.incrementButton)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.totalCountTextViewDetails))
            .check(ViewAssertions.matches(ViewMatchers.withText(TEST_NUMBER_OF_RESULTS_PLUS_1)))
    }
}
