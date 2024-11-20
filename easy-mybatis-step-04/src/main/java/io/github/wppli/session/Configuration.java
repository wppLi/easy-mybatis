package io.github.wppli.session;

import io.github.wppli.binding.MapperRegistry;
import io.github.wppli.mapping.MappedStatement;

import java.util.HashMap;
import java.util.Map;

/**
 * 文件解析以后会存放到 Configuration 配置类中，接下来你会看到这个配置类会被串联到整个 Mybatis 流程中，所有内容存放和读取都离不开这个类。
 * 如我们在 DefaultSqlSession 中获取 Mapper 和执行 selectOne 也同样是需要在 Configuration 配置类中进行读取操作。
 *
 * @author li--jiaqiang 2024−11−20
 */
public class Configuration {

    /** mapper 映射器 */
    protected final MapperRegistry mapperRegistry = new MapperRegistry(this);

    /**
     * 映射的sql语句
     */
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>(16);

    public void addMappers(String packageName) {
        mapperRegistry.addMappers(packageName);
    }

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public boolean hasMapper(Class<?> type) {
        return mapperRegistry.hasMapper(type);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }

    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }

}