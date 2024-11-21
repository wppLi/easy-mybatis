package io.github.wppli.session.defaults;

import io.github.wppli.mapping.BoundSql;
import io.github.wppli.mapping.Environment;
import io.github.wppli.mapping.MappedStatement;
import io.github.wppli.session.Configuration;
import io.github.wppli.session.SqlSession;

import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 默认SqlSession实现类
 * @author li--jiaqiang 2024−11−19
 */
@SuppressWarnings("unchecked")
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
    public <T> T selectOne(String statement, Object parameter) throws SQLException, ClassNotFoundException {
        MappedStatement mappedStatement = configuration.getMappedStatement(statement);
        Environment environment = configuration.getEnvironment();
        Connection connection = environment.getDataSource().getConnection();

        // 获取待执行sql
        BoundSql boundSql = mappedStatement.getBoundSql();
        // 参数准备
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSql());
        preparedStatement.setLong(1, Long.parseLong(((Object[])parameter)[0].toString()));
        // 执行，获取结果
        ResultSet resultSet = preparedStatement.executeQuery();
        // 结果解析
        System.out.println(boundSql.getSql());
        List<T> result = resultSet2Obj(resultSet, Class.forName(boundSql.getResultType()));
        if (result.isEmpty()) return null;
        return result.get(0);
    }

    private <T> List<T> resultSet2Obj(ResultSet resultSet, Class<?> clazz) {
        List<T> list = new ArrayList<>();
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            // 每次遍历行值
            while (resultSet.next()) {
                T obj = (T) clazz.newInstance();
                for (int i = 1; i <= columnCount; i++) {
                    Object value = resultSet.getObject(i);
                    String columnName = metaData.getColumnName(i);
                    String setMethod = "set" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
                    Method method;
                    if (value instanceof Timestamp) {
                        method = clazz.getMethod(setMethod, Date.class);
                    } else {
                        method = clazz.getMethod(setMethod, value.getClass());
                    }
                    method.invoke(obj, value);
                }
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
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