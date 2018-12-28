package com.common.baselibrary.base;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.common.baselibrary.R;
import com.common.baselibrary.R2;
import com.common.baselibrary.mvp.presenter.BasePresenter;
import com.common.baselibrary.net.callback.OnResultCallBack;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

import butterknife.BindView;


/**
 * 使用 smartrefresh+BaseRecyclerViewAdapterHelper
 * 开源框架实现的列表基类
 *
 * @auther zhc
 */
public abstract class BaseRecyclerViewActivity<T,P extends BasePresenter> extends CommonActivity<P> implements BaseQuickAdapter.OnItemLongClickListener, BaseQuickAdapter.OnItemClickListener {

    @BindView(R2.id.recyclerView)
    protected RecyclerView mRecyclerView;
    protected BaseQuickAdapter mAdapter;

    protected OnResultCallBack mOnResultCallBack;
    protected int pageIndex;
    protected int pageSize = 10;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_rv;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.id_titleBar;
    }

    @Override
    public void initView() {
        super.initView();

        mAdapter = mAdapter == null ? getRecyclerAdapter() : mAdapter;
        mAdapter.setEnableLoadMore(false);

        mRecyclerView.setLayoutManager(getLayoutManager());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.getItemAnimator().setChangeDuration(0);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemLongClickListener(this);

    }

    @Override
    public void initData() {
        super.initData();

        mOnResultCallBack = new OnResultCallBack<List<T>>() {

            @Override
            public void onSuccess(boolean success, int code, String msg, Object tag, List<T> response) {
                setDataList(response);

            }

            @Override
            public void onFailure(Object tag, Exception e) {
                onActionCompleted();

            }

            /**
             * 注意这个回调不是每次必走的
             * 当走onFailure时 onCompleted不执行
             */
            @Override
            public void onCompleted() {

            }
        };

    }

    protected void setDataList(List<T> response) {
        if (pageIndex == 0) {
            onRefreshSuccess(response);
        } else {
            onLoadMoreSuccess(response);

        }
    }

    protected void onActionCompleted() {
        if(mSmartRefresh != null){
            if (pageIndex == 0) {
                mSmartRefresh.finishLoadMore();
            } else {
                mSmartRefresh.finishRefresh();
            }
        }
    }

    protected void onRefreshSuccess(List<T> list) {
        if (mSmartRefresh != null) {
            mSmartRefresh.finishRefresh();
            if (list == null) {
                return;
            }

        }
        if(mAdapter != null){
            mAdapter.setNewData(list);
            pageIndex++;
        }

    }


    protected void onLoadMoreSuccess(List mlist) {
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
        if(mAdapter != null){
            mAdapter.addData(mlist);
            pageIndex++;
        }

    }


    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }


    protected abstract BaseQuickAdapter getRecyclerAdapter();


    @Override
    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
        return false;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }


}
