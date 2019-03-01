package com.template.app.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.common.baselibrary.base.CommonLazyFragment;
import com.common.baselibrary.utils.ImageLoaderUtils;
import com.hjq.toast.ToastUtils;
import com.template.app.R;
import com.template.app.arouter.ARouterUIHelper;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends CommonLazyFragment {
    @BindView(R.id.tv_btn)
    TextView tvBtn;
    @BindView(R.id.banner)
    ImageView iv_banner;

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

        String imgUrl = "http://pic9.photophoto.cn/20081229/0034034829945374_b.jpg";
        ImageLoaderUtils.loadImage(iv_banner, imgUrl);

    }

    @Override
    protected void initData() {
    }

    @OnClick({R.id.tv_btn, R.id.banner})
    public void onClick(View view) {
//        ARouterUIHelper.openTestActivity();
        ARouterUIHelper.openTestActivity(getActivity(), new NavCallback() {
            @Override
            public void onFound(Postcard postcard) {
            }

            @Override
            public void onLost(Postcard postcard) {
            }

            @Override
            public void onArrival(Postcard postcard) {
            }

            @Override
            public void onInterrupt(Postcard postcard) {
            }
        });
    }
}
