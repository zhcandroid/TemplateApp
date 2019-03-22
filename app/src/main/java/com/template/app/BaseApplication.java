package com.template.app;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.common.baselibrary.BaseLibraryApp;
import com.meituan.android.walle.WalleChannelReader;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;

//直接继承MultiDexApplication不需要在手动调用  MultiDex.install(this);
public class BaseApplication extends MultiDexApplication {
    public static Context context;

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
        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        // 《异常日志初始化》为了保证运营数据的准确性，建议不要在异步线程初始化Bugly。  测试阶段建议设置成true，发布时设置为false。
        CrashReport.initCrashReport(getApplicationContext(), "注册时申请的APPID", false);
        // 《热更新初始化》 调试时，将第三个参数改为true
        Bugly.init(this, "注册时申请的APPID", false);

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // you must install multiDex whatever tinker is installed!
        //MultiDex.install(base);

        // 安装tinker
        Beta.installTinker();
    }


}
