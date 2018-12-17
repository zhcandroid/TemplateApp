package com.common.baselibrary.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.common.baselibrary.R;
import com.common.baselibrary.entity.Event;
import com.common.baselibrary.interf.OnTitleBarListener;
import com.common.baselibrary.utils.RxBus;
import com.common.baselibrary.view.TitleBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 项目中的Activity基类
 */
public abstract class CommonActivity extends UiActivity implements OnTitleBarListener {

    private Unbinder mButterKnife;//View注解
    private CompositeDisposable mDisposables;

    protected  SmartRefreshLayout mSmartRefresh;

    private RxPermissions rxPermissions;

    protected Activity mContext;

    @Override
    protected void init() {
        mContext = this;
        mButterKnife = ButterKnife.bind(this);

        if (rxPermissions == null) {
            rxPermissions = new RxPermissions((FragmentActivity) mContext);
        }
        initTitleBar();

        initOrientation();

        initSmartRefresh();

        initEventBus();

        super.init();
    }

    /**
     * 初始化titleBar对项目标题进行统一处理
     */
    private void initTitleBar() {
        //初始化标题栏的监听
        if (getTitleBarId() > 0) {
            if (findViewById(getTitleBarId()) instanceof TitleBar) {
                TitleBar titleBar = (TitleBar) findViewById(getTitleBarId());
                titleBar.setTitleBarListener(this);
            }
        }


    }


    /**
     * 初始化数据刷新框架
     */
    private void initSmartRefresh() {
        mSmartRefresh = findViewById(R.id.smart_refresh);
        if (mSmartRefresh != null) {
            mSmartRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
                @Override
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    onRefreshData(refreshLayout);
                }

                @Override
                public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                    onLoadMoreData(refreshLayout);
                }

            });
            if (!useLoadMore()) {
                mSmartRefresh.setEnableLoadMore(false);
            }
        }


    }


    private void initEventBus() {

        if (useEventBus()) {
            //注册eventbus
            Disposable disposable = RxBus.getDefault()
                    .register(Event.class, new Consumer<Event>() {
                        @Override
                        public void accept(Event event) {
                            int eventCode = event.getCode();
                            switch (eventCode) {
                                case Event.EVENT_CLOSE_ALL_ACTIVITY:

                                    break;
                                default:
                                    onEvent(event);
                                    break;
                            }
                        }
                    });
            addDispose(disposable);
        }
    }


    /**
     * 检查权限
     * @param observer
     * @param permissions  Manifest.permission.WRITE_EXTERNAL_STORAGE
     */
    public void checkPermission(Observer<Boolean> observer, String... permissions) {
        rxPermissions.request(permissions)
                .subscribe(observer);
    }

    /**
     * 请求权限
     * @param context
     * @param observer
     * @param permissions
     */
    public void requstPermission(Activity context, Observer<Boolean> observer, String... permissions) {
        rxPermissions.shouldShowRequestPermissionRationale(context, permissions)
                .subscribe(observer);
    }

    /**
     * 子类自己实现，处理接收到到Rxbus
     *
     * @param event
     */
    protected void onEvent(Event event) {

    }

    /**
     * RxJava 添加订阅
     */
    protected void addDispose(Disposable disposable) {
        if (mDisposables == null) {
            mDisposables = new CompositeDisposable();
        }
        //将所有disposable放入,集中处理
        mDisposables.add(disposable);
    }

    /**
     * 子类不必自己实现 刷新数据 默认调用initData
     *
     * @param refreshLayout
     */
    protected void onRefreshData(RefreshLayout refreshLayout) {

    }

    /**
     * 子类自己实现，onLoadMoreData
     *
     * @param refreshLayout
     */
    protected void onLoadMoreData(RefreshLayout refreshLayout) {

    }

    /**
     * 子类自己实现，是否使用加载更多,默认使用
     *
     * @return
     */
    protected boolean useLoadMore() {
        return true;
    }



    /**
     * 子类自己实现，是否实用Rxbus,默认不使用
     *
     * @return
     */
    protected boolean useEventBus() {
        return false;
    }

    /**
     * 初始化横竖屏方向，会和 LauncherTheme 主题样式有冲突，注意不要同时使用
     */
    protected void initOrientation() {
        //如果没有指定屏幕方向，则默认为竖屏
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        // 手动统计页面
        MobclickAgent.onPageStart(getClass().getSimpleName());
        // 友盟统计
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 手动统计页面，必须保证 onPageEnd 在 onPause 之前调用，因为SDK会在 onPause 中保存onPageEnd统计到的页面数据
        MobclickAgent.onPageEnd(getClass().getSimpleName());
        // 友盟统计
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mButterKnife != null) mButterKnife.unbind();
        RxBus.getDefault().unregister(mDisposables);
    }

    /**
     * @param v     被点击的左项View
     */
    @Override
    public void onLeftClick(View v) {

    }

    /**
     *
     * @param v     被点击的标题View
     */
    @Override
    public void onTitleClick(View v) {

    }

    /**
     *
     * @param v     被点击的右项View
     */
    @Override
    public void onRightClick(View v) {

    }
}
