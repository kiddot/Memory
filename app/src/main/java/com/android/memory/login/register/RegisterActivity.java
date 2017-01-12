package com.android.memory.login.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.memory.R;
import com.android.memory.base.componet.BaseActivity;
import com.android.memory.main.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by kiddo on 17-1-8.
 */

public class RegisterActivity extends BaseActivity {
    public static final String TAG = RegisterActivity.class.getSimpleName();
    @BindView(R.id.register_et_phone)
    EditText mRegisterEtPhone;
    @BindView(R.id.register_et_psw)
    EditText mRegisterEtPsw;
    @BindView(R.id.register_et_captcha)
    EditText mRegisterEtCaptcha;
    @BindView(R.id.register_btn_captcha)
    Button mRegisterBtnCaptcha;
    @BindView(R.id.register_btn_register)
    Button mRegisterBtnRegister;

    private RegisterDelegate mRegisterDelegate;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mRegisterDelegate = new RegisterDelegate();
        initView();
    }

    private void initView(){
        mRegisterEtPhone = (EditText) findViewById(R.id.register_et_phone);
        mRegisterEtPsw = (EditText) findViewById(R.id.register_et_psw);
        mRegisterEtCaptcha = (EditText) findViewById(R.id.register_et_captcha);
    }

//    @OnClick({R.id.register_btn_captcha, R.id.register_btn_register})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.register_btn_captcha:
//                sendVerificationCode();
//                break;
//            case R.id.register_btn_register:
//                register();
//                break;
//        }
//    }

    public void sendVerificationCode(View view){
        if (mRegisterEtPhone.getText().toString().equals("")){
            showToast("请输入有效的手机号码");
        }else {
            mRegisterDelegate.sendMsg(mRegisterEtPhone.getText().toString());
        }
    }

    public void register(View view){
        Boolean success = mRegisterDelegate.register(mRegisterEtPhone.getText().toString() ,
                mRegisterEtPsw.getText().toString() ,
                mRegisterEtCaptcha.getText().toString()).getResult();
        if (success != null){
            MainActivity.startActivity(this);
        }else {
            showToast("请检查您所填的资料");
        }
    }
}
