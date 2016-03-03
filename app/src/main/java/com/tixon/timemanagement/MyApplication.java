package com.tixon.timemanagement;

import android.app.Application;

import com.tixon.timemanagement.database.HelperFactory;

/**
 * Created by tikhon on 27.02.16.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        HelperFactory.setHelper(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        HelperFactory.releaseHelper();
        super.onTerminate();
    }
}
