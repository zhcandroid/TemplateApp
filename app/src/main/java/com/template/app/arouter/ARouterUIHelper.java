package com.template.app.arouter;

import android.content.Context;

import com.alibaba.android.arouter.facade.callback.NavigationCallback;

public class ARouterUIHelper {

    public static void openTestActivity(){
        ARouterUtils.toActivity(ARouterUriManger.AROUTER_TestActivity);
    }

    public static void openTestActivity(Context context, NavigationCallback callback){
        ARouterUtils.toActivity(ARouterUriManger.AROUTER_TestActivity,context,callback);
    }
}
