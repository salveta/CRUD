package com.salvador.perez.crud.ui.addUser;

import android.util.Log;

import com.salvador.perez.crud.ApplicationConfig;
import com.salvador.perez.crud.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Salva on 12/01/2018.
 */

public class AddUserModel implements AddUserContract.model{

    private Call mCall;

    @Override
    public void createUser(User user, onCreateUser listener) {
        mCall = ApplicationConfig.getApiService().createUser(user);
        Log.e("AddUserModel", mCall.request().url().toString());
        mCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(response.isSuccessful()) {
                    listener.onSuccess(response.body());
                }else{
                    listener.onErrorCode(response.code());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onFailure(t.toString());
            }
        });
    }
}
