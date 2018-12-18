package com.common.baselibrary.utils;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;


public class ImgAnimation {

	public static String TAG = ImgAnimation.class.getSimpleName();

	public static int VISIBLE = 1;
	public static int GONE = 0;

	/**
	 * 
	 * @return top淡出动画
	 */
	public static Animation getTopOutAnimation(final View view, final int style) {
		TranslateAnimation ta = new TranslateAnimation(0, 0, 0,
				-view.getHeight());
		ta.setDuration(300);
		ta.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation animation) {
				if (style == 0) {
					Log.i(TAG, "顶部消失");
					view.setVisibility(View.GONE);
				} else {
					view.setVisibility(View.VISIBLE);
				}
			}

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}
		});
		return ta;
	}

	/**
	 * 
	 * @return top淡入动画
	 */
	public static Animation getTopInAnimation(final View view, final int style) {
		TranslateAnimation ta = new TranslateAnimation(0, 0, -view.getHeight(),
				0);
		ta.setDuration(300);
		ta.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation animation) {
				if (style == 0) {
					view.setVisibility(View.GONE);
				} else {
					Log.i(TAG, "顶部出现");
					view.setVisibility(View.VISIBLE);
				}

			}

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}
		});
		return ta;
	}

	/**
	 * 
	 * @return buttom淡出动画
	 */
	public static Animation getButtomOutAnimation(final View view,
                                                  final int style) {
		TranslateAnimation ta = new TranslateAnimation(0, 0, 0,
				view.getHeight());
		ta.setDuration(300);
		ta.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation animation) {
				if (style == 0) {
					Log.i(TAG, "底部消失");
					view.setVisibility(View.GONE);
				} else {
					view.setVisibility(View.VISIBLE);
				}

			}

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}
		});
		return ta;
	}

	/**
	 * 
	 * @return buttom淡入动画
	 */
	public static Animation getButtomInAnimation(final View view,
                                                 final int style) {
		TranslateAnimation ta = new TranslateAnimation(0, 0, view.getHeight(),
				0);
		ta.setDuration(300);
		ta.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation animation) {
				if (style == 0) {
					view.setVisibility(View.GONE);
				} else {
					Log.i(TAG, "底部出现");
					view.setVisibility(View.VISIBLE);
				}
			}

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}
		});
		return ta;
	}


}
