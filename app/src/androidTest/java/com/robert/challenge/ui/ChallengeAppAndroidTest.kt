package com.robert.challenge.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.rememberNavController
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.robert.challenge.MainActivity
import com.robert.challenge.R
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ChallengeAppAndroidTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testNavigationBetweenHomeAndDetailScreens() {
        // Verify that HomeScreen is displayed
        onView(withText(Routes.HOME.name)).check(matches(isDisplayed()))


//        type in search bar "car"
        // Perform actions on the TextField
         composeTestRule.onNodeWithContentDescription("Search").performTextInput("car")

//        check if lazy vertical grid is displayed with text "car"
//        composeTestRule.onNodeWithText("car").assertIsDisplayed()

        // Verify that DetailScreen is displayed
//        onView(withText(Routes.DETAILS.name)).check(matches(isDisplayed()))

        // Perform back navigation
//        Espresso.pressBack()

        // Verify that HomeScreen is displayed again
//        onView(withText(Routes.HOME.name)).check(matches(isDisplayed()))
    }
}

enum class Routes{
    HOME, DETAILS
}