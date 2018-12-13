package com.template.app.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.common.baselibrary.base.BaseRecyclerViewActivity;
import com.template.app.AppConfig;
import com.template.app.R;
import com.template.app.adapter.TestAdapter;
import com.template.app.bean.TestBean;

import java.util.ArrayList;
import java.util.List;

@Route(path = AppConfig.AROUTER_TestActivity)
public class TestActivity extends BaseRecyclerViewActivity {
    List<TestBean> data = new ArrayList<>();

    @Override
    public void initData() {
        super.initData();
        ARouter.getInstance().inject(this);
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
