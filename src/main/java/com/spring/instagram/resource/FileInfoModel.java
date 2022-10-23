package com.spring.instagram.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileInfoModel {
    private String filename;
    private String url;
    private Long size;
}
