package com.salvador.perez.crud.ui.addUser;

import com.salvador.perez.crud.commons.Event;
import com.salvador.perez.crud.model.User;
import com.salvador.perez.crud.rest.ApiClient;
import com.salvador.perez.crud.utils.StringUtil;

import org.joda.time.DateTime;

import de.greenrobot.event.EventBus;

/**
 * Created by Salva on 12/01/2018.
 */

public class AddUserPresenter implements AddUserContract.presenter, AddUserContract.model.onCreateUser {

    private AddUserActivity view;
    private AddUserModel model;

    AddUserPresenter(AddUserActivity view, AddUserModel model){
        this.view = view;
        this.model = model;
    }

    @Override
    public void saveUser(String name, DateTime userBirthday) {

        if(StringUtil.isNull(name)){
            view.showErrorUserName();
            return;
        }

        if(userBirthday == null){
            view.showErrorBirthday();
            return;
        }

        User user = new User(name, dateTimeToString(userBirthday));
        model.createUser(user, this);
    }

    private String dateTimeToString(DateTime birthday){
        return birthday.toString("yyyy-MM-dd'T'HH:mm:ss'Z'");
    }

    @Override
    public void onSuccess(User user) {
        EventBus.getDefault().post(new Event(user, Event.UPDATE_USER_LIST));
        view.saveUserSuccess();
    }

    @Override
    public void onFailure(String error) {
        view.showErrorCreateUser();
    }

    @Override
    public void onErrorCode(int error) {
        switch(error) {
            case ApiClient.METHOD_NOT_ALLOWED :
                view.showErrorCode();
                break;
        }
    }
}
