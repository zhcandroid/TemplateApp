package com.common.baselibrary.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * desc : 项目中Fragment懒加载基类
 */
public abstract class CommonLazyFragment extends UILazyFragment {

    private Unbinder mButterKnife;// View注解

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mButterKnife = ButterKnife.bind(this, view);
        ARouter.getInstance().inject(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // 友盟统计
        MobclickAgent.onResume(getContext());
    }

    @Override
    public void onPause() {
        super.onPause();
        // 友盟统计
        MobclickAgent.onPause(getContext());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mButterKnife.unbind();
    }
}