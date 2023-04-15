package com.javacourse.oc.maru;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
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
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FilterByDateTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void filterByDateTest() {
        ViewInteraction textView = onView(
                allOf(withId(R.id.item_list_meeting_name), withText("10-05-2023 - Salle 1 - 10:00"),
                        withParent(withParent(withId(R.id.activity_list_user_rv))),
                        isDisplayed()));
        textView.check(matches(withText("10-05-2023 - Salle 1 - 10:00")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.item_list_meeting_name), withText("12-05-2023 - Salle 2 - 11:30"),
                        withParent(withParent(withId(R.id.activity_list_user_rv))),
                        isDisplayed()));
        textView2.check(matches(withText("12-05-2023 - Salle 2 - 11:30")));

        ViewInteraction overflowMenuButton = onView(
                allOf(withContentDescription("Plus d'options"),
                        childAtPosition(
                                childAtPosition(
                                        withId(com.google.android.material.R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()));
        overflowMenuButton.perform(click());

        ViewInteraction materialTextView = onView(
                allOf(withId(androidx.transition.R.id.title), withText("Filter par date"),
                        childAtPosition(
                                childAtPosition(
                                        withId(com.google.android.material.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialTextView.perform(click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withClassName(is("androidx.appcompat.widget.AppCompatImageButton")), withContentDescription("Mois suivant"),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.DayPickerView")),
                                        childAtPosition(
                                                withClassName(is("com.android.internal.widget.DialogViewAnimator")),
                                                0)),
                                2)));
        appCompatImageButton.perform(scrollTo(), click());

        ViewInteraction materialButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton.perform(scrollTo(), click());

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.item_list_meeting_name), withText("10-05-2023 - Salle 1 - 10:00"),
                        withParent(withParent(withId(R.id.activity_list_user_rv))),
                        isDisplayed()));
        textView3.check(matches(withText("10-05-2023 - Salle 1 - 10:00")));
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
