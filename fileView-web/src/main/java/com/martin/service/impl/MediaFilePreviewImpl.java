package com.martin.service.impl;

import com.martin.service.FileViewService;
import com.martin.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * @Author: qienbo
 * @Date: 2018/8/11 下午1:32
 * @Description:
 */
@Service
public class MediaFilePreviewImpl implements FileViewService {

    @Autowired
    FileUtils fileUtils;

    @Override
    public String filePreviewHandle(String url, Model model) {
        model.addAttribute("mediaUrl", url);
        return "media";
    }


}
