package io.github.wppli.binding;

import cn.hutool.core.lang.ClassScanner;
import io.github.wppli.exceptions.MapperInstanceNotFountException;
import io.github.wppli.exceptions.MapperRegisterRepeatException;
import io.github.wppli.session.Configuration;
import io.github.wppli.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 映射器注册机
 * @author li--jiaqiang 2024−11−19
 */
public class MapperRegistry {

    private final Configuration configuration;

    /**
     * 将已添加的映射器代理加入到 HashMap
     */
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    public MapperRegistry(Configuration configuration) {
        this.configuration = configuration;
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        if  (null == mapperProxyFactory) {
            throw new MapperInstanceNotFountException("Type " + type + " is not known to the MapperRegistry.");
        }
        try {
            return mapperProxyFactory.newInstance(sqlSession);
        } catch (Exception exception) {
            throw new RuntimeException("Error getting mapper instance. Cause: " + exception.getMessage(), exception);
        }

    }

    public <T> void addMapper(Class<T> type) {
        /* Mapper 是接口才注册 */
        if (type.isInterface()) {
            if (hasMapper(type)) {
                throw new MapperRegisterRepeatException("Type " + type + " is already known to the MapperRegistry.");
            }
            // 注册映射器代理工厂
            knownMappers.put(type, new MapperProxyFactory<>(type));
        }
    }

    public <T> void addMappers(String packageName) {
        Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);
        for (Class<?> mapperClass : mapperSet) {
            addMapper(mapperClass);
        }
    }

    public <T> boolean hasMapper(Class<T> type) {
        return knownMappers.containsKey(type);
    }
}