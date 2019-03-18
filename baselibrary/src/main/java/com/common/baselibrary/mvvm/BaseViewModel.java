package com.common.baselibrary.mvvm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

/**
 * mvvm架构的基础vm
 */
public class BaseViewModel extends AndroidViewModel {

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * viewModel销毁时 解除耗时操作
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        //取消耗时操作 如网络请求
    }
}
