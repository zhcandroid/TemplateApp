package com.common.baselibrary.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * 初始化腾讯webview
 */
public class X5WebView extends WebView {

    private Context mContext;

    private WebViewClient client = new WebViewClient() {
        /**
         * 防止加载网页时调起系统浏览器
         */
        public boolean shouldOverrideUrlLoading(WebView view, String httpurl) {

            if (httpurl.startsWith("http:") || httpurl.startsWith("https:")) {
                view.loadUrl(httpurl);
            }else{
                try{
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(httpurl));
                    mContext.startActivity(intent);
                    return true;
                }catch (Exception e){
                    return false;
                }
            }
            return true;
        }

        @Override
        public void onPageFinished(WebView webView, String s) {
            super.onPageFinished(webView, s);
        }
    };



    @SuppressLint("SetJavaScriptEnabled")
    public X5WebView(Context arg0, AttributeSet arg1) {
        super(arg0, arg1);
        this.mContext = arg0;
        this.setWebViewClient(client);
        initWebViewSettings();
        this.getView().setClickable(true);
        this.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

    }

    private void initWebViewSettings() {
        WebSettings mWebSettings = this.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        mWebSettings.setAllowFileAccess(true);
        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        mWebSettings.setSupportZoom(true);
        mWebSettings.setBuiltInZoomControls(true);
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setSupportMultipleWindows(true);
        mWebSettings.setAppCacheEnabled(true);
        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setGeolocationEnabled(true);
        mWebSettings.setAppCacheMaxSize(Long.MAX_VALUE);
        mWebSettings.setPluginState(WebSettings.PluginState.ON_DEMAND);
        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
    }

    public X5WebView(Context arg0) {
        super(arg0);
        setBackgroundColor(85621);
    }
}
