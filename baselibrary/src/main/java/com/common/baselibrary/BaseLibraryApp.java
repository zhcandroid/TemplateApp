package com.common.baselibrary;

import android.app.Application;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;

/**
 * base库Application上下文的入口
 * 需要在app module的Application中初始化
 */
public class BaseLibraryApp {

    private static Application application;
    private static boolean debug;


    public static void init(Application app){
        setApplication(app);
        setDebug(debug);
        initBGASwipe(app);
    }

    public static void init(Application app,boolean debug){
        setApplication(app);
        setDebug(debug);
        initBGASwipe(app);
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

}
