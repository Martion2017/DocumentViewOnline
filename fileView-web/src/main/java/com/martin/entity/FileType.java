package com.martin.entity;

/**
 * @Author: qienbo
 * @Date: 2018/8/11 下午12:56
 * @Description:
 * 文件类型
 */
public enum FileType {
    picture("pictureFilePreviewImpl"),
    compress("compressFilePreviewImpl"),
    office("officeFilePreviewImpl"),
    simText("simTextFilePreviewImpl"),
    pdf("pdfFilePreviewImpl"),
    other("otherFilePreviewImpl"),
    media("mediaFilePreviewImpl");

    private String instanceName;
    FileType(String instanceName){
        this.instanceName=instanceName;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }
}
