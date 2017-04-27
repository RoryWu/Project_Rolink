package com.jcoder.app.folink;

import android.app.Application;
import android.content.Context;

/**
 * Created by Rory on 16/9/30.
 *
 * https://api.tianapi.com/meinv/?key=APIKEY&num=10
 */

public class FolinkApp extends Application {

    private static FolinkApp instance = null;

    public static Context getContext() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this ;
    }
}
