package com.salvador.perez.crud.rest;


import com.salvador.perez.crud.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Salva on 09/01/2019.
 */

public interface ApiInterface {

    @GET("getall")
    Call <List<User>> getUserList();

    @GET("get/{userId}")
    Call <User> getUser(@Path("userId") int userId);

    @POST("create")
    Call <User> createUser(@Body User user);

    @POST("update")
    Call <User> updateUser(@Body User user);

    @GET("remove/{userId}")
    Call <User> removeUser(@Path("userId") int userId);
}
