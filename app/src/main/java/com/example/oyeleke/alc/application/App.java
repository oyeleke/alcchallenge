package com.example.oyeleke.alc.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by oyeleke on 10/29/17.
 */

public class App extends Application {
    public static App mInstance;
    public static Context mContext;


    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        setAppContext(getApplicationContext());
    }


    public static App getInstance(){
        return mInstance;
    }

    private void setAppContext(Context appContext){
        mContext = appContext;
    }
    public static Context getAppContext() {
        return mContext;
    }

}
