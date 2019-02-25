package com.common.baselibrary.utils;

import android.view.View;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * rxjava 工具类
 */
public class RxUtils {

    /**
     * View 的点击事件防抖
     * @param view
     */
    public static void onViewClick(final View view){
        RxUtils.clickView(view)
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Void>() {
                    @Override
                    public void accept(Void aVoid) throws Exception {
                        Toast.makeText(view.getContext(), "rx click triggered", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public static Observable<Void> clickView(@NonNull View view) {
        checkNoNull(view);
        return Observable.create(new ViewClickOnSubscribe(view));
    }


    /**
     * 查空
     */
    private static <T> void checkNoNull(T value) {
        if (value == null) {
            throw new NullPointerException("generic value here is null");
        }
    }

    /**
     * 点击事件作为被观察者
     */
    private static class ViewClickOnSubscribe implements ObservableOnSubscribe<Void> {
        private View view;

        public ViewClickOnSubscribe(View view) {
            this.view = view;
        }


        @Override
        public void subscribe(final ObservableEmitter<Void> emitter) throws Exception {
            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //订阅没取消
                    if (!emitter.isDisposed()) {
                        //发送消息
                        emitter.onNext(null);
                    }
                }
            };
            view.setOnClickListener(onClickListener);
        }
    }
}
