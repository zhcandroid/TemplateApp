package com.common.baselibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.common.baselibrary.interf.BaseViewInterface;
import com.common.baselibrary.mvp.presenter.BasePresenter;
import com.common.baselibrary.mvp.view.IView;

/**
 * 描述：activity的基类
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseViewInterface, IView {

    protected BasePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }
        //初始化Presenter
        presenter = getPresenter();
        if (presenter == null) {
            presenter = new BasePresenter();
        }
        // 绑定View引用
        presenter.attachView(this);

        init();

    }

    protected void init() {
        initView();
        initData();
        addListener();


    }

    public abstract BasePresenter getPresenter();

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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            // 断开View引用
            presenter.detachView();
        }

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
