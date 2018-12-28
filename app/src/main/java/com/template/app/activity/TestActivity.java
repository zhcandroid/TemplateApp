package com.template.app.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.common.baselibrary.base.BaseRecyclerViewActivity;
import com.hjq.toast.ToastUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.template.app.R;
import com.template.app.adapter.TestAdapter;
import com.template.app.arouter.ARouterUriManger;
import com.template.app.bean.TestBean;
import com.template.app.mvp.contract.TestContract;
import com.template.app.mvp.presnter.TestPresenter;

import java.util.ArrayList;
import java.util.List;

@Route(path = ARouterUriManger.AROUTER_TestActivity)
public class TestActivity extends BaseRecyclerViewActivity<TestBean, TestPresenter> implements TestContract.TestView {

    @Override
    public TestPresenter getPresenter() {
        return new TestPresenter();
    }

    @Override
    public void initView() {
        super.initView();
        //设置 Header 为 贝塞尔雷达 样式
        // mSmartRefresh.setRefreshHeader(new TaurusHeader(this));
    }

    @Override
    public void initData() {
        super.initData();
        presenter.getDataFromServer();
    }


    @Override
    protected BaseQuickAdapter getRecyclerAdapter() {
        return new TestAdapter(R.layout.item_test);
    }


    @Override
    protected void onRefreshData(RefreshLayout refreshLayout) {
        super.onRefreshData(refreshLayout);


    }

    @Override
    protected void onLoadMoreData(RefreshLayout refreshLayout) {
        super.onLoadMoreData(refreshLayout);


    }


    /**
     * 显示列表数据
     *
     * @param list
     */
    @Override
    public void showList(List<TestBean> list) {
        ToastUtils.show("showList 执行了");
        onRefreshSuccess(list);
    }


}
