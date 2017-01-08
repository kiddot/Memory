package com.android.memory;

import android.app.Application;
import android.content.Context;

/**
 * Created by kiddo on 17-1-8.
 */

public class MemoryApplication extends Application{
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext(){
        return mContext;
    }
}
