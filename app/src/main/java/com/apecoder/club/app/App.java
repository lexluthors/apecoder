package com.apecoder.club.app;

import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.text.TextUtils;

import com.apecoder.club.base.BaseApplication;
import com.apecoder.club.base.BaseBean;
import com.apecoder.club.bean.UserInfoBean;
import com.apecoder.club.http.ServerConfig;
import com.apecoder.club.util.LogUtils;
import com.apecoder.club.util.SharedPreferencesUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;

/**
 * Description:
 * Data：2018/12/6-18:22
 * Author: Allen
 */
public class App extends BaseApplication {

    private static BaseBean<UserInfoBean> sAccountInfo;
    private static App instance;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //初始化当前登录用户
        sAccountInfo = parseAccount(instance);
        setUpThreePlatformParams();
    }

    /**
     * description: 实例化第三方库
     * author: liujie
     * date: 2017/12/27 15:19
     */
    private void setUpThreePlatformParams() {
        //初始化logger
        LogUtils.logInit(BuildConfig.LOG_DEBUG);
        //测试阶段建议设置成true，发布时设置为false。
//        //实例化图片选择库
//        Album.initialize(AlbumConfig.newBuilder(this)
//                .setAlbumLoader(new MediaLoader())
//                .setLocale(Locale.getDefault())
//                .build());

        //解决android N（>=24）系统以上分享 路径为file://时的 android.os.FileUriExposedException异常
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }


        // 全局 BaseUrl 的优先级低于 Domain-Name header 中单独配置的,其他未配置的接口将受全局 BaseUrl 的影响
        RetrofitUrlManager.getInstance().setGlobalDomain(ServerConfig.NEW_BASE_URL);
    }

    /**
     * 解析当前登录的用户
     *
     * @param context 上下文环境
     * @return AccountInfo
     */
    public static BaseBean<UserInfoBean> parseAccount(Context context) {
        String accountString = (String) SharedPreferencesUtil.get(context, SharedPreferencesConfig.PERSONAL_DATA, "");
        if (!TextUtils.isEmpty(accountString)) {
            sAccountInfo = new Gson().fromJson(accountString, new TypeToken<BaseBean<UserInfoBean>>() {
            }.getType());
            return sAccountInfo;
        }
        return null;
    }

    /**
     * 得到当前登录用户
     */
    public static BaseBean<UserInfoBean> getAccount() {
        if (sAccountInfo == null) {
            return parseAccount(instance);
        }
        return sAccountInfo;
    }

    public static boolean isLogin() {
        return sAccountInfo != null;
    }

    /**
     * 设置当前登录用户
     */
    public static void setAccount(BaseBean<UserInfoBean> accountInfo) {
        sAccountInfo = accountInfo;
    }

    public static App getInstance() {
        return instance;
    }

    public static long getUserId() {
        if (sAccountInfo != null) {
            if (sAccountInfo.getData() != null) {
                return sAccountInfo.getData().getId();
            }
        }
        sAccountInfo = parseAccount(getAppContext());
        if (sAccountInfo != null) {
            if (sAccountInfo.getData() != null) {
                return sAccountInfo.getData().getId();
            }
        }
        return 0;
    }

    public static String getNickName() {
        if (sAccountInfo != null) {
            if (sAccountInfo.getData() != null) {
                return sAccountInfo.getData().getNickName();
            }
        }
        sAccountInfo = parseAccount(getAppContext());
        if (sAccountInfo != null) {
            if (sAccountInfo.getData() != null) {
                return sAccountInfo.getData().getNickName();
            }
        }
        return "";
    }


}
