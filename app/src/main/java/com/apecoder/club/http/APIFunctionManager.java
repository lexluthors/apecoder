package com.apecoder.club.http;


import com.apecoder.club.base.BaseBean;
import com.apecoder.club.bean.ArticleEntity;
import com.apecoder.club.bean.ArticleJerry;
import com.apecoder.club.bean.UserInfoBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Tony on 2018/1/15.
 */

public interface APIFunctionManager {

    /**
     * description: 登录接口
     * author: liujie
     * date: 2018/1/16 10:19
     */
    @FormUrlEncoded
    @POST("userLogin")
    Observable<BaseBean<UserInfoBean>> login(@FieldMap Map<String, String> params);

    /**
     * description: 注册接口
     * author: Allen
     * date: 2018/11/6 16:46
     */
    @FormUrlEncoded
    @POST("userRegister")
    Observable<BaseBean<UserInfoBean>> register(@FieldMap Map<String, String> params);

    /**
     * 根据category获取列表
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("articlesByCategory")
    Observable<BaseBean<List<ArticleEntity>>> getArticleList(@FieldMap Map<String, Object> params);
    
    /** 
     * description: 获取全部文章列表 Category为-1
     * author: Allen
     * date: 2018/12/21 11:43
     */
    @FormUrlEncoded
    @POST("getAllArticles")
    Observable<BaseBean<List<ArticleEntity>>> getAllArticles(@FieldMap Map<String, Object> params);

    /**
     * 磊子给的接口，抓取数据用的
     * @param params
     * @return
     */
    @Headers({"Domain-Name: douban"}) // 加上 Domain-Name header
    @FormUrlEncoded
    @POST("getArticleList")
    Observable<BaseBean<List<ArticleJerry>>> getLeiZiArticleList(@FieldMap Map<String, Object> params);

    /**
     * 批量上传文章
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("batchSaveArticle")
    Observable<BaseBean<Object>> batchSaveArticle(@FieldMap Map<String, Object> params);

}
