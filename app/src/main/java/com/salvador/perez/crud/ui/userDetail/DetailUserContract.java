package com.salvador.perez.crud.ui.userDetail;

import com.salvador.perez.crud.model.User;

/**
 * Created by Salva on 13/01/2018.
 */

public interface DetailUserContract {

    interface view{
        void setUserName(String name);
        void setUserBirthday(String birthday);
        void errorLoadUser();
        void deleteUserDone();
        void showErrorMethodNotAllowed();
    }

    interface presenter{
        void getUser(int id);
        void updateUser(String name, String birthday);
        void deleteUser(int id);
    }

    interface model{
        interface onGetUser {
            void onSuccess(User user);
            void onFailure(String error);
        }

        interface onUpdateUser {
            void onSuccess(User user);
            void onFailure(String error);
            void onErrorCode(int error);
        }

        interface onDeleteUser {
            void onSuccessDelete(int id);
            void onFailure(String error);
            void onErrorCode(int error);
        }

        void onGetUser(int userId, onGetUser listener);
        void onUpdateUser(User user, onUpdateUser listener);
        void onDeleteUser(int userId, onDeleteUser listener);
    }
}
