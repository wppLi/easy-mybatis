package io.github.wppli.session.defaults;

import io.github.wppli.binding.MapperRegistry;
import io.github.wppli.session.SqlSession;
import io.github.wppli.session.SqlSessionFactory;

/**
 * @author li--jiaqiang 2024−11−19
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}