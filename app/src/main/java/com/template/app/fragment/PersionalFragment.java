package com.template.app.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.TextView;

import com.common.baselibrary.base.CommonLazyFragment;
import com.common.baselibrary.dialog.AppDialog;
import com.common.baselibrary.dialog.MalertDialog;
import com.common.baselibrary.utils.ImageUtils;
import com.common.baselibrary.view.CircleImageView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.template.app.R;
import com.template.app.arouter.ARouterUIHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

public class PersionalFragment extends CommonLazyFragment {
    @BindView(R.id.iv_face)
    CircleImageView mIvFace;
    @BindView(R.id.tv_show_dialog)
    TextView mTvShowDialog;
    @BindView(R.id.tv_show_indicator)
    TextView mTvShowIndicator;

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

    @OnClick({R.id.iv_face, R.id.tv_show_dialog,R.id.tv_show_indicator})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_face:
                PictureSelector.create(PersionalFragment.this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(1)
                        .enableCrop(true)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
            case R.id.tv_show_dialog:
                mDialog = AppDialog.showClearDialog(getActivity(), "show dialog", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                    }
                });
                break;
            case R.id.tv_show_indicator:
                ARouterUIHelper.openIndicatorActivity();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    if (selectList.size() > 0) {
                        LocalMedia media = selectList.get(0);
                        Bitmap bitmap = ImageUtils.getBitmapByPath(media.getPath());
                        mIvFace.setImageBitmap(bitmap);
                    }
                    break;
            }
        }
    }
}
