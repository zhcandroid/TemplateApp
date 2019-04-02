package com.common.baselibrary.utils;

import android.annotation.SuppressLint;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * 计数器
 */
public class CountDownHelp {

    private long startTime;
    private CountDownCallback countDownCallback;
    private Disposable subscribe;

    public CountDownHelp(long startTime) {
        this.startTime = startTime;
    }

    public void setCountDownCallback(CountDownCallback countDownCallback) {
        this.countDownCallback = countDownCallback;
    }

    @SuppressLint("CheckResult")
    public void countDown() {
        subscribe = Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(startTime + 1)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return startTime - aLong;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (countDownCallback != null) {
                            countDownCallback.countDown(aLong);
                        }
                    }
                });
    }

    public void release() {
        if (subscribe != null && !subscribe.isDisposed()) {
            subscribe.dispose();
        }
        subscribe = null;
        countDownCallback = null;
    }

    public interface CountDownCallback {
        void countDown(long time);
    }
}
