package io.github.wppli.transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 事务接口
 * @author li--jiaqiang 2024−11−21
 */
public interface Transaction {

    Connection getConnection() throws SQLException;

    void commit() throws SQLException;

    void rollback() throws SQLException;

    void close() throws SQLException;
}