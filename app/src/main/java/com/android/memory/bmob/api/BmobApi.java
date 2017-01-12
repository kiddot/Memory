package com.android.memory.bmob.api;

import android.util.Log;

import com.android.memory.bean._User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 *
 * Created by kiddo on 17-1-10.
 */

public class BmobApi {
    public static final String TAG = BmobApi.class.getSimpleName();
    private static final String MUBAN = "Memory";
    public static BmobApi mBombApi ;

    /**
     * 由于外面是链式进行调用，虽然不太好，但是可以成功，设置容量为1，避免出现获取值混乱
     */
    //private ArrayBlockingQueue<Object> mBlockingQueue ;
    private List<Object> mList ;


    public static BmobApi getInstance() {
        if (mBombApi == null) {
            synchronized (BmobApi.class) {
                if (mBombApi == null) {
                    mBombApi = new BmobApi();
                }
            }
        }
        return mBombApi;
    }

    public BmobApi(){
        //mBlockingQueue = new ArrayBlockingQueue<>(1 , true);//必须使用公平锁
        mList = new ArrayList<>(1);
    }

    /**
     * 用于用户名注册，用户名和密码是必须的,注册端务必要检测是否有输入合法的用户名和密码
     *
     * @param userInfo
     */
    private void signUpUser(_User userInfo, final OnResultBack callback) {
        userInfo.signUp(new SaveListener<_User>() {
            @Override
            public void done(_User userInfo, BmobException e) {
                if (e == null) {
                    Log.d(TAG, "注册成功" + userInfo.toString());
                    callback.onResult(true, userInfo);
                } else {
                    e.printStackTrace();
                    //应该弹Toast提醒用户
                    callback.onResult(false, null);
                }
            }
        });
    }

    /**
     * 将注册加一层装饰，方便外面的调用，减少回调带来的麻烦
     * @return
     */
    public Object signUp(String account , String password){
        final boolean[] finished = new boolean[1];//这样做不太好，但是为了使代码更加整洁，这里只能这样设计一层
        _User user = new _User();
        user.setUsername(account);
        user.setPassword(password);
        signUpUser(user, new OnResultBack() {
            @Override
            public void onResult(boolean success, Object object) {
                mList.add(object);
            }
        });
        while (!finished[0]){
            //空循环
        }
        return mList.get(0);
    }

    /**
     * 用于用户登录，应该传进来含有用户名和密码的bean类,务必在登录端进行检测是否有输入用户名和密码
     *
     * @param userInfo
     */
    private void loginUser(_User userInfo, final OnResultBack callback){
        userInfo.login(new SaveListener<_User>() {
            @Override
            public void done(_User userInfo, BmobException e) {
                if (e == null) {
                    Log.d(TAG, "登录成功");
                    callback.onResult(true, userInfo);
                } else {
                    Log.d(TAG, "请检查用户名或者密码");
                    callback.onResult(false, userInfo);
                }
            }
        });
    }

    /**
     * 将登录加一层装饰，方便外面的调用，减少回调带来的麻烦
     * @return
     */
    public Object login(String account , String password){
        final boolean[] finished = new boolean[1];//这样做不太好，但是为了使代码更加整洁，这里只能这样设计一层
        _User user = new _User();
        user.setUsername(account);
        user.setPassword(password);
        loginUser(user, new OnResultBack() {
            @Override
            public void onResult(boolean success, Object object) {
                finished[0] = true ;
                mList.add(object);
            }
        });
        while (!finished[0]){
            //空循环
        }
        return mList.get(0);
    }

    /**
     * 查询当前用户
     *
     * @param callback
     */
    public void queryUserInfo(final OnResultBack callback) {
        BmobQuery<_User> query = new BmobQuery<>();
        query.addWhereEqualTo("tag", "help");
        query.findObjects(new FindListener<_User>() {
            @Override
            public void done(List<_User> list, BmobException e) {
                if (e == null) {
                    Log.d(TAG, "done: 查找用户成功");
                    callback.onResult(true, list);
                } else {
                    callback.onResult(false, null);
                }
            }
        });
    }

    /**
     * 更新用户信息
     *
     * @param newUser  传入新的用户对象
     * @param callback
     */
    public void updateUserInfo(_User newUser, final OnResultBack callback) {
        Log.d(TAG, "updateUserInfo: ");
        BmobUser userInfo = _User.getCurrentUser();
        Log.d(TAG, "updateUserInfo: getCurrentUser");
        newUser.update(userInfo.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.d(TAG, "done: 更新用户成功");
                    callback.onResult(true, null);
                } else {
                    Log.d(TAG, "done: 更新用户失败");
                    e.printStackTrace();
                    callback.onResult(false, null);
                }
            }
        });
    }

    /**
     * 用于向手机发送一条手机验证码，用于注册或者登陆
     * 注册页面应在验证码输入框旁边添加发送验证码按钮，此处主要用于这个按钮的逻辑处理
     *
     * @param phone
     * @param callback 用于回调到调用处，处理成功和失败的逻辑
     */
    public void sendVerificationCode(String phone, final OnResultBack callback) {
        BmobSMS.requestSMSCode(phone, MUBAN, new QueryListener<Integer>() {
            @Override
            public void done(Integer integer, BmobException e) {
                if (e == null) {
                    Log.d(TAG, "done: 验证码发送成功" + integer);
                    callback.onResult(true, null);
                } else {
                    Log.d(TAG, "done: 验证码发送失败" + integer);
                    callback.onResult(false, null);
                }
            }
        });
    }

    /**
     * 用于点击注册按钮之后进行的逻辑：注册成功会直接跳转,成功和失败的回调处理逻辑不在此处处理
     *
     * @param phone
     * @param password
     * @param verificationCode
     * @param callback         用于回调到调用处，处理成功和失败的逻辑
     */
    public void registerByPhone(String phone, String password, String verificationCode, final OnResultBack callback) {
        _User userInfo = new _User();
        userInfo.setMobilePhoneNumber(phone);//设置手机号码
        userInfo.setPassword(password);//设置密码
        //userInfo.setUsername();没有传用户名则默认为手机号码
        Log.d(TAG, "registerByPhone: " + phone + " " + password + " " + verificationCode);
        userInfo.signOrLogin(verificationCode, new SaveListener<_User>() {
            @Override
            public void done(_User userInfo, BmobException e) {
                if (e == null) {
                    Log.d(TAG, "done: 注册成功或者登陆成功");
                    Log.d(TAG, "done: 手机号码：" + userInfo.getMobilePhoneNumber() + "用户名：" + userInfo.getUsername());
                    callback.onResult(true, userInfo);
                } else {
                    Log.d(TAG, "done: 注册或登录失败");
                    callback.onResult(false, userInfo);
                }
            }
        });
    }

    /**
     * 将注册加一层装饰，方便外面的调用，减少回调带来的麻烦
     * @return
     */
    public Object registerByPhone(String phone , String password ,String verificationCode){
        final boolean[] finished = new boolean[1];//这样做不太好，但是为了使代码更加整洁，这里只能这样设计一层
        _User user = new _User();
        registerByPhone(phone , password , verificationCode, new OnResultBack() {
            @Override
            public void onResult(boolean success, Object object) {
                mList.add(object);
            }
        });
        while (!finished[0]){
            //空循环
        }
        return mList.get(0);
    }

    public interface OnResultBack{
        void onResult(boolean success , Object object);
    }
}
