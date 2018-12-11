package com.common.baselibrary.base;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.common.baselibrary.R;
import com.common.baselibrary.R2;

import butterknife.BindView;


/**
 * 使用 smartrefresh+BaseRecyclerViewAdapterHelper
 * 开源框架实现的列表基类
 *
 * @auther zhc
 */
public abstract class BaseRecyclerViewActivity extends CommonActivity implements BaseQuickAdapter.OnItemLongClickListener, BaseQuickAdapter.OnItemClickListener {

    @BindView(R2.id.recyclerView)
    protected RecyclerView mRecyclerView;

    protected BaseQuickAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_rv;
    }


    @Override
    public void initView() {
        super.initView();

        mAdapter = mAdapter == null ? getRecyclerAdapter() : mAdapter;
        mRecyclerView.setLayoutManager(getLayoutManager());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.getItemAnimator().setChangeDuration(0);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemLongClickListener(this);

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
