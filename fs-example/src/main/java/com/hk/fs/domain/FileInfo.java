/**
 *
 */
package com.hk.fs.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kevin
 */
@SuppressWarnings("serial")
@Data
public class FileInfo implements Serializable {

    private String fileName;

    private String filePath;

    private Long fileSize;

    private String ext;

}
