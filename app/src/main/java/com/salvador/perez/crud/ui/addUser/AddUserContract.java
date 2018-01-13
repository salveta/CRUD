package com.salvador.perez.crud.ui.addUser;

import com.salvador.perez.crud.model.User;

import org.joda.time.DateTime;

/**
 * Created by Salva on 12/01/2018.
 */

public interface AddUserContract {

    interface view{
        void saveUserSuccess();
        void showErrorBirthday();
        void showErrorUserName();
        void showErrorCreateUser();
        void showErrorCode();
    }

    interface presenter{
        void saveUser(String name, DateTime userBitthday);
    }

    interface model{
        interface onCreateUser {
            void onSuccess(User user);
            void onFailure(String error);
            void onErrorCode(int error);
        }

        void createUser(User user, onCreateUser listener);
    }
}
