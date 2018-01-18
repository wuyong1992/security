package com.wuyong.security.dto;

import lombok.Data;

/**
 * created by JianGuo
 * on 2018/1/18
 * description:
 */
@Data
public class FileInfo {
    private String path;

    public FileInfo(String path) {
        this.path = path;
    }
}
