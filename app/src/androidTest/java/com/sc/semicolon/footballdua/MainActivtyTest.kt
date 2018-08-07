package com.sc.semicolon.footballdua


import android.support.test.espresso.Espresso.*
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.core.IsInstanceOf
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import com.sc.semicolon.footballdua.Adapter.NextAdapter
import com.sc.semicolon.footballdua.Adapter.PrevAdapter
import org.hamcrest.Matchers.*

/**
 * Created by cis on 06/08/2018.
 */
@RunWith(AndroidJUnit4::class)
class MainActivtyTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)
    @Test
    fun testAppBehaviour() {
        val bottomNavigationItemView = onView(
                allOf(withId(R.id.navigation_home),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.navigation),
                                        0),
                                0),
                        isDisplayed()))
        bottomNavigationItemView.perform(click())

        val recyclerView = onView(
                allOf(withId(R.id.rv),
                        childAtPosition(
                                withClassName(`is`("android.widget.LinearLayout")),
                                0)))
        recyclerView.perform(actionOnItemAtPosition<PrevAdapter.ViewHolder>(1, click()))

        val actionMenuItemView = onView(
                allOf(withId(R.id.add_to_favorite), withContentDescription("Favorite"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()))
        actionMenuItemView.perform(click())

        pressBack()

        val bottomNavigationItemView2 = onView(
                allOf(withId(R.id.navigation_dashboard),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.navigation),
                                        0),
                                1),
                        isDisplayed()))
        bottomNavigationItemView2.perform(click())

        val recyclerView2 = onView(
                allOf(withId(R.id.rv),
                        childAtPosition(
                                withClassName(`is`("android.widget.FrameLayout")),
                                0)))
        recyclerView2.perform(actionOnItemAtPosition<NextAdapter.ViewHolder>(0, click()))

        val actionMenuItemView2 = onView(
                allOf(withId(R.id.add_to_favorite), withContentDescription("Favorite"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()))
        actionMenuItemView2.perform(click())

        pressBack()

        val bottomNavigationItemView3 = onView(
                allOf(withId(R.id.navigation_favorite),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.navigation),
                                        0),
                                2),
                        isDisplayed()))
        bottomNavigationItemView3.perform(click())

        pressBack()

    }


    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return (parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position))
            }
        }
    }
}