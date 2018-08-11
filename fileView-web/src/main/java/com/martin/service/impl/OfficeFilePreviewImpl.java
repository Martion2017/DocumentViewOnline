package com.martin.service.impl;

import com.martin.entity.FileAttribute;
import com.martin.service.FileViewService;
import com.martin.utils.DownloadUtils;
import com.martin.utils.FileUtils;
import com.martin.utils.OfficeToPdf;
import fr.opensagres.xdocreport.document.json.JSONObject;
import org.jodconverter.office.OfficeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import java.io.File;

/**
 * @Author: qienbo
 * @Date: 2018/8/11 下午1:32
 * @Description:
 */
@Service
public class OfficeFilePreviewImpl implements FileViewService {

    @Autowired
    FileUtils fileUtils;

    @Value("${file.dir}")
    String fileDir;

    @Autowired
    DownloadUtils downloadUtils;

    @Autowired
    private OfficeToPdf officeToPdf;

    @Override
    public String filePreviewHandle(String url, Model model) {
        FileAttribute fileAttribute=fileUtils.getFileAttribute(url);
        String suffix=fileAttribute.getSuffix();
        String fileName=fileAttribute.getName();
        String decodedUrl=fileAttribute.getDecodedUrl();
        boolean isHtml = suffix.equalsIgnoreCase("xls") || suffix.equalsIgnoreCase("xlsx");
        String pdfName = fileName.substring(0, fileName.lastIndexOf(".") + 1) + (isHtml ? "html" : "pdf");
        // 判断之前是否已转换过，如果转换过，直接返回，否则执行转换
        if (!fileUtils.listConvertedFiles().containsKey(pdfName)) {
            String filePath = fileDir + fileName;
            if (!new File(filePath).exists()) {
                JSONObject res = downloadUtils.downLoad(decodedUrl, suffix, null);
                if (0!=res.getInt("code")) {
                    model.addAttribute("msg", res.getString("msg"));
                    return "fileNotSupported";
                }
                filePath = res.getString("content");
            }
            String outFilePath = fileDir + pdfName;
            if (StringUtils.hasText(outFilePath)) {
                try {
                    officeToPdf.openOfficeToPDF(filePath, outFilePath);
                } catch (OfficeException e) {
                    e.printStackTrace();
                }
                File f = new File(filePath);
                if (f.exists()) {
                    f.delete();
                }
                if (isHtml) {
                    // 对转换后的文件进行操作(改变编码方式)
                    fileUtils.doActionConvertedFile(outFilePath);
                }
                // 加入缓存
                fileUtils.addConvertedFile(pdfName, fileUtils.getRelativePath(outFilePath));
            }
        }
        model.addAttribute("pdfUrl", pdfName);
        return isHtml ? "html" : "pdf";
    }
}
