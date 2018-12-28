package com.template.app.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.template.app.R;
import com.template.app.bean.TestBean;

public class TestAdapter extends BaseQuickAdapter<TestBean,BaseViewHolder> {
    public TestAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestBean item) {
        helper.setText(R.id.tv_test,item.name);
    }
}
