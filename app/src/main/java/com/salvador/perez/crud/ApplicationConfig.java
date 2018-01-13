package com.salvador.perez.crud;

import android.app.Application;

import com.salvador.perez.crud.rest.ApiClient;
import com.salvador.perez.crud.rest.ApiInterface;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by Salva on 16/10/2017.
 */

public class ApplicationConfig extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
    }

    public static ApiInterface getApiService(){
        return ApiClient.getClient().create(ApiInterface.class);
    }
}
