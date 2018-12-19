package com.template.app.fragment;

import android.view.View;
import android.widget.TextView;

import com.common.baselibrary.base.CommonLazyFragment;
import com.common.baselibrary.dialog.AppDialog;
import com.common.baselibrary.dialog.MalertDialog;
import com.common.baselibrary.view.CircleImageView;
import com.template.app.R;

import butterknife.BindView;
import butterknife.OnClick;

public class PersionalFragment extends CommonLazyFragment {
    @BindView(R.id.iv_face)
    CircleImageView mIvFace;
    @BindView(R.id.tv_show_dialog)
    TextView mTvShowDialog;

    MalertDialog mDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_persional;
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

    @OnClick({R.id.iv_face, R.id.tv_show_dialog})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_face:
                break;
            case R.id.tv_show_dialog:
                mDialog = AppDialog.showClearDialog(getActivity(), "show dialog", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                    }
                });
                break;
        }
    }
}
