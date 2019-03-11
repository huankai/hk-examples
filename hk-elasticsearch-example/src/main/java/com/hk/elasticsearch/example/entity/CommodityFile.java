package com.hk.elasticsearch.example.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author huangkai
 * @date 2019/3/11 14:45
 */
@Data
public class CommodityFile implements Serializable {

    private String id;

    private String fileName;

    private String filePath;
}
