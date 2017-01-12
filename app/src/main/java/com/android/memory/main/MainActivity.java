package com.android.memory.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.android.memory.R;
import com.android.memory.base.componet.BaseActivity;

/**
 * Created by kiddo on 17-1-11.
 */

public class MainActivity extends BaseActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    public static void startActivity(Context context){
        Intent intent = new Intent(context , MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }
}
