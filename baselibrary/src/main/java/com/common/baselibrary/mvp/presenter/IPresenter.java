package com.common.baselibrary.mvp.presenter;

import com.common.baselibrary.mvp.view.IView;

public interface IPresenter<V extends IView>{

    /**
     * 绑定视图
     *
     * @param view
     */
    void attachView(V view);

    /**
     * 解除绑定（每个V记得使用完之后解绑，主要是用于防止内存泄漏问题）
     */
    void dettachView();


}
