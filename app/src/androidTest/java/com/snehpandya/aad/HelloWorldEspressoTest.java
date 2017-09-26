package com.snehpandya.aad;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.snehpandya.aad.activity.SecondActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by sneh.pandya on 26/09/17.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class HelloWorldEspressoTest {

    @Rule
    public ActivityTestRule<SecondActivity> mActivityTestRule = new ActivityTestRule<>(SecondActivity.class);

    @Test
    public void listGoesOverTheFold() {
        onView(withId(R.id.text_hello)).check(matches(withText("Hello World!")));
    }
}
