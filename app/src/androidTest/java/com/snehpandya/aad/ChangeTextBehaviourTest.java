package com.snehpandya.aad;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.snehpandya.aad.activity.SecondActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by sneh.pandya on 26/09/17.
 */

@RunWith(AndroidJUnit4.class)
public class ChangeTextBehaviourTest {

    private String mString;

    @Rule
    public ActivityTestRule<SecondActivity> mActivityTestRule = new ActivityTestRule<>(SecondActivity.class);

    @Before
    public void initValidString() {
        mString = "Espresso";
    }

    @Test
    public void changeText_sameActivity() {
        onView(withId(R.id.edittext_name))
                .perform(typeText(mString), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.btn_second)).perform(click());

        onView(withId(R.id.text_name_second))
                .check(matches(withText(mString)));
    }
}
