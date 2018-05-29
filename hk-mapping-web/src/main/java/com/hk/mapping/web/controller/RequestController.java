package com.hk.mapping.web.controller;

import com.hk.commons.util.CollectionUtils;
import com.hk.config.Configuration;
import com.hk.core.MetaData;
import com.hk.core.TemplateGenerator;
import com.hk.entity.Table;
import com.hk.util.ConfigurationUtils;
import com.hk.util.ConnectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Connection;
import java.util.List;

/**
 * @author: huangkai
 * @date 2018-05-29 20:07
 */
@Controller
@RequestMapping
public class RequestController {

    @ModelAttribute("configuration")
    public Configuration configuration() {
        return ConfigurationUtils.getConfiguration();
    }

    @RequestMapping({"/", "index"})
    public String index(ModelMap modelMap) {
        return "index";
    }

    @RequestMapping("gengrate")
    public String generate(Configuration configuration,ModelMap modelMap) {
        ConfigurationUtils.setConfiguration(configuration);
        try (Connection connection = ConnectionUtils.getConnection()) {
            List<Table> tables;
            List<String> includeTables = configuration.getIncludeTables();
            if (CollectionUtils.isNotEmpty(includeTables)) {
                tables = new MetaData(connection).getTables(includeTables); //生成指定的表名
            } else {
                tables = new MetaData(connection).getTables(); // 生成所有表
            }
            new TemplateGenerator().generate(tables);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        modelMap.put("rootPath",configuration.getRootPath());
        return "success";
    }

}
