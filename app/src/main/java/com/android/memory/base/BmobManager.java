package com.android.memory.base;

import android.content.Context;
import android.util.Log;

import cn.bmob.v3.Bmob;

/**
 * Created by kiddo on 17-1-8.
 */

public class BmobManager {
    private static final String TAG = BmobManager.class.getSimpleName();
    private static BmobManager mBmobManger;

    public static BmobManager getInstance() {
        if (mBmobManger == null) {
            synchronized (BmobManager.class) {
                if (mBmobManger == null) {
                    mBmobManger = new BmobManager();
                }
            }
        }
        return mBmobManger;
    }

    public void init(Context context){
        Bmob.initialize(context , "ad684cd876c6eb9e433f738ad5822986");
        Log.d(TAG , "Bmob初始化完毕");
    }
}
