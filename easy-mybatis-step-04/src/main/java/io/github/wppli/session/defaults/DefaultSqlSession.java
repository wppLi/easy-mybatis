package io.github.wppli.session.defaults;

import io.github.wppli.binding.MapperRegistry;
import io.github.wppli.session.Configuration;
import io.github.wppli.session.SqlSession;

/**
 * 默认SqlSession实现类
 * @author li--jiaqiang 2024−11−19
 */
public class DefaultSqlSession implements SqlSession {

    private final Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了！" + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return (T) ("你被代理了！" + "方法：" + statement + " 入参：" + parameter.toString());
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }
}