package com.common.baselibrary;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hjq.toast.ToastUtils;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshInitializer;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.umeng.analytics.MobclickAgent;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;

/**
 * base库Application上下文的入口
 * 需要在app module的Application中初始化
 */
public class BaseLibraryApp {

    private static Application application;
    private static boolean debug;

    public static void init(Application app,boolean debug){
        setApplication(app);
        setDebug(debug);
        initBGASwipe(app);
        ToastUtils.init(app);
        //路由初始化
        ARouter.init(app);


    }

    public static Application getApplication() {
        return application;
    }

    private static void setApplication(Application application) {
        BaseLibraryApp.application = application;
    }

    public static boolean isDebug() {
        return debug;
    }

    public static void setDebug(boolean debug) {
        BaseLibraryApp.debug = debug;
    }


    private static void initBGASwipe(Application app){
        /**
         * 必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回
         * 第一个参数：应用程序上下文
         * 第二个参数：如果发现滑动返回后立即触摸界面时应用崩溃，请把该界面里比较特殊的 View 的 class 添加到该集合中，目前在库中已经添加了 WebView 和 SurfaceView
         */
        BGASwipeBackHelper.init(app, null);
    }



    //static 代码段可以防止内存泄露
    static {
        //设置全局的基本设置构建器
        SmartRefreshLayout.setDefaultRefreshInitializer(new DefaultRefreshInitializer() {
            @Override
            public void initialize(@NonNull Context context, @NonNull RefreshLayout refreshLayout) {
                //取消内容不满一页时开启上拉加载功能
                refreshLayout.setEnableLoadMoreWhenContentNotFull(false);
                //是否在刷新的时候禁止列表的操作
                refreshLayout.setDisableContentWhenRefresh(true);
                //是否在加载的时候禁止列表的操作
                refreshLayout.setDisableContentWhenLoading(true);
            }
        });
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                //全局设置主题颜色
                layout.setPrimaryColorsId(R.color.colorPrimary, R.color.white);
                //.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
                return new MaterialHeader(context);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

}
