package com.android.memory.bmob.sp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by kiddo on 17-1-11.
 */

public class SpManager {
    public static final String TAG = SpManager.class.getSimpleName();

    public static SpManager mSpManager;

    public static SpManager getInstance() {
        if (mSpManager == null) {
            synchronized (SpManager.class) {
                if (mSpManager == null) {
                    mSpManager = new SpManager();
                }
            }
        }
        return mSpManager;
    }
//
//    public SpManager(Context context){
//        mContext = context;
//        SharedPreferences.Editor editor ;
//    }

    public void rememberPassword(Context context , String account , String password){
        SharedPreferences.Editor editor = (SharedPreferences.Editor) context.getSharedPreferences("password" , Context.MODE_PRIVATE);
        editor.putString(account , password);
        editor.apply();
    }

    public String getPassword(Context context , String account){
        SharedPreferences sharedPreferences = context.getSharedPreferences("password" , Context.MODE_PRIVATE);
        return sharedPreferences.getString(account , "");
    }

    public void rememberAccount(Context context , String account){
        SharedPreferences.Editor editor = (SharedPreferences.Editor) context.getSharedPreferences("account" , Context.MODE_PRIVATE);
        editor.putString("account" , account);
        editor.apply();
    }

    public String getAccount(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("account" , Context.MODE_PRIVATE);
        return sharedPreferences.getString("account" , "");
    }


}
