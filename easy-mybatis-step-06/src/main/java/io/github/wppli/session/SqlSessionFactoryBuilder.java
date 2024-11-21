package io.github.wppli.session;

import io.github.wppli.builder.xml.XMLConfigBuilder;
import io.github.wppli.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;

/**
 * SqlSessionFactoryBuilder 作为整个 Mybatis 的入口，提供建造者工厂，包装 XML 解析处理，并返回对应 SqlSessionFactory 处理类
 * @author li--jiaqiang 2024−11−20
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader) {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration config) {
        return new DefaultSqlSessionFactory(config);
    }
}