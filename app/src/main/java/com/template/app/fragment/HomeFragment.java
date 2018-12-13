package com.template.app.fragment;

import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.common.baselibrary.base.CommonLazyFragment;
import com.hjq.toast.ToastUtils;
import com.template.app.AppConfig;
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
        ARouter.getInstance().inject(this);
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.tv_btn)
    public void onClick() {
        ToastUtils.show("1111111");
        ARouter.getInstance().build(AppConfig.AROUTER_TestActivity).navigation();
    }
}
