package com.template.app.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.template.app.R;
import com.template.app.bean.TestBean;

import java.util.List;

public class TestAdapter extends BaseQuickAdapter<TestBean,BaseViewHolder> {
    public TestAdapter(int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestBean item) {
        helper.setText(R.id.tv_test,item.getTest());
    }
}
