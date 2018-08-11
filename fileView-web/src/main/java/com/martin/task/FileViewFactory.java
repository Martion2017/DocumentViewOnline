package com.martin.task;

import com.martin.entity.FileAttribute;
import com.martin.service.FileViewService;
import com.martin.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: qienbo
 * @Date: 2018/8/11 下午3:07
 * @Description:
 * 通过工厂调用不同的类别服务
 * 不同的文件类型需要不同的接口实现类
 */
@Service
public class FileViewFactory {
    @Autowired
    FileUtils fileUtils;

    @Autowired
    ApplicationContext context;

    public FileViewService get(String url) {
        Map<String, FileViewService> filePreviewMap = context.getBeansOfType(FileViewService.class);
        FileAttribute fileAttribute = fileUtils.getFileAttribute(url);
        return filePreviewMap.get(fileAttribute.getType().getInstanceName());
    }
}
