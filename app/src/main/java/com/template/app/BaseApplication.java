package com.template.app;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.common.baselibrary.BaseLibraryApp;
import com.tencent.tinker.entry.ApplicationLike;
import com.tinkerpatch.sdk.TinkerPatch;
import com.tinkerpatch.sdk.loader.TinkerPatchApplicationLike;
//直接继承MultiDexApplication不需要在手动调用  MultiDex.install(this);
public class BaseApplication extends MultiDexApplication {
    public static Context context;
    private ApplicationLike mApplicationLike;

    @Override
    public void onCreate() {
        super.onCreate();

        initThinker();

        context = this;

        //初始化基础库
        BaseLibraryApp.init(this, true);


    }

    /**
     * 对热修复sdk进行初始化
     */
    private void initThinker() {
        // 我们可以从这里获得Tinker加载过程的信息
        mApplicationLike = TinkerPatchApplicationLike.getTinkerPatchApplicationLike();

        // 初始化TinkerPatch SDK, 更多配置可参照API章节中的,初始化SDK
        TinkerPatch.init(mApplicationLike)
                .reflectPatchLibrary()
                .setPatchRollbackOnScreenOff(true)
                .setPatchRestartOnSrceenOff(true)
                .setFetchPatchIntervalByHours(3);

        // 每隔3个小时(通过setFetchPatchIntervalByHours设置)去访问后台时候有更新,通过handler实现轮训的效果
        TinkerPatch.with().fetchPatchUpdateAndPollWithInterval();

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }


}
