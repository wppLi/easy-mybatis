package io.github.wppli.session.defaults;

import io.github.wppli.binding.MapperRegistry;
import io.github.wppli.session.Configuration;
import io.github.wppli.session.SqlSession;
import io.github.wppli.session.SqlSessionFactory;

/**
 * @author li--jiaqiang 2024−11−19
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}