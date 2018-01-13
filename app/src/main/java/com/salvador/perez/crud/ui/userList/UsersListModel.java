package com.salvador.perez.crud.ui.userList;

import com.salvador.perez.crud.ApplicationConfig;
import com.salvador.perez.crud.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Salva on 09/01/2018.
 */

public class UsersListModel implements UsersListContract.model {

    private Call<List<User>> mCall;

    @Override
    public void getUsers(final OnGetUsers listener) {
        mCall = ApplicationConfig.getApiService().getUserList();
        mCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                listener.onFailure(t.toString());
            }
        });
    }
}
