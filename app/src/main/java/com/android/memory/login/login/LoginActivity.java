package com.android.memory.login.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.android.memory.R;
import com.android.memory.base.componet.BaseActivity;
import com.android.memory.bmob.sp.SpManager;
import com.android.memory.login.register.RegisterActivity;
import com.android.memory.main.MainActivity;

import butterknife.BindView;


/**
 *
 * Created by kiddo on 17-1-8.
 */

public class LoginActivity extends BaseActivity{
    public static final String TAG = LoginActivity.class.getSimpleName();
    @BindView(R.id.username_edit)
    EditText mUsernameEdit;
    @BindView(R.id.password_edit)
    EditText mPasswordEdit;
    @BindView(R.id.remember_password)
    CheckBox mRememberPassword;

    private SpManager mSpManager;
    private LoginDelegate mLoginDelegate;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    public void login(View view) {
        Boolean success = mLoginDelegate.login(this ,
                mUsernameEdit.getText().toString() ,
                mPasswordEdit.getText().toString() ,
                mRememberPassword.isChecked()).getResult();
        if (success != null){
            MainActivity.startActivity(this);
        }else {
            showToast("请检查用户名或者密码是否正确！");
        }
    }

    public void register(View view) {
        RegisterActivity.startActivity(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
    }

    private void initView(){
        mSpManager = SpManager.getInstance();
        mLoginDelegate = new LoginDelegate();
        /**
         * 不知道什么原因黄油刀用不了
         */
        mUsernameEdit = (EditText) findViewById(R.id.username_edit);
        mPasswordEdit = (EditText) findViewById(R.id.password_edit);
        mRememberPassword = (CheckBox) findViewById(R.id.remember_password);
    }

    private void initData(){
        mUsernameEdit.setText(mSpManager.getAccount(this));
        mPasswordEdit.setText(mSpManager.getPassword(this , mSpManager.getAccount(this)));
    }
}
