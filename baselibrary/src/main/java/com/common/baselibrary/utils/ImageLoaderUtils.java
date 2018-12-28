package com.common.baselibrary.utils;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.common.baselibrary.R;

public class ImageLoaderUtils {


    /**
     * 加载网络图片
     *
     * @param url       url
     * @param imageView imageView
     * @param imageView transformation 转换器
     */
    @SuppressLint("ResourceAsColor")
    public static void loadImage(ImageView imageView, String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        Glide.with(imageView.getContext())
                .load(url)
                .apply(new RequestOptions()
                        .placeholder(new ColorDrawable(R.color.main_bg_item))
                        .error(new ColorDrawable(Color.WHITE))
                        .fallback(new ColorDrawable(Color.RED)))
                .into(imageView);
    }

    /**
     * 加载圆形
     *
     * @param url       url
     * @param imageView imageView
     */
    public static void loadImageCircle(ImageView imageView, String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        Glide.with(imageView.getContext())
                .load(url)
                .apply(RequestOptions.circleCropTransform()
                        .error(new ColorDrawable(Color.WHITE))
                        .fallback(new ColorDrawable(Color.RED)))
                .into(imageView);
    }



}
