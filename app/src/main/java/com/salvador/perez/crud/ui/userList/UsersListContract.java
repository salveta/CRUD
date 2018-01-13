package com.salvador.perez.crud.ui.userList;

import com.salvador.perez.crud.model.User;

import java.util.List;

/**
 * Created by Salva on 09/01/2018.
 */

public interface UsersListContract {

    interface view {
        void onGetUsers(List<User> users);
        void showLoader();
        void hideLoader();
        void showEmptyScreen();
        void openUserDetail(int userId);
    }

    interface presenter{
        void getUsers();
        void openDetail(int position);
    }

    interface model{
        interface OnGetUsers {
            void onSuccess(List<User> users);
            void onFailure(String error);
        }

        void getUsers(OnGetUsers listener);
    }
}
