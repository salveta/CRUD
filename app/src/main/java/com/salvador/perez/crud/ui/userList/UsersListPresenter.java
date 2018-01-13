package com.salvador.perez.crud.ui.userList;

import com.salvador.perez.crud.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Salva on 09/01/2018.
 */

public class UsersListPresenter implements UsersListContract.presenter, UsersListContract.model.OnGetUsers{

    private UsersListModel model;
    private UsersListActivity view;
    private List<User> mUser = new ArrayList<>();

    public UsersListPresenter(UsersListActivity view, UsersListModel model){
        this.view = view;
        this.model = model;
    }

    @Override
    public void openDetail(int position) {
        view.openUserDetail(mUser.get(position).getId());
    }

    @Override
    public void getUsers() {
        view.showLoader();
        model.getUsers(this);
    }

    @Override
    public void onSuccess(List<User> users) {

        for (User message : users) {
            mUser.add(message);
        }

        view.hideLoader();
        view.onGetUsers(mUser);
    }

    @Override
    public void onFailure(String error) {
        view.hideLoader();
        view.showEmptyScreen();
    }


}
