package com.android.memory.login.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.memory.R;
import com.android.memory.base.componet.BaseActivity;


/**
 *
 * Created by kiddo on 17-1-8.
 */

public class LoginActivity extends BaseActivity {
    public static final String Tag = LoginActivity.class.getSimpleName();

    public static void startActivity(Context context){
        Intent intent = new Intent(context , LoginActivity.class);
        context.startActivity(intent);
    }

    public void login(View view){

    }

    public void register(View view){
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }
}
