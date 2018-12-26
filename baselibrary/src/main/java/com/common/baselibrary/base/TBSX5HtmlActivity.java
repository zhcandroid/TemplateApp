package com.common.baselibrary.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.ProgressBar;

import com.common.baselibrary.R;
import com.common.baselibrary.R2;
import com.common.baselibrary.mvp.presenter.BasePresenter;
import com.common.baselibrary.utils.WebViewLifecycleUtils;
import com.common.baselibrary.view.X5WebView;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;

import butterknife.BindView;

/**
 * 腾讯X5webView内核浏览器
 */
public class TBSX5HtmlActivity extends CommonActivity {

    @BindView(R2.id.id_webView)
    X5WebView mX5WebView;
    @BindView(R2.id.id_progressBar)
    ProgressBar mWebProgressBar;

    private String mTitle;
    private String mHtmlUrl;

    public static void open(Context context, String title, String url) {
        Intent intent = new Intent(context, TBSX5HtmlActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }


    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.id_titleBar;
    }

    @Override
    public void initView() {
        //设置标题为wevView的标题
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mTitle = extras.getString("title", "");
            mHtmlUrl = extras.getString("url", "");
        }

        mTitleBar.setTitle(TextUtils.isEmpty(mTitle) ? mX5WebView.getTitle() : mTitle);
        mX5WebView.setWebChromeClient(webChromeClient);
        mX5WebView.addJavascriptInterface(new WebAppInterface(TBSX5HtmlActivity.this), "AndroidWebView");
        WebViewPost("https://www.baidu.com");
    }

    WebChromeClient webChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView webView, int newProgress) {

            if (newProgress > 90) {
                mWebProgressBar.setVisibility(View.GONE);//加载完网页进度条消失
            } else {
                mWebProgressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                mWebProgressBar.setProgress(newProgress);//设置进度值
            }
        }


        @Override
        public void onReceivedTitle(WebView webView, String s) {
            super.onReceivedTitle(webView, s);
            mTitleBar.setTitle(TextUtils.isEmpty(mTitle) ? mX5WebView.getTitle() : mTitle);

        }
    };


    /**
     * 原生和js交互接口
     *
     * @author zhc
     */
    public class WebAppInterface {
        Context mContext;

        public WebAppInterface(Context c) {
            mContext = c;
        }

        // 如果target 大于等于API 17，则需要加上如下注解
        @JavascriptInterface
        public void call(String msg) {
            //在js调用后的Java回调线程并不是主线程 将webview操作放在主线程中即可解决异常 WebViewPost（）
        }


    }


    /**
     * 在js调用后的Java回调线程并不是主线程
     * @param commd
     */
    public void WebViewPost(final String commd) {

        mX5WebView.post(new Runnable() {

            @Override
            public void run() {
                mX5WebView.loadUrl(commd);

            }
        });
    }


    /**
     * 返回上个页面
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mX5WebView != null && mX5WebView.canGoBack()) {
                mX5WebView.goBack();
                return true;
            } else {
                return super.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mX5WebView != null)
            WebViewLifecycleUtils.onResume(mX5WebView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mX5WebView != null)
            WebViewLifecycleUtils.onPause(mX5WebView);
    }

    @Override
    protected void onDestroy() {
        //释放资源
        if (mX5WebView != null)
            WebViewLifecycleUtils.onDestroy(mX5WebView);
        super.onDestroy();
    }


}
