package com.android.memory.base.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.android.memory.base.thread.ThreadPoolConst;
import com.android.memory.base.thread.ThreadPoolManager;
import com.android.memory.base.util.DialogFragmentHelper;

import java.util.concurrent.Executor;

import bolts.Task;
import butterknife.ButterKnife;

/**
 *
 * Created by kiddo on 17-1-10.
 */
public abstract class BaseFragment extends MSBaseFragment{
    public static final String TAG = BaseFragment.class.getSimpleName();
    protected static Executor sHTTPExecutor = ThreadPoolManager.getInstance().getThreadPool(ThreadPoolConst.THREAD_TYPE_SIMPLE_HTTP);
    protected static Executor sWORKExecutor = ThreadPoolManager.getInstance().getThreadPool(ThreadPoolConst.THREAD_TYPE_WORK);
    protected static Executor sUIExecutor = Task.UI_THREAD_EXECUTOR;


    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this , getContentView());
    }

    private DialogFragment mLoadingDialog;

    protected void showLoading(String msg) {
        boolean isDestroy;
        if (Build.VERSION.SDK_INT > 16) {
            isDestroy = getActivity().isDestroyed();
        } else {
            isDestroy = getActivity().isFinishing();
        }
        if (mLoadingDialog == null && !isDestroy)
            mLoadingDialog = DialogFragmentHelper.showProgress(getChildFragmentManager(), msg, true);
    }

    protected void dismissLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }
}
