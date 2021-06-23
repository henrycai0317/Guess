package com.henry.guessnumber


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MaterialActivityTest{

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule<MaterialActivity>(MaterialActivity::class.java)

    @Test
    fun guessWrong(){
        val secrete = activityTestRule.activity.secreteNumber.secrete
        val resource = activityTestRule.activity.resources

        for(n in 1..10){

            if(n != secrete){
            onView(withId(R.id.number)).perform(clearText())
            onView(withId(R.id.number)).perform(typeText(n.toString()))
            onView(withId(R.id.ok_button)).perform(click())
            val meassage =
                    if( n < secrete )  resource.getString(R.string.bigger)
                    else
                        resource.getString(R.string.smaller)
            onView(withText(meassage)).check(matches(isDisplayed()))
            onView(withText(resource.getString(R.string.ok))).perform(click())

            }

        }

    }

}