package com.template.app.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.template.app.fragment.HomeFragment;

import java.util.HashMap;
import java.util.Map;

public class TabPageIndicatorAdapter extends FragmentPagerAdapter {

    /**
     * Tab标题
     */
    private static final String[] TITLE = new String[] { "头条", "房产", "另一面", "女人",
            "财经", "数码", "情感", "科技" };
    private Map<String, Fragment> fragmentList = null;

    public TabPageIndicatorAdapter(FragmentManager fm) {
        super(fm);
        fragmentList = new HashMap<String, Fragment>();
    }

    @Override
    public Fragment getItem(int position) {
        //新建一个Fragment来展示ViewPager item的内容，并传递参数
        Fragment pageFragment = fragmentList.get(TITLE[position]);
        if (pageFragment == null) {
            pageFragment = new HomeFragment();
            fragmentList.put(TITLE[position], pageFragment);
        }
        return pageFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLE[position % TITLE.length];
    }

    @Override
    public int getCount() {
        return TITLE.length;
    }
}
