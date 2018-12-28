package com.common.baselibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.common.baselibrary.interf.BaseFragmentInterface;
import com.common.baselibrary.mvp.presenter.BasePresenter;
import com.common.baselibrary.mvp.view.IView;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 碎片基础类
 */
public abstract  class BaseFragment extends Fragment implements
        View.OnClickListener, BaseFragmentInterface ,IView {
    public static final int STATE_NONE = 0;
    public static final int STATE_REFRESH = 1;
    public static final int STATE_LOADMORE = 2;
    public static final int STATE_NOMORE = 3;
    public static final int STATE_PRESSNONE = 4;// 正在下拉但还没有到刷新的状态
    public static int mState = STATE_NONE;

    protected Context mContext;
    protected View mRootView;
    protected Bundle mBundle;
    protected LayoutInflater mInflater;
    boolean isSaveFragementState = true;
    private Unbinder mBinder;

    protected BasePresenter presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;

        //初始化Presenter
        presenter = getPresenter();
        if (presenter != null) {
            // 绑定View引用
            presenter.attachView(this);
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
        if (presenter != null) {
            // 断开View引用
            presenter.detachView();
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
        mBundle = getArguments();
        initBundle(mBundle);
    }
    public abstract BasePresenter getPresenter();

    protected void init(Bundle savedInstanceState) {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.mInflater = inflater;
        /**
         * 这段代码就是返回rootView的。即当rootView==null，即第一次创建时，就利用inflater.inflate()来创建初始化状态的视图，
         * 当下次再进到这个界面时,比如下面的通过回退操作进入到fragment1时，这时候的rootView就不再是空了。
         * 但在onCreateView()中返回的视图是要添加到ViewTree中去的。而这里的rootView视图在上次已经添加到里面去了，
         * 一个视图实例不能被add两次，不然就会被下面这个错误！所以，我们针对这种情况，如果rootView已经存在于ViewTree中的时候，要先从ViewTree中移除。
         */
        if (mRootView != null && isSaveFragementState()) {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (parent != null)
                parent.removeView(mRootView);
        }else{
            mRootView = mInflater.inflate(getLayoutId(),container,false);
            onBindViewBefore(mRootView);
            mBinder= ButterKnife.bind(this, mRootView);
            if (savedInstanceState != null)
                onRestartInstance(savedInstanceState);
            initView(mRootView);
            initData();
        }
        return mRootView;
    }



    /**
     * 是否保存fragment的状态
     * @return
     */
    protected abstract boolean isSaveFragementState();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mBinder != null){
            mBinder.unbind();
        }


    }

    protected void initBundle(Bundle bundle) {

    }

    protected void onRestartInstance(Bundle bundle) {

    }
    protected void onBindViewBefore(View root) {
        // ...
    }
    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(getClass().getName());
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(getActivity().getLocalClassName());
    }

    protected int getLayoutId() {
        return 0;
    }

    protected View inflateView(int resId) {
        return this.mInflater.inflate(resId, null);
    }

    public boolean onBackPressed() {
        return false;
    }


}
