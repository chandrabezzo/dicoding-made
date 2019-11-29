package com.bezzo.moviecatalogue

import android.view.View
import androidx.test.espresso.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.bezzo.moviecatalogue.features.main.MainActivity
import com.bezzo.moviecatalogue.util.EspressoIdlingResource
import com.google.android.material.tabs.TabLayout
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule @JvmField
    val rule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource)
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource)
    }

    @Test
    fun getMovie(){
        onView(withId(R.id.bnv_main)).check(matches(isDisplayed()))
        onView(withId(R.id.tl_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.nav_movie)).check(matches(isSelected()))
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).check(RecyclerViewItemCountAssertion(20))
    }

    @Test
    fun getTvShow(){

        onView(withId(R.id.bnv_main)).check(matches(isDisplayed()))
        onView(withId(R.id.tl_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.nav_movie)).check(matches(isSelected()))
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).check(RecyclerViewItemCountAssertion(20))
        onView(withId(R.id.tl_movie)).perform(selectTabAtPosition(1))

        onView(withId(R.id.bnv_main)).check(matches(isDisplayed()))
        onView(withId(R.id.tl_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.nav_movie)).check(matches(isSelected()))
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).check(RecyclerViewItemCountAssertion(20))
    }

    @Test
    fun gotoAbout(){
        onView(withId(R.id.bnv_main)).check(matches(isDisplayed()))
        onView(withId(R.id.tl_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.nav_movie)).check(matches(isSelected()))
        onView(withId(R.id.nav_about)).check(matches(isDisplayed()))
        onView(withId(R.id.nav_about)).perform(click())
        onView(withId(R.id.nav_about)).check(matches(isSelected()))
    }

    private fun selectTabAtPosition(tabIndex: Int): ViewAction {
        return object : ViewAction {
            override fun getDescription() = "with tab at index $tabIndex"

            override fun getConstraints() = allOf(isDisplayed(), isAssignableFrom(TabLayout::class.java))

            override fun perform(uiController: UiController, view: View) {
                val tabLayout = view as TabLayout
                val tabAtIndex: TabLayout.Tab = tabLayout.getTabAt(tabIndex)
                    ?: throw PerformException.Builder()
                        .withCause(Throwable("No tab at index $tabIndex"))
                        .build()

                tabAtIndex.select()
            }
        }
    }
}