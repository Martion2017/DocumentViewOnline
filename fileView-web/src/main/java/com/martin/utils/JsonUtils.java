package com.martin.utils;

import fr.opensagres.xdocreport.document.json.JSONObject;

/**
 * @Author: qienbo
 * @Date: 2018/8/11 下午3:25
 * @Description:
 */
public class JsonUtils {
    /**
     *  返回json封装
     * @param code 0：成功，其他错误码
     * @param message 返回消息
     * @return
     */
    public static JSONObject getJson(int code, String message) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("code", code);
        resultJson.put("msg", message);
        return resultJson;
    }

    /**
     * 返回json封装
     * @param code 0:成功，其他：错误码
     * @param message 返回消息
     * @param content 返回数据
     * @return
     */
    public static JSONObject getJson(int code, String message, Object content) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("code", code);
        resultJson.put("msg", message);
        resultJson.put("content", content);
        return resultJson;
    }
}
