package com.martin.common;

import fr.opensagres.xdocreport.document.json.JSONObject;

/**
 * @Author: qienbo
 * @Date: 2018/8/11 下午1:32
 * @Description:
 */
public class BaseController {

    /**
     *  返回json封装
     * @param code 0：成功，其他错误码
     * @param message 返回消息
     * @return
     */
    protected String getJson(int code, String message) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("code", code);
        resultJson.put("msg", message);
        return resultJson.toString();
    }

    /**
     * 返回json封装
     * @param code 0:成功，其他：错误码
     * @param message 返回消息
     * @param content 返回数据
     * @return
     */
    protected String getJson(int code, String message, Object content) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("code", code);
        resultJson.put("msg", message);
        resultJson.put("content", content);
        return resultJson.toString();
    }
}
