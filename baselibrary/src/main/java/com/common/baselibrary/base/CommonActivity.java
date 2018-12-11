package com.common.baselibrary.base;

import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;

import com.common.baselibrary.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 项目中的Activity基类
 */
public abstract class CommonActivity extends UiActivity {

    private Unbinder mButterKnife;//View注解

    SmartRefreshLayout mSmartRefresh;

    protected int pageIndex;

    @Override
    protected void init() {

        mButterKnife = ButterKnife.bind(this);

        initOrientation();

        initSmartRefresh();

        super.init();
    }

    /**
     * 初始化数据刷新框架
     */
    private void initSmartRefresh() {
        mSmartRefresh = findViewById(R.id.smart_refresh);
        if(mSmartRefresh == null) {
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

    /**
     * 子类不必实现，自动调用initData刷新数据
     *
     * @param refreshLayout
     */
    protected void onRefreshData(RefreshLayout refreshLayout) {
        initData();
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
     * 子类自己实现，加载更多成功后的操作
     *
     * @param mlist
     */
    protected void onLoadMoreSuccess(List mlist) {
        pageIndex++;
        if (mSmartRefresh != null) {
            if (mlist == null) {
                return;
            }
            if (mlist.size() == 0) {
                mSmartRefresh.finishLoadMoreWithNoMoreData();
            } else {
                mSmartRefresh.finishLoadMore();
            }
        }
    }

    /**
     * 若无特殊需求，子类可以不处理加载更多失败后的操作。
     *
     * @param msg
     */
    public void onLoadMoreFail(String msg) {
        if (mSmartRefresh != null) {
            mSmartRefresh.finishLoadMore();
        }

        loadMoreFail(msg);
    }

    /**
     * 子类自己实现，loadMoreFail
     *
     * @param msg
     */
    protected void loadMoreFail(String msg) {

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
    }
}
