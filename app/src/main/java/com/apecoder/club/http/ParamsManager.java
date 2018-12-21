package com.apecoder.club.http;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tony on 2018/1/15.
 */

public class ParamsManager {

    public static Map<String, Object> getCateParams(int page, int category) {
        Map<String,Object> params = new HashMap<>();
        params.put("page", page);
        params.put("pageSize", 10);
        params.put("category", category);
        return params;
    }
    public static Map<String, Object> getListParams(int page, int pageSize) {
        Map<String,Object> params = new HashMap<>();
        params.put("page", page);
        params.put("pageSize", pageSize);
        return params;
    }

    public static Map<String, Object> getLeiziParams(int page, int size) {
        Map<String,Object> params = new HashMap<>();
        params.put("page", page);
        params.put("size", size);
        return params;
    }
    public static Map<String, Object> getJsonArrayParams(String jsonArray) {
        Map<String,Object> params = new HashMap<>();
        params.put("jsonArray", jsonArray);
        return params;
    }

}
