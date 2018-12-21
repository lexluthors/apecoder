package com.apecoder.club.app;

/**
 * 常用的常量
 * Created by Tony on 2017/12/27.
 */

public class BuildConfig {
    public static final boolean DEBUG = Boolean.parseBoolean("true");
    public static final String APPLICATION_ID = "jaydenxiao.com.androidfire";
    public static final String BUILD_TYPE = "debug";
    public static final String FLAVOR = "";
    public static final int VERSION_CODE = 4;
    public static final String VERSION_NAME = "1.0.3";
    // Fields from build type: debug  发布改为false
    public static final boolean LOG_DEBUG = true;
    /**
     * The constant DEBUG_TAG.
     */
    public static final String DEBUG_TAG = "logger";// LogCat的标记
    // 首页fragment索引值
    public static final int HOME_FRAGMENT_INDEX = 0;
    public static final String SHOP_ID = "shop_id";// 店铺id
    //浏览器标识
    public static final String WEBVIEW_FLAG = "native_app";
}
