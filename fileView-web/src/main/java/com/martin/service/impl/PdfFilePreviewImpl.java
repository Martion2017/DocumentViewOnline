package com.martin.service.impl;

import com.martin.service.FileViewService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * @Author: qienbo
 * @Date: 2018/8/11 下午1:32
 * @Description:
 */
@Service
public class PdfFilePreviewImpl implements FileViewService{

    @Override
    public String filePreviewHandle(String url, Model model) {
        model.addAttribute("pdfUrl", url);
        return "pdf";
    }
}
