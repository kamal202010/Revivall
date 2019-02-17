package com.khalaf.kamal.revival.helper;
/*
 *
 * Created by Kamal Khalaf on 2/17/2019.
 * Contact : kamal.khalaf56@gmail.com
 *
 */

import android.app.Application;
import android.content.Context;

import com.khalaf.kamal.revival.service.webApi.ApiClient;
import com.khalaf.kamal.revival.service.webApi.ApiInterface;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class RevivalApplication extends Application {


    private ApiInterface apiInterface;
    private Scheduler scheduler;
    private static RevivalApplication mInstance;

    private static RevivalApplication get(Context context) {
        return (RevivalApplication) context.getApplicationContext();
    }

    public static RevivalApplication create(Context context) {
        return RevivalApplication.get(context);
    }

    public ApiInterface getApiInterface() {
        if (apiInterface == null) {
            apiInterface = ApiClient.create();
        }

        return apiInterface;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized RevivalApplication getInstance() {

        return mInstance;

    }
}
