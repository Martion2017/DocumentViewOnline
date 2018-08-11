package com.martin.service;

import org.springframework.ui.Model;

/**
 * @Author: qienbo
 * @Date: 2018/8/11 下午3:11
 * @Description:
 */
public interface FileViewService {
    String filePreviewHandle(String url, Model model);
}
