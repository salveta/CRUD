package userList;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import com.salvador.perez.crud.R;
import com.salvador.perez.crud.ui.userList.UsersListActivity;

import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by Salva on 13/01/2018.
 */
public class UsersListActivityTest {

    public static final String USER_NAME = "Harry James";

    @Rule
    public final ActivityTestRule mActivityRule = new ActivityTestRule<>(UsersListActivity.class);


    @Test
    public void testExpectedItem()  throws IOException {
        onView(withId(R.id.rv_list_users)).check(matches(isDisplayed()));
        onView(allOf(isDescendantOfA(withId(R.id.rv_list_users)), withText(is(USER_NAME))));
    }

    @Test
    public void userClickToOpenDetail() {
        onView(withId(R.id.rv_list_users))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    @Test
    public void fabIsDisplayed() {
        onView(withId(R.id.fab_add_user)).check(matches(isDisplayed()));
    }

    @Test
    public void fabOpenAddActivity() {
        onView(withId(R.id.fab_add_user)).perform(click());
    }

}