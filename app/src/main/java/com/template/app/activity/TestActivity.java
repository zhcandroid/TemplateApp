package com.template.app.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.common.baselibrary.base.BaseRecyclerViewActivity;
import com.scwang.smartrefresh.header.TaurusHeader;
import com.scwang.smartrefresh.header.WaveSwipeHeader;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.template.app.AppConfig;
import com.template.app.R;
import com.template.app.adapter.TestAdapter;
import com.template.app.arouter.ARouterUriManger;
import com.template.app.bean.TestBean;

import java.util.ArrayList;
import java.util.List;

@Route(path = ARouterUriManger.AROUTER_TestActivity)
public class TestActivity extends BaseRecyclerViewActivity {
    List<TestBean> data = new ArrayList<>();


    @Override
    public void initView() {
        super.initView();
        //设置 Header 为 贝塞尔雷达 样式
       // mSmartRefresh.setRefreshHeader(new TaurusHeader(this));

    }

    @Override
    public void initData() {
        super.initData();
        TestBean testBean = new TestBean();
        testBean.setTest("test---------");
        data.add(testBean);
        data.add(testBean);
        data.add(testBean);
        data.add(testBean);
        data.add(testBean);
    }



    @Override
    protected BaseQuickAdapter getRecyclerAdapter() {
        return new TestAdapter(R.layout.item_test,data);
    }
}
