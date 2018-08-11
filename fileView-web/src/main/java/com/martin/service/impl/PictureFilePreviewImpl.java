package com.martin.service.impl;

import com.google.common.collect.Lists;
import com.martin.service.FileViewService;
import com.martin.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.List;

/**
 * @Author: qienbo
 * @Date: 2018/8/11 下午1:32
 * @Description:
 */
@Service
public class PictureFilePreviewImpl implements FileViewService {

    @Autowired
    FileUtils fileUtils;

    @Override
    public String filePreviewHandle(String url, Model model) {
        String fileKey=(String) RequestContextHolder.currentRequestAttributes().getAttribute("fileKey",0);
        List imgUrls = Lists.newArrayList(url);
        try{
            imgUrls.clear();
            imgUrls.addAll(fileUtils.getRedisImgUrls(fileKey));
        }catch (Exception e){
            imgUrls = Lists.newArrayList(url);
        }
        model.addAttribute("imgurls", imgUrls);
        model.addAttribute("currentUrl",url);
        return "picture";
    }
}
