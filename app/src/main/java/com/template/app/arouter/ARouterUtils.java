package com.template.app.arouter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.common.baselibrary.base.BaseActivity;

public class ARouterUtils {
    /**
     * 根据path返回Fragment
     *
     * @param path path
     * @return fragment
     */
    public static Fragment getFragment(String path) {
        return (Fragment) ARouter.getInstance()
                .build(path)
                .navigation();
    }

    /**
     * 根据path返回Activity
     *
     * @param path path
     * @return Activity
     */
    public static BaseActivity getActivity(String path) {
        return (BaseActivity) ARouter.getInstance()
                .build(path)
                .navigation();
    }

    /**
     * ARouter路由普通不带参数跳转页面
     * */
    public static void toActivity(String path){
        ARouter.getInstance().build(path).navigation();
    }

    /**
     * ARouter路由带bundle参数跳转页面
     * */
    public static void toActivity(String path,Bundle params){
        ARouter.getInstance().build(path).with(params).navigation();
    }

    /**
     * 构建标准的路由请求，startActivityForResult
     * ARouter路由带RequestCode参数跳转页面
     * navigation的第一个参数必须是Activity，第二个参数则是RequestCode
     * */
    public static void toActivity(AppCompatActivity activity, String path, int RequestCode){
        ARouter.getInstance().build(path).navigation(activity,RequestCode);
    }
}
