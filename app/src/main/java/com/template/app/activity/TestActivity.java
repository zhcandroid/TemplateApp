package com.template.app.activity;

import com.common.baselibrary.base.CommonActivity;
import com.template.app.R;

public class TestActivity extends CommonActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.id_titleBar;
    }


}
