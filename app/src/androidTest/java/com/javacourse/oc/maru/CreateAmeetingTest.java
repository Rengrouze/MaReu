package com.javacourse.oc.maru;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
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
public class CreateAmeetingTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void createAmeetingTest() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.item_list_meeting_delete_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_list_user_rv),
                                        0),
                                2),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withId(R.id.item_list_meeting_delete_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_list_user_rv),
                                        0),
                                2),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withId(R.id.item_list_meeting_delete_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_list_user_rv),
                                        0),
                                2),
                        isDisplayed()));
        appCompatImageButton3.perform(click());

        ViewInteraction appCompatImageButton4 = onView(
                allOf(withId(R.id.item_list_meeting_delete_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_list_user_rv),
                                        0),
                                2),
                        isDisplayed()));
        appCompatImageButton4.perform(click());

        ViewInteraction appCompatImageButton5 = onView(
                allOf(withId(R.id.item_list_meeting_delete_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_list_user_rv),
                                        0),
                                2),
                        isDisplayed()));
        appCompatImageButton5.perform(click());

        ViewInteraction appCompatImageButton6 = onView(
                allOf(withId(R.id.item_list_meeting_delete_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_list_user_rv),
                                        0),
                                2),
                        isDisplayed()));
        appCompatImageButton6.perform(click());

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.activity_list_user_fab),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction materialAutoCompleteTextView = onView(
                allOf(withId(R.id.room_Picker),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.roomPickerLayout),
                                        0),
                                0)));
        materialAutoCompleteTextView.perform(scrollTo(), click());

        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(0);
        appCompatCheckedTextView.perform(click());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.datePickerButton), withText("Choisissez une date"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        materialButton.perform(scrollTo(), click());

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

        ViewInteraction materialButton4 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton4.perform(scrollTo(), click());

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.theme_picker_edittext),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0)));
        textInputEditText.perform(scrollTo(), replaceText("Test"), closeSoftKeyboard());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.peoples_edittext),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0)));
        textInputEditText2.perform(scrollTo(), replaceText("Elias"), closeSoftKeyboard());

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.peoples_edittext), withText("Elias"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.textfield.TextInputLayout")),
                                        0),
                                0)));
        textInputEditText3.perform(pressImeActionButton());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.addMeetingButton), withText("Cr�er une r�union"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                7)));
        materialButton5.perform(scrollTo(), click());

        ViewInteraction viewGroup = onView(
                allOf(withParent(allOf(withId(R.id.activity_list_user_rv),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        viewGroup.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.item_list_meeting_name), withText("20-04-2023 - Salle 1 - 12:00"),
                        withParent(withParent(withId(R.id.activity_list_user_rv))),
                        isDisplayed()));
        textView.check(matches(withText("20-04-2023 - Salle 1 - 12:00")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.item_list_meeting_theme), withText("Test"),
                        withParent(withParent(withId(R.id.activity_list_user_rv))),
                        isDisplayed()));
        textView2.check(matches(withText("Test")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.item_list_meeting_people), withText("Elias"),
                        withParent(withParent(withId(R.id.activity_list_user_rv))),
                        isDisplayed()));
        textView3.check(matches(withText("Elias")));
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
