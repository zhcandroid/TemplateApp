package com.template.app.fragment;

import android.widget.TextView;

import com.common.baselibrary.base.CommonLazyFragment;
import com.template.app.R;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends CommonLazyFragment {
    @BindView(R.id.tv_btn)
    TextView tvBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.tv_btn)
    public void onClick() {
    }
}
