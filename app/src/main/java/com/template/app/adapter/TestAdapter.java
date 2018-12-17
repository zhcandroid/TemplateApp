package com.template.app.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.template.app.R;
import com.template.app.bean.TestBean;

public class TestAdapter extends BaseQuickAdapter<TestBean,BaseViewHolder> {
    public TestAdapter(int layoutResId) {
        super(layoutResId);
    }
//    public TestAdapter(int layoutResId, @Nullable List data) {
//        super(layoutResId, data);
//    }

    @Override
    protected void convert(BaseViewHolder helper, TestBean item) {
        helper.setText(R.id.tv_test,item.getTest());
    }
}
