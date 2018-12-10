package com.common.baselibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.common.baselibrary.interf.BaseViewInterface;

/**
 * 描述：activity的基类
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseViewInterface {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }
        init();

    }

    protected void init() {
        initView();
        initData();
        addListener();

    }

    //引入布局
    protected abstract int getLayoutId();

    //标题栏id
    protected abstract int getTitleBarId();

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void addListener() {

    }


    /**
     * 延迟执行某个任务
     *
     * @param action Runnable对象
     */
    public boolean post(Runnable action) {
        return getWindow().getDecorView().post(action);
    }

    /**
     * 延迟某个时间执行某个任务
     *
     * @param action      Runnable对象
     * @param delayMillis 延迟的时间
     */
    public boolean postDelayed(Runnable action, long delayMillis) {
        return getWindow().getDecorView().postDelayed(action, delayMillis);
    }

    /**
     * 删除某个延迟任务
     *
     * @param action Runnable对象
     */
    public boolean removeCallbacks(Runnable action) {
        if (getWindow().getDecorView() != null) {
            return getWindow().getDecorView().removeCallbacks(action);
        } else {
            return true;
        }
    }

}
