package com.hk.elasticsearch.example.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * @author huangkai
 * @date 2019/3/11 14:45
 */
@Data
@SuppressWarnings("serial")
public class CommodityFile implements Serializable {

    private String id;

    private String fileName;

    private String filePath;
}
