package io.github.wppli.mapping;

import java.util.Map;

/**
 * 绑定的SQL,是从SqlSource而来，将动态内容都处理完成得到的SQL语句字符串，其中包括?,还有绑定的参数
 * @author li--jiaqiang 2024−11−21
 */
public class BoundSql {

    private final String sql;
    private final Map<Integer, String> parametersMapping;
    private final String parameterType;
    private final String resultType;

    public BoundSql(String sql, Map<Integer, String> parametersMapping, String parameterType, String resultType) {
        this.sql = sql;
        this.parametersMapping = parametersMapping;
        this.parameterType = parameterType;
        this.resultType = resultType;
    }

    public String getSql() {
        return sql;
    }

    public Map<Integer, String> getParametersMapping() {
        return parametersMapping;
    }

    public String getParameterType() {
        return parameterType;
    }

    public String getResultType() {
        return resultType;
    }
}