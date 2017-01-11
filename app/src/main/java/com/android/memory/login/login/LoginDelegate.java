package com.android.memory.login.login;

import android.content.Context;

import com.android.memory.base.thread.ThreadPoolConst;
import com.android.memory.base.thread.ThreadPoolManager;
import com.android.memory.bean._User;
import com.android.memory.bmob.api.BmobApi;
import com.android.memory.bmob.sp.SpManager;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

import bolts.Continuation;
import bolts.Task;

/**
 * Created by kiddo on 17-1-8.
 */

public class LoginDelegate {
    private static final String TAG = LoginDelegate.class.getSimpleName();
    private SpManager mSpManager ;

    public LoginDelegate(){
        mSpManager = SpManager.getInstance();
    }

    /**
     * 登录逻辑
     * @param account 用户名
     * @param password 密码
     * @param remember 是否记住密码
     */
    public Task<Boolean> login(Context context , final String account , final String password , boolean remember){
        Executor workExecutor = ThreadPoolManager.getInstance().getThreadPool(ThreadPoolConst.THREAD_TYPE_WORK);
        if (remember){
            mSpManager.rememberPassword(context , account , password);
        }
        return Task.call(new Callable<_User>(){

            @Override
            public _User call() throws Exception {
                return (_User) BmobApi.getInstance().login(account, password);
            }
        } , workExecutor).continueWith(new Continuation<_User, Boolean>() {
            @Override
            public Boolean then(Task<_User> task) throws Exception {
                _User user = task.getResult();
                if (user.getObjectId().equals("")||user.getObjectId() == null){//说明登录失败
                    return false;
                }else {
                    //可以在这里执行一些登录后获取用户信息的耗时操作
                    return true;
                }
            }
        },workExecutor);
    }

}
