package com.common.baselibrary.base;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.common.baselibrary.R;
import com.common.baselibrary.R2;

import butterknife.BindView;

/**
 * 基础viewPager
 * @auther zhc
 */
public abstract class BaseViewPagerFragment extends CommonLazyFragment {

    @BindView(R2.id.tab_nav)
    protected TabLayout mTabNav;

    @BindView(R2.id.base_viewPager)
    protected ViewPager mBaseViewPager;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_viewpager;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {
        BaseViewPagerAdapter adapter = new BaseViewPagerAdapter(getChildFragmentManager(), getPagers());
        mBaseViewPager.setAdapter(adapter);
        mTabNav.setupWithViewPager(mBaseViewPager);
        mBaseViewPager.setCurrentItem(0, true);
    }

    @Override
    protected void initData() {

    }

    protected abstract PagerInfo[] getPagers();


    public static class PagerInfo {
        private String title;
        private Class<?> clx;
        private Bundle args;

        public PagerInfo(String title, Class<?> clx, Bundle args) {
            this.title = title;
            this.clx = clx;
            this.args = args;
        }
    }


    public class BaseViewPagerAdapter extends FragmentPagerAdapter {
        private PagerInfo[] mInfoList;
        private Fragment mCurFragment;

        public BaseViewPagerAdapter(FragmentManager fm, PagerInfo[] infoList) {
            super(fm);
            mInfoList = infoList;
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);
            if (object instanceof Fragment) {
                mCurFragment = (Fragment) object;
            }
        }

        public Fragment getCurFragment() {
            return mCurFragment;
        }

        @Override
        public Fragment getItem(int position) {
            PagerInfo info = mInfoList[position];
            return instantiate(getContext(), info.clx.getName(), info.args);
        }

        @Override
        public int getCount() {
            return mInfoList.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mInfoList[position].title;
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }
    }


}
