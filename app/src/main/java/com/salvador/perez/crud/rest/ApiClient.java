package com.salvador.perez.crud.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Salva on 09/01/2019.
 */

public class ApiClient {

    public static final int METHOD_NOT_ALLOWED = 405;
    public static final int SERVER_ERROR = 500;

    public static final String BASE_URL = "http://hello-world.innocv.com/api/user/";

    public static Retrofit getClient() {
        Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        return retrofit;
    }
}
