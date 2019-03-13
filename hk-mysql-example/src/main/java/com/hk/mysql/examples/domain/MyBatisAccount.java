package com.hk.mysql.examples.domain;

import com.hk.core.data.jdbc.domain.AbstractUUIDPersistable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author huangkai
 * @date 2019/3/13 9:06
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MyBatisAccount extends AbstractUUIDPersistable {

    private String sheyuanId;

    private String nickName;

    private Content contentOne;

    private List<Content> content;
}
