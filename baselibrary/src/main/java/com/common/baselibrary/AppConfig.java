package com.common.baselibrary;

/**
 * 环境配置
 */
public class AppConfig {

    /**
     * 服务器类型
     * SERVER_DEVELOP       开发环境
     * SERVER_TEST          测试环境
     * SERVER_PRODUCTION    生产环境
     */
    public static final int SERVER_TYPE = ServerType.SERVER_DEVELOP;

    /**
     * 服务器类型
     */
    public class ServerType {
        public static final int SERVER_DEVELOP = 0;//开发环境
        public static final int SERVER_TEST = 1;//测试环境
        public static final int SERVER_PRODUCTION = 2;//生产环境
    }

}
