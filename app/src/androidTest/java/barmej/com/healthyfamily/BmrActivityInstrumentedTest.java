package barmej.com.healthyfamily;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class BmrActivityInstrumentedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void uitest_add_user() {
        onView(withId(R.id.chooseUserLinearLayout)).perform(click());

        try {
            onView(withId(R.id.floatingActionButton)).perform(click());

        } catch (NoMatchingViewException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.nameEditText)).perform(typeText("Abdullah"), closeSoftKeyboard());
        onView(withId(R.id.weightEditText)).perform(typeText("75"), closeSoftKeyboard());
        onView(withId(R.id.heightEditText)).perform(typeText("170"), closeSoftKeyboard());
        onView(withId(R.id.ageEditText)).perform(typeText("25"), closeSoftKeyboard());
        onView(withId(R.id.maleRadioButton)).perform(click());
        onView(withId(R.id.button)).perform(click());

    }

    @Test
    public void uitest_check_bmr_textviews() {
        onView(withId(R.id.chooseUserLinearLayout)).perform(click());

        onView(withId(R.id.usersRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.bmrView)).perform(click());

        onView(withId(R.id.kcalTextView)).check(matches(withText("1692.50")));
        onView(withId(R.id.tiTextView)).check(matches(withText("2589.53")));
    }

    @Test
    public void uitest_change_active_type() {
        onView(withId(R.id.chooseUserLinearLayout)).perform(click());

        onView(withId(R.id.usersRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.bmrView)).perform(click());

        onView(withId(R.id.activeRadioButton)).perform(click());
        onView(withId(R.id.tiTextView)).check(matches(withText("2978.80")));

        onView(withId(R.id.vigorouslyRadioButton)).perform(click());
        onView(withId(R.id.tiTextView)).check(matches(withText("3808.13")));

    }

}
