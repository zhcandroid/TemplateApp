package com.common.baselibrary.interf;

import android.view.View;

/**
 * titleBar点击监听
 */
public interface OnTitleBarListener {

    /**
     * 左项被点击
     * @param v     被点击的左项View
     */
    void onLeftClick(View v);

    /**
     * 标题被点击
     * @param v     被点击的标题View
     */
    void onTitleClick(View v);

    /**
     * 右项被点击
     * @param v     被点击的右项View
     */
    void onRightClick(View v);



}