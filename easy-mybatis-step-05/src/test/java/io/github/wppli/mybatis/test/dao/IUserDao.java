package io.github.wppli.mybatis.test.dao;

import io.github.wppli.mybatis.test.po.User;

public interface IUserDao {

    User queryUserInfoById(Long uId);

}
