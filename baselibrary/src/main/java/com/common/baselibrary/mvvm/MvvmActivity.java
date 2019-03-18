package com.common.baselibrary.mvvm;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.common.baselibrary.base.CommonActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * view中引用viewmodel示例 注意view model 中不能引用view
 * 同样model也不可以引用viewmodel 他们之间最好不好相互引用
 *
 * 参考 https://github.com/SelfZhangTQ/T-MVVM
 *
 * 由于没有真正使用mvvm开发模式 存在未知问题********
 * @param <T>
 */
public abstract  class MvvmActivity<T extends BaseViewModel> extends CommonActivity {

    /**
     * viewModel
     */
    protected T mViewModel;


    protected Object mStateEventKey;

    protected String mStateEventTag;

    private List<Object> eventKeys = new ArrayList<>();

    @Override
    protected void init() {
        mViewModel = VMProviders(this, (Class<T>) TUtil.getInstance(this, 0));
        if (null != mViewModel) {
            dataObserver();
            mStateEventKey = getStateEventKey();
            mStateEventTag = getStateEventTag();
            eventKeys.add(new StringBuilder((String) mStateEventKey).append(mStateEventTag).toString());
            LiveBus.getDefault().subscribe(mStateEventKey, mStateEventTag).observe(this, observer);
        }
        super.init();
    }


    protected <T extends ViewModel> T VMProviders(AppCompatActivity fragment, @NonNull Class modelClass) {
        return (T) ViewModelProviders.of(fragment).get(modelClass);

    }

    /**
     * ViewPager +fragment tag
     */
    protected String getStateEventTag() {
        return "";
    }

    /**
     * get state page event key
     */
    protected abstract Object getStateEventKey();

    /**
     * 让子类去实现liveData的数据监听
     */
    protected abstract void dataObserver();

    public class StateConstants {
        public static final String NET_WORK_STATE = "1";
        public static final String ERROR_STATE = "2";
        public static final String LOADING_STATE = "3";
        public static final String SUCCESS_STATE = "4";
    }
    protected Observer observer = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String state) {
            if (!TextUtils.isEmpty(state)) {
                //
                if (StateConstants.ERROR_STATE.equals(state)) {
                    //展示错误页面
                } else if (StateConstants.NET_WORK_STATE.equals(state)) {
                    //展示网络错误
                } else if (StateConstants.LOADING_STATE.equals(state)) {
                    //展示loading
                } else if (StateConstants.SUCCESS_STATE.equals(state)) {
                    //展示成功
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (eventKeys != null && eventKeys.size() > 0) {
            for (int i = 0; i < eventKeys.size(); i++) {
                LiveBus.getDefault().clear(eventKeys.get(i));
            }
        }
    }
}
