/**
 * 
 */
package com.hk.fs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hk.commons.files.FileHandler;
import com.hk.commons.files.LocalFileHandler;

/**
 * @author huangkai
 *
 */
@SpringBootApplication
public class FileApplication {

	/**
	 * 文件上传基本路径
	 */
	@Value("${fs.upload.basepath}")
	private String basePath;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(FileApplication.class, args);
	}
	

	/**
	 * 本地文件处理器
	 * 
	 * @return
	 */
	@Bean
	public FileHandler fileHandler() {
		return new LocalFileHandler(basePath);
	}

}
