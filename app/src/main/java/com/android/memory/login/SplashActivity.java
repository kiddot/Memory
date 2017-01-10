package com.android.memory.login;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.memory.bmob.manager.BmobManager;
import com.android.memory.login.login.LoginActivity;

/**
 * Created by kiddo on 17-1-8.
 */
public class SplashActivity extends AppCompatActivity {

    private static final int WHAT_START_LOGIN = 0;
    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initHandler();
        new Thread() {
            @Override
            public void run() {
                SplashActivity.this.init();
            }
        }.start();
    }

    /**
     * 所有初始化操作写在这里
     */
    private void init() {
        initBmob();
        mHandler.sendEmptyMessage(WHAT_START_LOGIN);
    }

    private void initHandler() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case WHAT_START_LOGIN:
                        startLoginActivity();
                        finish();
                        break;
                }
            }
        };
    }


    private void startLoginActivity() {
        LoginActivity.startActivity(this);
        finish();
    }

    private void initBmob() {
        BmobManager bmobManager = BmobManager.getInstance();
        bmobManager.init(getApplicationContext());
    }

}