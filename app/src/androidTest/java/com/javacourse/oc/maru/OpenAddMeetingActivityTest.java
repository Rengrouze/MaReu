package com.javacourse.oc.maru;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class OpenAddMeetingActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void openAddMeetingActivityTest() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.activity_list_user_fab),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction linearLayout = onView(
                allOf(withParent(withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class))),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

        ViewInteraction spinner = onView(
                allOf(withId(R.id.room_Picker), withText("Salle"),
                        withParent(withParent(withId(R.id.roomPickerLayout))),
                        isDisplayed()));
        spinner.check(matches(isDisplayed()));

        ViewInteraction editText = onView(
                allOf(withId(R.id.date_picker_edittext), withText("Date"),
                        withParent(withParent(withId(R.id.datePickerLayout))),
                        isDisplayed()));
        editText.check(matches(isDisplayed()));

        ViewInteraction button = onView(
                allOf(withId(R.id.datePickerButton), withText("CHOISISSEZ UNE DATE"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.time_picker_edittext), withText("Heure de la r�union"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        editText2.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.timePickerButton), withText("CHOISISSEZ UNE HORAIRE"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.theme_picker_edittext), withText("Th�me de la r�union"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        editText3.check(matches(isDisplayed()));

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.peoples_edittext), withText("Participants, s�par�s par une virgule"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        editText4.check(matches(isDisplayed()));

        ViewInteraction button3 = onView(
                allOf(withId(R.id.addMeetingButton), withText("CR�ER UNE R�UNION"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        button3.check(matches(isDisplayed()));

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.datePickerButton), withText("Choisissez une date"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        materialButton.perform(scrollTo(), click());

        ViewInteraction linearLayout2 = onView(
                allOf(withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class)),
                        isDisplayed()));
        linearLayout2.check(matches(isDisplayed()));

        ViewInteraction materialButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton2.perform(scrollTo(), click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.timePickerButton), withText("Choisissez une horaire"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                4)));
        materialButton3.perform(scrollTo(), click());

        ViewInteraction linearLayout3 = onView(
                allOf(withParent(allOf(IsInstanceOf.<View>instanceOf(android.widget.TimePicker.class),
                                withParent(withId(android.R.id.custom)))),
                        isDisplayed()));
        linearLayout3.check(matches(isDisplayed()));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
