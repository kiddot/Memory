package com.android.memory.login.register;

import com.android.memory.base.thread.ThreadPoolConst;
import com.android.memory.base.thread.ThreadPoolManager;
import com.android.memory.bean._User;
import com.android.memory.bmob.api.BmobApi;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by kiddo on 17-1-8.
 */

public class RegisterDelegate {
    private static final String TAG = RegisterDelegate.class.getSimpleName();

    public Task<Boolean> register(final String phone , final String password , final String verificationCode){
        Executor workExecutor = ThreadPoolManager.getInstance().getThreadPool(ThreadPoolConst.THREAD_TYPE_WORK);
        return Task.call(new Callable<_User>() {
            @Override
            public _User call() throws Exception {
                return (_User) BmobApi.getInstance().registerByPhone(phone , password , verificationCode);
            }
        } ,workExecutor).continueWith(new Continuation<_User, Boolean>() {
            @Override
            public Boolean then(Task<_User> task) throws Exception {
                return null;
            }
        } ,workExecutor);
    }

    public void sendMsg(String phone){
        BmobApi.getInstance().sendVerificationCode(phone, new BmobApi.OnResultBack() {
            @Override
            public void onResult(boolean success, Object object) {

            }
        });
    }
}
