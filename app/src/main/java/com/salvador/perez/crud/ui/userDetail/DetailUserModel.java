package com.salvador.perez.crud.ui.userDetail;

import com.salvador.perez.crud.ApplicationConfig;
import com.salvador.perez.crud.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Salva on 13/01/2018.
 */

public class DetailUserModel implements DetailUserContract.model {

    private Call<User> mCall;

    @Override
    public void onGetUser(int userId, onGetUser listener) {
        mCall = ApplicationConfig.getApiService().getUser(userId);
        mCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.isSuccessful()) {
                    listener.onSuccess(response.body());
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onFailure(t.toString());
            }
        });
    }

    @Override
    public void onUpdateUser(User user, onUpdateUser listener) {
        mCall = ApplicationConfig.getApiService().updateUser(user);
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

    @Override
    public void onDeleteUser(int userId, onDeleteUser listener) {
        mCall = ApplicationConfig.getApiService().removeUser(userId);
        mCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.isSuccessful()) {
                    listener.onSuccessDelete(response.body().getId());
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
