package com.martin.task;

import com.martin.entity.FileAttribute;
import com.martin.entity.FileType;
import com.martin.service.FileViewService;
import com.martin.utils.FileUtils;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ExtendedModelMap;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: qienbo
 * @Date: 2018/8/11 下午3:15
 * @Description:
 */
@Service
public class FileConverQueueTask {
    Logger logger= LoggerFactory.getLogger(getClass());
    public static final String queueTaskName="FileConverQueueTask";

    @Autowired
    FileViewFactory fileViewFactory;

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    FileUtils fileUtils;

    @PostConstruct
    public void startTask(){
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(new ConverTask(fileViewFactory,redissonClient,fileUtils));
    }

    class  ConverTask implements Runnable{

        FileViewFactory fileViewFactory;

        RedissonClient redissonClient;

        FileUtils fileUtils;

        public ConverTask(FileViewFactory fileViewFactory, RedissonClient redissonClient,FileUtils fileUtils) {
            this.fileViewFactory = fileViewFactory;
            this.redissonClient = redissonClient;
            this.fileUtils=fileUtils;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    final RBlockingQueue<String> queue = redissonClient.getBlockingQueue(FileConverQueueTask.queueTaskName);
                    String url = queue.take();
                    if(url!=null){
                        FileAttribute fileAttribute=fileUtils.getFileAttribute(url);
                        logger.info("正在处理转换任务，文件名称【{}】",fileAttribute.getName());
                        FileType fileType=fileAttribute.getType();
                        if(fileType.equals(FileType.compress) || fileType.equals(FileType.office)){
                            FileViewService fileViewService=fileViewFactory.get(url);
                            fileViewService.filePreviewHandle(url,new ExtendedModelMap());
                        }
                    }
                } catch (Exception e) {
                    try {
                        Thread.sleep(1000*10);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                    e.printStackTrace();
                }
            }
        }
    }

}
