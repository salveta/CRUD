package com.salvador.perez.crud.ui.userDetail;

import com.salvador.perez.crud.commons.Event;
import com.salvador.perez.crud.model.User;
import com.salvador.perez.crud.rest.ApiClient;

import de.greenrobot.event.EventBus;

/**
 * Created by Salva on 13/01/2018.
 */

public class DetailUserPresenter implements DetailUserContract.presenter, DetailUserContract.model.onGetUser, DetailUserContract.model.onUpdateUser, DetailUserContract.model.onDeleteUser{

    private DetailUserModel model;
    private DetailUserActivity view;

    DetailUserPresenter(DetailUserActivity view, DetailUserModel model){
        this.view = view;
        this.model = model;
    }

    @Override
    public void getUser(int id) {
        model.onGetUser(id, this);
    }

    @Override
    public void updateUser(String name, String birthday) {
        User user = new User(name, birthday);
        model.onUpdateUser(user, this);
    }

    @Override
    public void deleteUser(int id) {
        model.onDeleteUser(id, this);
    }

    @Override
    public void onSuccess(User user) {
        if(user != null) {
            view.setUserBirthday(user.getBirthdate().substring(0, user.getBirthdate().indexOf("T")));
            view.setUserName(user.getName());
        }
    }

    @Override
    public void onSuccessDelete(int id) {
        EventBus.getDefault().post(new Event(id, Event.REMOVE_USER_LIST));
        view.deleteUserDone();
    }

    @Override
    public void onFailure(String error) {
        view.errorLoadUser();
    }

    @Override
    public void onErrorCode(int error) {
        switch(error) {
            case ApiClient.METHOD_NOT_ALLOWED :
                view.showErrorMethodNotAllowed();
                break;
            case ApiClient.SERVER_ERROR :
                view.showErrorMethodNotAllowed();
                break;
        }
    }
}
