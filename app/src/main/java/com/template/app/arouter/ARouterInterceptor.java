package com.template.app.arouter;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;

/**
 * 使用Interceptor类注解的priority数值越小，越先执行，优先级越高
 * */
@Interceptor(priority = 1)
public class ARouterInterceptor implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        Log.e("testService", ARouterInterceptor.class.getName() + " has process.");
        //拦截跳转，进行一些处理
        if (postcard.getPath().equals(ARouterUriManger.AROUTER_TestActivity)) {
            Log.e("testService", ARouterInterceptor.class.getName() + " 进行了拦截处理！");
        }
        callback.onContinue(postcard);
    }

    @Override
    public void init(Context context) {

    }
}
