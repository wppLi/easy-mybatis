package io.github.wppli.session;

/**
 * 工厂模式接口，构建SqlSession的工厂
 * @author li--jiaqiang 2024−11−19
 */
public interface SqlSessionFactory {
    /**
     * 打开一个 session
     * @return SqlSession
     */
    SqlSession openSession();
}