package com.common.baselibrary.base;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.common.baselibrary.R;
import com.common.baselibrary.R2;
import com.common.baselibrary.net.callback.OnResultCallBack;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

import butterknife.BindView;


/**
 * 使用 smartrefresh+BaseRecyclerViewAdapterHelper
 * 开源框架实现的列表基类
 * @auther zhc
 */
public abstract class BaseRecyclerViewActivity<T> extends CommonActivity implements BaseQuickAdapter.OnItemLongClickListener, BaseQuickAdapter.OnItemClickListener {

    @BindView(R2.id.recyclerView)
    protected RecyclerView mRecyclerView;
    protected BaseQuickAdapter mAdapter;

    protected OnResultCallBack mOnResultCallBack;

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

        mOnResultCallBack = new OnResultCallBack<List<T>>(){

            @Override
            public void onSuccess(boolean success, int code, String msg, Object tag, List<T> response) {
                setRefreshData(response);
            }

            @Override
            public void onFailure(Object tag, Exception e) {

            }

            @Override
            public void onCompleted() {

            }
        };

    }

    protected void setRefreshData(List<T> list){
        mSmartRefresh.finishRefresh();
        mAdapter.setNewData(list);


    }

    protected void setLoadMoreData(List<T> list){
        mSmartRefresh.finishLoadMore();
        mAdapter.addData(list);

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
