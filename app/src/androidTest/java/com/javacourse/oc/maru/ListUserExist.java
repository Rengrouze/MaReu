package com.javacourse.oc.maru;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.recyclerview.widget.RecyclerView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test instrumenté pour vérifier si une liste d'utilisateurs existe et a le nombre d'éléments attendu.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ListUserExist {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    /**
     * Vérifie si la liste d'utilisateurs existe et a le nombre d'éléments attendu.
     */
    @Test
    public void listUserExist() {
        // Vérifie si le RecyclerView est affiché à l'écran
        ViewInteraction recyclerView = Espresso.onView(
                allOf(withId(R.id.activity_list_user_rv),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        recyclerView.check(matches(isDisplayed()));

        // Vérifie si le RecyclerView a le nombre d'éléments attendu
        int expectedItemCount = 6; // Remplacez par le nombre d'éléments attendu dans la liste
        recyclerView.check((view, noViewFoundException) -> {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }

            RecyclerView rv = (RecyclerView) view;
            RecyclerView.Adapter adapter = rv.getAdapter();
            assertThat(adapter.getItemCount(), is(expectedItemCount));
        });
    }
}
