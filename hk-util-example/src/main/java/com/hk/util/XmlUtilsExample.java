package com.hk.util;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.hk.commons.util.IDGenerator;
import com.hk.commons.util.XmlUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 * @author kevin
 * @date 2019-7-13 16:42
 */
public class XmlUtilsExample {

    @Data
    @JacksonXmlRootElement(localName = "xml")
    private static class Model {

        @JacksonXmlProperty(localName = "id")
        private String id;

        @JacksonXmlProperty(localName = "name_")
        private String name;

        @JacksonXmlProperty(localName = "time")
        @JacksonXmlCData
        private LocalDate time;

        @JacksonXmlProperty(localName = "childs")
        @JacksonXmlElementWrapper(useWrapping = false)
        private List<Child> childs;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Child {

        @JacksonXmlProperty(localName = "id")
        private String id;

        @JacksonXmlProperty(localName = "name")
        private String name;
    }

    public static void main(String[] args) {
        Model model = new Model();
        model.setId(IDGenerator.STRING_UUID.generate());
        model.setName("name");
        model.setTime(LocalDate.now());
        model.setChilds(Collections.singletonList(new Child("1", "child_name")));
        String content = XmlUtils.serialize(model, true);
        System.out.println(content);
        Model deserialize = XmlUtils.deserialize(content, Model.class);
        System.out.println(deserialize);
    }
}
