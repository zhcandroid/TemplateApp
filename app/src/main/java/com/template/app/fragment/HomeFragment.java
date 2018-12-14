package com.template.app.fragment;

import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.common.baselibrary.base.CommonLazyFragment;
import com.hjq.toast.ToastUtils;
import com.template.app.AppConfig;
import com.template.app.R;
import com.template.app.arouter.ARouterUIHelper;
import com.template.app.arouter.ARouterUriManger;

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
        ARouterUIHelper.openTestActivity();
    }
}
