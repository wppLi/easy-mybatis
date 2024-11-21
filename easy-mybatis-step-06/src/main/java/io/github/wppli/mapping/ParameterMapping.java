package io.github.wppli.mapping;

import io.github.wppli.session.Configuration;
import io.github.wppli.type.JdbcType;

/**
 * 参数映射 #{property,javaType=int,jdbcType=NUMERIC}
 * @author li--jiaqiang 2024−11−21
 */
public class ParameterMapping {

    private Configuration configuration;

    /** property */
    private String property;
    /** javaType=int */
    private Class<?> javaType;
    /** jdbcType=NUMERIC */
    private JdbcType jdbcType;

    private ParameterMapping() {
    }

    public static class Builder {

        private final ParameterMapping parameterMapping = new ParameterMapping();

        public Builder(Configuration configuration, String property) {
            parameterMapping.configuration = configuration;
            parameterMapping.property = property;
        }

        public Builder javaType(Class<?> javaType) {
            parameterMapping.javaType = javaType;
            return this;
        }

        public Builder jdbcType(JdbcType jdbcType) {
            parameterMapping.jdbcType = jdbcType;
            return this;
        }

    }

    public Class<?> getJavaType() {
        return javaType;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public String getProperty() {
        return property;
    }

    public JdbcType getJdbcType() {
        return jdbcType;
    }
}