package com.android.memory.base.fragment;

import android.os.Bundle;

import com.changelcai.mothership.assit.Toastor;
import com.changelcai.mothership.utils.HandlerUtil;

/**
 * Created by Hans on 16/7/7.
 *
 * 基础组件:mothership中基础的Fragment类
 * 建议项目中的BaseFragment可以继承该类
 */
public abstract class MSBaseFragment extends MSLazyFragment {
    private Toastor sToast;

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(getContentViewId());
        if (sToast == null && getContext() != null) sToast = new Toastor(getContext());
        init(savedInstanceState);
    }

    protected abstract int getContentViewId();

    protected abstract void init(Bundle savedInstanceState);


    public void showToast(final String content) {
        HandlerUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (getContext() == null) return;
                if (sToast == null) sToast = new Toastor(getContext());
                sToast.showToast(content);
            }
        });
    }
}
