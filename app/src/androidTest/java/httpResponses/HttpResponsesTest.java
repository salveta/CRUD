package httpResponses;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.test.InstrumentationTestCase;

import com.salvador.perez.crud.R;
import com.salvador.perez.crud.ui.userList.UsersListActivity;
import com.salvador.perez.crud.ui.userList.UsersListModel;
import com.salvador.perez.crud.ui.userList.UsersListPresenter;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Mockito.verify;


/**
 * Created by Salva on 13/01/2018.
 */

public class HttpResponsesTest extends InstrumentationTestCase {

    UsersListPresenter presenter;

    @Mock
    UsersListActivity view;

    @Mock
    UsersListModel model;

    @Rule
    public ActivityTestRule<UsersListActivity> mActivityRule = new ActivityTestRule<>(UsersListActivity.class, true, false);
    private MockWebServer server;
    private String url = "http://hello-world.innocv.com/api/user/";

    @Before
    public void setUp() throws Exception {
        super.setUp();
        MockitoAnnotations.initMocks(this);
        presenter = new UsersListPresenter(view, model);
        server = new MockWebServer();
        server.start();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        url = server.url("/").toString();
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    public void testQuoteIsShown() throws Exception {
        String fileName = "ok_response.json";
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(RestServiceTestHelper.getStringFromFile(getInstrumentation().getContext(), fileName)));

        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);

        onView(withId(R.id.rv_list_users)).check(matches(isDisplayed()));
    }

    @Test
    public void testRetryButtonShowsWhenError() throws Exception {
        String fileName = "405_not_allowed.json";

        server.enqueue(new MockResponse()
                .setResponseCode(405)
                .setBody(RestServiceTestHelper.getStringFromFile(getInstrumentation().getContext(), fileName)));

        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);

        presenter.onFailure("");
        verify(view).hideLoader();
        verify(view).showEmptyScreen();
    }
}
