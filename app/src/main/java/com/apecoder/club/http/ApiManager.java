package com.apecoder.club.http;

import com.apecoder.club.app.App;
import com.apecoder.club.util.DisplayUtil;
import com.apecoder.club.util.LogUtils;
import com.apecoder.club.util.SystemUtils;
import com.apecoder.club.util.VersionUtils;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * description:
 * author: Allen
 * date: 2018/11/5 12:23
 */
public class ApiManager {
    public static final String API_VERSION = "1.0"; // 当前API版本号

    // OkHttp3 的监听
    private static class LogInterceptor implements Interceptor {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            okhttp3.Response response = chain.proceed(chain.request());
            okhttp3.MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            Request request = chain.request();
//            LogUtils.loge(String.format("Sending request %s on %s%n%s", request.url(),  chain.connection(), request.headers()));
            LogUtils.loge(String.format("链接地址>>>>>%s", request.url()) + "\n" + content);
            return response.newBuilder().body(okhttp3.ResponseBody.create(mediaType, content)).build();
        }
    }

    private static class RequestInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request()
                    .newBuilder().
                            addHeader("app_name", "apecoder")
                    .addHeader("app_version", VersionUtils.getVersionName(App.getAppContext()))
                    .addHeader("platform", "android")
                    .addHeader("datetime", String.valueOf(System.currentTimeMillis()))
                    .addHeader("device_name", SystemUtils.getDeviceName())
                    .addHeader("ticks", "com.apecoder.club")
                    .addHeader("did", SystemUtils.getDeviceId())
                    .addHeader("system_version", SystemUtils.getOSVersion())
                    .addHeader("api_version", API_VERSION)
                    .addHeader("geo_country", "china")
                    .addHeader("geo_province", "guangdong")
                    .addHeader("geo_city", "shenzhen")
                    .addHeader("screen", DisplayUtil.getScreenWidth(App.getAppContext()) + "*" + DisplayUtil.getScreenHeight(App.getAppContext()))
                    .build();
            return chain.proceed(request);
        }
    }

    public static File cacheDirectory = new File(App.getAppContext()
            .getCacheDir().getAbsolutePath(), "HttpCache");
    // 初始化OkHttp3的客户端

    public static OkHttpClient client = RetrofitUrlManager.getInstance().with(new OkHttpClient.Builder()).build().newBuilder().addInterceptor(new LogInterceptor()).addInterceptor(new RequestInterceptor()).addNetworkInterceptor(new StethoInterceptor())
            .cache(new Cache(cacheDirectory, 1024 * 1024 * 50)).retryOnConnectionFailure(true).connectTimeout(18, TimeUnit.SECONDS).writeTimeout(18, TimeUnit.SECONDS).readTimeout(18, TimeUnit.SECONDS).build();

    // 初始化Retrofit
    private static final Retrofit sRetrofit = new Retrofit.Builder().baseUrl(ServerConfig.NEW_BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create())// 加入fastjson解析
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 使用RxJava作为回调适配器
            .build();

    public static APIFunctionManager mProfileApi = sRetrofit.create(APIFunctionManager.class); // 所有接口方法管理模块

}
