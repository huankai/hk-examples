package com.hk.mapping.web.controller;

import com.hk.commons.util.CollectionUtils;
import com.hk.commons.util.FileUtils;
import com.hk.commons.util.StringUtils;
import com.hk.commons.util.ZipUtils;
import com.hk.config.Configuration;
import com.hk.core.ConfigurationStorage;
import com.hk.core.MetaData;
import com.hk.core.TemplateGenerator;
import com.hk.util.ConnectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.Connection;
import java.util.List;

/**
 * @author: huangkai
 * @date 2018-05-29 20:07
 */
@Controller
@RequestMapping
public class RequestController {

    @Autowired
    private ConfigurationStorage configurationStorage;

    /**
     * 生成第一步，设置连接信息
     *
     * @param modelMap
     * @return
     */
    @RequestMapping({"/", "index"})
    public String index() {
        return "generate_1";
    }

    /**
     * 生成第二步、获取所有的表
     *
     * @param url
     * @param driver
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("generate_2")
    public String getTables(@RequestParam String jdbcUrl, @RequestParam String driver, @RequestParam String username, @RequestParam String password, ModelMap modelMap) {
        Configuration configuration = configurationStorage.getConfiguration();
        configuration.setJdbcUrl(jdbcUrl);
        configuration.setUsername(username);
        configuration.setPassword(password);
        configuration.setDriverName(driver);
        configurationStorage.setConfiguration(configuration);
        List<String> tableNameList = new MetaData(jdbcUrl, username, password, driver).getTableNameList();
        modelMap.put("tableNameList", tableNameList);
        return "generate_2";
    }

    @RequestMapping("generateanddown")
    public ResponseEntity<byte[]> generateAndDown(Configuration configuration) {
        String rootPath = configuration.getRootPath();
        generate(configuration);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDispositionFormData("attachment", "GenerateFile.zip");
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        String folder = configuration.getRootPath() + TemplateGenerator.JAVA_DIR;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipUtils.toZip(folder, baos, true);
        FileUtils.deleteDir(new File(configuration.getRootPath()));
        Configuration configuration_ = configurationStorage.getConfiguration();
        configuration_.setRootPath(rootPath);
        configurationStorage.setConfiguration(configuration_);
        return new ResponseEntity<>(baos.toByteArray(), httpHeaders, HttpStatus.OK);
    }

    private void generate(Configuration configuration) {
        String rootPath = configuration.getRootPath();
        if (!StringUtils.endsWith(rootPath, "/")) {
            rootPath += "/";
        }
        rootPath += System.currentTimeMillis() + "/";
        configuration.setRootPath(rootPath);
        configurationStorage.setConfiguration(configuration);
        Configuration configuration1 = configurationStorage.getConfiguration();
        try (Connection connection = ConnectionUtils.getConnection(configuration.getJdbcUrl(), configuration.getUsername(), configuration.getPassword(), configuration.getDriverName())) {
            TemplateGenerator generator = new TemplateGenerator(configurationStorage);
            List<String> includeTables = configuration.getIncludeTables();
            MetaData metaData = new MetaData(configuration.getJdbcUrl(), configuration.getUsername(), configuration.getPassword(), configuration.getDriverName());
            if (CollectionUtils.isNotEmpty(includeTables)) {
                generator.generate(metaData.getTables(includeTables), true);//生成指定的表名
            } else {
                generator.generate(metaData.getTables());// 生成所有表
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
