package com.template.app.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.common.baselibrary.base.UiActivity;
import com.common.baselibrary.interf.OnTabReselectListener;
import com.common.baselibrary.mvp.presenter.BasePresenter;
import com.template.app.R;
import com.template.app.fragment.NavFragment;
import com.template.app.view.NavigationButton;

public class MainActivity extends UiActivity implements NavFragment.OnNavigationReselectListener{

    private NavFragment mNavBar;

    /**
     * 如果使用mvp架构的话 传入Presenter对象即可
     * @return
     */
    @Override
    public BasePresenter getPresenter() {
        return null;
    }

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
        return R.id.id_titleBar;
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
