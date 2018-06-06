package com.hk.template;

/**
 * <p>
 * Spring Data Jpa Base Entity Template
 * </p>
 *
 * @author huangkai
 * @date 2018-5-30 21:02
 */
public class SpringDataJpaBaseEntityTemplate extends AbstractTemplate implements BaseEntityTemplate {

    /**
     * @see org.springframework.data.jpa.domain.AbstractPersistable
     */
    public static final String[] JPA_TEMPLATE_CLASSNAME = new String[]{
            "org.hibernate.annotations.GenericGenerator",
            "org.springframework.data.domain.Persistable",
            "org.springframework.data.jpa.domain.AbstractPersistable",
            "org.springframework.util.ClassUtils",
            "javax.persistence.GeneratedValue",
            "javax.persistence.Id",
            "javax.persistence.MappedSuperclass",
            "javax.persistence.Transient"};

    public SpringDataJpaBaseEntityTemplate() {
        importClassNames(JPA_TEMPLATE_CLASSNAME);
    }
}
