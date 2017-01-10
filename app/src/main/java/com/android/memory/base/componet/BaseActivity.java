package com.android.memory.base.componet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.memory.base.thread.ThreadPoolConst;
import com.android.memory.base.thread.ThreadPoolManager;

import java.util.concurrent.Executor;

import bolts.Task;
import butterknife.ButterKnife;

/**
 *
 * Created by kiddo on 17-1-9.
 */
public abstract class BaseActivity extends AppCompatActivity {

    //以ClassName作为TAG
    private static final String TAG = BaseActivity.class.getSimpleName();
    protected static Executor sHTTPExecutor = ThreadPoolManager.getInstance().getThreadPool(ThreadPoolConst.THREAD_TYPE_SIMPLE_HTTP);
    protected static Executor sWORKExecutor = ThreadPoolManager.getInstance().getThreadPool(ThreadPoolConst.THREAD_TYPE_WORK);
    protected static Executor sUIExecutor = Task.UI_THREAD_EXECUTOR;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        init(savedInstanceState);
    }

    /*
    * 重写该方法来设置布局Id
    */
    protected abstract int getLayoutId();

    /*
    * 初始化
    * */
    protected abstract void init(Bundle savedInstanceState);

}
