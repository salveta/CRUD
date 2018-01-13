package userList;

import android.support.test.rule.ActivityTestRule;

import com.salvador.perez.crud.ui.userList.UsersListActivity;
import com.salvador.perez.crud.ui.userList.UsersListModel;
import com.salvador.perez.crud.ui.userList.UsersListPresenter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

/**
 * Created by Salva on 13/01/2018.
 */

public class UserListPresenterTest {

    @Mock
    private UsersListPresenter presenter;

    @Mock
    private UsersListActivity view;

    @Rule
    public final ActivityTestRule mActivityRule = new ActivityTestRule<>(UsersListActivity.class);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new UsersListPresenter(view, new UsersListModel());
    }

    @Test
    public void checkLoaingEnableWhenGetUser(){
        presenter.getUsers();
        verify(view).showLoader();
    }

    @Test
    public void showErrors(){
        presenter.onFailure("");
        verify(view).hideLoader();
        verify(view).showEmptyScreen();
    }
}
