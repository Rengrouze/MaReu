package com.javacourse.oc.maru;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.DataInteraction;
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
public class FilterByRoom {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void filterByRoom() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.activity_list_user_rv),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        recyclerView.check(matches(isDisplayed()));

        recyclerView.check((view, noViewFoundException) -> {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }

            RecyclerView rv = (RecyclerView) view;
            RecyclerView.Adapter adapter = rv.getAdapter();
            assertEquals(6,adapter.getItemCount());
        });

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
                allOf(withId(androidx.transition.R.id.title), withText("Filter par salle"),
                        childAtPosition(
                                childAtPosition(
                                        withId(com.google.android.material.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialTextView.perform(click());

        DataInteraction materialTextView2 = onData(anything())
                .inAdapterView(allOf(withClassName(is("com.android.internal.app.AlertController$RecycleListView")),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)))
                .atPosition(0);
        materialTextView2.perform(click());

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.activity_list_user_rv),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        recyclerView2.check(matches(isDisplayed()));

        recyclerView.check((view, noViewFoundException) -> {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }

            RecyclerView rv = (RecyclerView) view;
            RecyclerView.Adapter adapter = rv.getAdapter();
            assertEquals(2,adapter.getItemCount());
        });
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
