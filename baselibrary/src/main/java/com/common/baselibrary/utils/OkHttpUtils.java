package com.common.baselibrary.utils;

import android.os.Build;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 专为上传文件打造
 */

public class OkHttpUtils<T> {

    private static final String TAG = "OkHttpUtils";

    private OkHttpClient client = null;
    public final static int CONNECT_TIMEOUT =60 * 5;
    public final static int READ_TIMEOUT=60 * 5;
    public final static int WRITE_TIMEOUT=60 * 5;
    private OkHttpUtils() {
        if (client == null) {
            client = new OkHttpClient();
        }
    }

    private static volatile OkHttpUtils sInstance;

    public static OkHttpUtils getInstance() {

        if (sInstance == null) {
            synchronized (OkHttpUtils.class) {
                sInstance = new OkHttpUtils();
            }
        }
        return sInstance;
    }

    public static final String DEFAULT_USER_AGENT = "Mozilla/5.0(Linux; U; Android "
            + Build.VERSION.RELEASE
            + "; "
            + Locale.getDefault().getLanguage()
            + "; "
            + Build.MODEL
            + ") AppleWebKit/533.0 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1";


    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");



    /**
     * 上传图片和其他参数
     * @param files
     * @param params
     * @param actionUrl
     * @param mHnadler
     * @param clazz
     */
    public void uploadImg(List<File> files, Map<String, String> params, String actionUrl, final Handler mHnadler, final Class<T> clazz) {
        // mImgUrls为存放图片的url集合
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
       for(File file: files){
           builder.addFormDataPart("img_urls", file.getName(), RequestBody.create(MEDIA_TYPE_PNG, file));
       }

        //添加其它信息
        Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            builder.addFormDataPart(entry.getKey(), entry.getValue());
        }

        MultipartBody requestBody = builder.build();
        //构建请求
        Request request = new Request.Builder()
                .url(actionUrl)//地址
                .addHeader("User-Agent", DEFAULT_USER_AGENT)
                .addHeader("cookie", "")//TODO
                .post(requestBody)//添加请求体
                .build();
        client.newBuilder().readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)//设置写的超时时间
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)//设置连接超时时间
                .build()
                .newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = Message.obtain();
                msg.what = 0;
                msg.obj = e.getLocalizedMessage();
                mHnadler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message msg = Message.obtain();
                msg.what = 1;
                String body = response.body().string();
                msg.obj = new Gson().fromJson(body, clazz);
                mHnadler.sendMessage(msg);
            }
        });

    }

}
