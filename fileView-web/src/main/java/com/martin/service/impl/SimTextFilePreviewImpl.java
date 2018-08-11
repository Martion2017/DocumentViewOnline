package com.martin.service.impl;

import com.martin.entity.FileAttribute;
import com.martin.service.FileViewService;
import com.martin.utils.FileUtils;
import com.martin.utils.SimTextUtil;
import fr.opensagres.xdocreport.document.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * @Author: qienbo
 * @Date: 2018/8/11 下午1:32
 * @Description:
 */
@Service
public class SimTextFilePreviewImpl implements FileViewService{

    @Autowired
    SimTextUtil simTextUtil;

    @Autowired
    FileUtils fileUtils;

    @Override
    public String filePreviewHandle(String url, Model model){
        FileAttribute fileAttribute=fileUtils.getFileAttribute(url);
        String decodedUrl=fileAttribute.getDecodedUrl();
        String fileName=fileAttribute.getName();
        JSONObject response = simTextUtil.readSimText(decodedUrl, fileName);
        if (0!=response.getInt("code")) {
            model.addAttribute("msg", response.getString("msg"));
            model.addAttribute("fileType",fileAttribute.getSuffix());
            return "fileNotSupported";
        }
        model.addAttribute("ordinaryUrl", response.getString("msg"));
        return "txt";
    }

}
