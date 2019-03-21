package com.template.app;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.common.baselibrary.BaseLibraryApp;
import com.meituan.android.walle.WalleChannelReader;
import com.tencent.tinker.entry.ApplicationLike;
import com.tinkerpatch.sdk.TinkerPatch;
import com.tinkerpatch.sdk.loader.TinkerPatchApplicationLike;
import com.umeng.analytics.MobclickAgent;

//直接继承MultiDexApplication不需要在手动调用  MultiDex.install(this);
public class BaseApplication extends MultiDexApplication {
    public static Context context;
    private ApplicationLike mApplicationLike;

    @Override
    public void onCreate() {
        super.onCreate();

        initThinker();

        context = this;

        initWalle();

        //初始化基础库
        BaseLibraryApp.init(this, true);


    }

    //使用walle时 传统的manifest清单文件配置的方式不在生效
    private void initWalle() {
        // 友盟统计
       // MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        //获取walle多渠道信息
        String channel = WalleChannelReader.getChannel(this.getApplicationContext());
        //设置给友盟统计
        MobclickAgent.startWithConfigure(new MobclickAgent.UMAnalyticsConfig(getApplicationContext(),"appkey",channel));


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
