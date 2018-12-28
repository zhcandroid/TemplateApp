package com.template.app.activity;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.common.baselibrary.base.CommonActivity;
import com.common.baselibrary.mvp.presenter.BasePresenter;
import com.template.app.R;
import com.template.app.adapter.TabPageIndicatorAdapter;
import com.template.app.arouter.ARouterUriManger;
import com.viewpagerindicator.TabPageIndicator;

import butterknife.BindView;

@Route(path = ARouterUriManger.AROUTER_IndicatorActivity)
public class IndicatorActivity extends CommonActivity {
    @BindView(R.id.indicator)
    TabPageIndicator mIndicator;
    @BindView(R.id.pager)
    ViewPager mPager;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_indicator;
    }

    @Override
    protected int getTitleBarId() {
       return R.id.id_titleBar;
    }

    @Override
    public void initView() {
        super.initView();

    }

    @Override
    public void initData() {
        super.initData();
        //ViewPager的adapter
        TabPageIndicatorAdapter adapter = new TabPageIndicatorAdapter(getSupportFragmentManager());
        mPager.setAdapter(adapter);

        //实例化TabPageIndicator然后设置ViewPager与之关联
        mIndicator.setViewPager(mPager);
        mIndicator.setVisibility(View.VISIBLE);
    }
}
