package com.martin.service.impl;

import com.martin.entity.FileAttribute;
import com.martin.service.FileViewService;
import com.martin.utils.DownloadUtils;
import com.martin.utils.FileUtils;
import com.martin.utils.ZipReader;
import fr.opensagres.xdocreport.document.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

/**
 * @Author: qienbo
 * @Date: 2018/8/11 下午11:32
 * @Description:
 */
@Service
public class CompressFilePreviewImpl implements FileViewService{

    @Autowired
    FileUtils fileUtils;

    @Autowired
    DownloadUtils downloadUtils;

    @Autowired
    ZipReader zipReader;

    @Override
    public String filePreviewHandle(String url, Model model) {
        FileAttribute fileAttribute=fileUtils.getFileAttribute(url);
        String fileName=fileAttribute.getName();
        String decodedUrl=fileAttribute.getDecodedUrl();
        String suffix=fileAttribute.getSuffix();
        String fileTree = null;
        // 判断文件名是否存在(redis缓存读取)
        if (!StringUtils.hasText(fileUtils.getConvertedFile(fileName))) {
            JSONObject str = downloadUtils.downLoad(decodedUrl, suffix, fileName);
            if (0!=str.getInt("code")) {
                model.addAttribute("msg", str.getString("msg"));
                return "fileNotSupported";
            }
            String filePath = str.getString("content");
            if ("zip".equalsIgnoreCase(suffix) || "jar".equalsIgnoreCase(suffix) || "gzip".equalsIgnoreCase(suffix)) {
                fileTree = zipReader.readZipFile(filePath, fileName);
            } else if ("rar".equalsIgnoreCase(suffix)) {
                fileTree = zipReader.unRar(filePath, fileName);
            }
            fileUtils.addConvertedFile(fileName, fileTree);
        } else {
            fileTree = fileUtils.getConvertedFile(fileName);
        }
        if (null != fileTree) {
            model.addAttribute("fileTree", fileTree);
            return "compress";
        } else {
            model.addAttribute("msg", "压缩文件类型不受支持，尝试在压缩的时候选择RAR4格式");
            return "fileNotSupported";
        }
    }
}
