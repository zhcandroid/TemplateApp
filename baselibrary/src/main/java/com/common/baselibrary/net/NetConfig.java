package com.common.baselibrary.net;

import com.common.baselibrary.AppConfig;

/**
 * Describe：网络请求URL
 */
public class NetConfig {


    /**
     * 响应的返回key
     */
    public class Code {
        public static final String SUCCESS = "success";
        public static final String MSG = "errorMsg";
        public static final String CODE = "errorCode";
        public static final String MODEL = "data";
    }

    /**
     * H5界面
     */
    public class Html {

    }

    /**
     * 网络请求Url
     */
    public static class Url {

        //自己服务器IP
        public static String MY_SERVICE_URL = "http://sdk.xiaoyuyu.com.cn";

        //服务器地址
        interface BaseUrl {
            String SERVER_DEVELOP = "http://www.wanandroid.com";
            String SERVER_TEST = "";
            String SERVER_PRODUCTION = "";
        }

        /**
         * 返回服务器基础地址
         */
        static String getBaseUrl() {
            switch (AppConfig.SERVER_TYPE) {
                case AppConfig.ServerType.SERVER_DEVELOP:
                    return BaseUrl.SERVER_DEVELOP;
                case AppConfig.ServerType.SERVER_TEST:
                    return BaseUrl.SERVER_TEST;
                case AppConfig.ServerType.SERVER_PRODUCTION:
                    return BaseUrl.SERVER_PRODUCTION;
            }
            return BaseUrl.SERVER_PRODUCTION;
        }


    }

}