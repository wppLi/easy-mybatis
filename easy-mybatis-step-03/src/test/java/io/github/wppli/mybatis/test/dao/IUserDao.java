package io.github.wppli.mybatis.test.dao;

/**
 * @author li--jiaqiang 2024−11−19
 */
public interface IUserDao {

    String queryUserName(String uId);

    Integer queryUserAge(String uId);

}