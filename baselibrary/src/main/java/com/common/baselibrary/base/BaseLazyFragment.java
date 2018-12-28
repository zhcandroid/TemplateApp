package com.common.baselibrary.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;

/**
 *  isOpenLazyLoad() 是否开启懒加载 默认不开启
 *  注意: 在ViewPager中时使用时建议打开
 *  desc : Fragment懒加载基类
 */
public abstract class BaseLazyFragment extends Fragment {

    private boolean isLazyLoad = false;//是否已经懒加载
    private View mRootView;//根布局
    public Activity mActivity;//Activity对象


    /**
     * 获得全局的，防止使用getActivity()为空
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity) context;
    }

    public Activity getSupportActivity() {
        return mActivity;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (mRootView == null && getLayoutId() > 0) {
            mRootView = inflater.inflate(getLayoutId(), null);
        }

        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }

        return mRootView;
    }

    @Override
    public View getView() {
        return mRootView;
    }

    protected boolean isLazyLoad() {
        return isLazyLoad;
    }

    /**
     * 是否在Fragment使用沉浸式
     */
    protected boolean isStatusBarEnabled() {
        return false;
    }

    /**
     *  是否开启懒加载 默认不开启
     * @return
     */
    protected boolean isOpenLazyLoad() {
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mActivity = null;
        mRootView = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(isOpenLazyLoad()){
            //当打开懒加载时 只有满足一下条件才会初始化数据 init（）
            if (isVisibleToUser && !isLazyLoad() && getView() != null) {
                isLazyLoad = true;
                init();
            }
        }else{
            init();
        }


    }

    private boolean isVisibleToUser;

    /**
     * 这里有一个很大的坑 妈蛋 这个函数只有结合ViewPager时才起作用
     * 所以在我们的mainActivity里 永远不会调用
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if (isVisibleToUser && !isLazyLoad() && getView() != null) {
            isLazyLoad = true;
            init();
        }

    }

    public boolean isVisibleToUser() {
        return isVisibleToUser;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //解决java.lang.IllegalStateException: Activity has been destroyed 的错误
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    protected void init() {
        initView();
        initData();
    }

    //引入布局
    protected abstract int getLayoutId();

    //标题栏id
    protected abstract int getTitleBarId();

    //初始化控件
    protected abstract void initView();

    //初始化数据
    protected abstract void initData();

    /**
     * 根据资源id获取一个View
     */
    protected <T extends View> T findViewById(@IdRes int id) {
        return (T) getView().findViewById(id);
    }

    protected <T extends View> T findActivityViewById(@IdRes int id) {
        return (T) mActivity.findViewById(id);
    }

    /**
     * 跳转到其他Activity
     *
     * @param cls          目标Activity的Class
     */
    public void startActivity(Class<? extends Activity> cls) {
        startActivity(new Intent(getContext(), cls));
    }

    /**
     * Fragment返回键被按下时回调
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //默认不拦截按键事件，传递给Activity
        return false;
    }
}