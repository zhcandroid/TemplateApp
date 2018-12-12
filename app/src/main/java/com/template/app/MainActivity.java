package com.template.app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.common.baselibrary.base.UiActivity;
import com.common.baselibrary.interf.OnTabReselectListener;
import com.template.app.view.NavigationButton;

public class MainActivity extends UiActivity implements NavFragment.OnNavigationReselectListener{

    private NavFragment mNavBar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        super.initView();
        FragmentManager manager = getSupportFragmentManager();
        mNavBar = ((NavFragment) manager.findFragmentById(R.id.fag_nav));
        mNavBar.setup(this, manager, R.id.main_container, this);
    }

    @Override
    protected int getTitleBarId() {
        return R.id.tv_titleBar;
    }

    @Override
    public void onReselect(NavigationButton navigationButton) {
        Fragment fragment = navigationButton.getFragment();
        if (fragment != null
                && fragment instanceof OnTabReselectListener) {
            OnTabReselectListener listener = (OnTabReselectListener) fragment;
            listener.onTabReselect();
        }
    }
}
