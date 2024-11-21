package io.github.wppli.mybatis.test;


import com.alibaba.fastjson.JSON;
import io.github.wppli.builder.xml.XMLConfigBuilder;
import io.github.wppli.io.Resources;
import io.github.wppli.mybatis.test.dao.IUserDao;
import io.github.wppli.mybatis.test.po.User;
import io.github.wppli.session.Configuration;
import io.github.wppli.session.SqlSession;
import io.github.wppli.session.SqlSessionFactory;
import io.github.wppli.session.SqlSessionFactoryBuilder;
import io.github.wppli.session.defaults.DefaultSqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

/**
 * @author li--jiaqiang 2024−11−21
 */
public class ApiTest {

    private final Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_SqlSessionFactory() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 3. 测试验证
        User res = userDao.queryUserInfoById(1L);
        logger.info("测试结果：{}", JSON.toJSONString(res));
    }

    @Test
    public void test_selectOne() throws IOException, SQLException, ClassNotFoundException {
        // 解析 XML
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        Configuration configuration = xmlConfigBuilder.parse();

        // 获取 DefaultSqlSession
        SqlSession sqlSession = new DefaultSqlSession(configuration);

        // 执行查询：默认是一个集合参数
        Object[] req = {1L};
        Object res = sqlSession.selectOne("io.github.wppli.mybatis.test.dao.IUserDao.queryUserInfoById", req);
        logger.info("测试结果：{}", JSON.toJSONString(res));
    }
}
