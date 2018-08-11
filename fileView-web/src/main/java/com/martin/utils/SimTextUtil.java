package com.martin.utils;

import fr.opensagres.xdocreport.document.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


 /**
 * @Author: qienbo
 * @Date: 2018/8/11 下午1:32
 * @Description:
  *
  *  读取类文本文件
 */
@Component
public class SimTextUtil {
    @Value("${file.dir}")
    String fileDir;
    @Autowired
    DownloadUtils downloadUtils;

    public JSONObject readSimText(String url, String fileName){
        JSONObject response = downloadUtils.downLoad(url, "txt", fileName);
        return response;
    }
}
