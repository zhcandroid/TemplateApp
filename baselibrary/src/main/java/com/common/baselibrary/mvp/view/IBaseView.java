package com.common.baselibrary.mvp.view;

import android.content.Context;

public interface IBaseView extends IView{

    /**
     * 显示加载框
     */
    void showLoading();

    /**
     * 隐藏加载框
     */
    void dismissLoading();

    /**
     * 空数据
     *
     * @param tag TAG
     */
    void onEmpty(Object tag);

    /**
     * 错误数据
     *
     * @param tag      TAG
     * @param errorMsg 错误信息
     */
    void onError(Object tag, String errorMsg);

    /**
     * 上下文
     *
     * @return context
     */
    Context getContext();
}
