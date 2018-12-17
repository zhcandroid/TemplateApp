package com.common.baselibrary.mvp.presenter;

import com.common.baselibrary.mvp.view.IView;

import java.lang.ref.WeakReference;

/**
 * 抽象类 统一管理View层绑定和解除绑定
 *
 * @param <V>
 */
public class BasePresenter<V extends IView> implements IPresenter<V> {

    private WeakReference<V> weakView;

    public V getView() {
        return weakView.get();
    }

    /**
     * 用于检查View是否为空对象
     *
     * @return
     */
    public boolean isAttachView() {
        return this.weakView != null && this.weakView.get() != null;
    }

    @Override
    public void attachView(V view) {
        this.weakView = new WeakReference<V>(view);
    }

    @Override
    public void detachView() {
        if (this.weakView != null) {
            this.weakView.clear();
            this.weakView = null;
        }
    }
}
