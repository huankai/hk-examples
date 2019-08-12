package com.hk.elasticsearch.example.entity;

import com.hk.core.elasticsearch.analyzer.IKanalyzer;
import com.hk.core.elasticsearch.domain.AbstractIDPersistable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author kevin
 * @date 2019-8-10 11:56
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Document(indexName = "user", refreshInterval = "-1")
public class User extends AbstractIDPersistable {

    @Field(analyzer = IKanalyzer.IK_MAX_WORD_ANALYZER,
            searchAnalyzer = IKanalyzer.IK_MAX_WORD_ANALYZER, type = FieldType.Text)
    private String userName;

    @Field(analyzer = IKanalyzer.IK_MAX_WORD_ANALYZER,
            searchAnalyzer = IKanalyzer.IK_MAX_WORD_ANALYZER, type = FieldType.Text)
    private String phone;

    @Field(type = FieldType.Long)
    private Long orgId;

}
