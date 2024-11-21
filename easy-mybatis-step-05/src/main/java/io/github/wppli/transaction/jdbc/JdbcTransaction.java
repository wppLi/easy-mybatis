package io.github.wppli.transaction.jdbc;

import io.github.wppli.session.TransactionIsolationLevel;
import io.github.wppli.transaction.Transaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author li--jiaqiang 2024−11−21
 */
public class JdbcTransaction implements Transaction {

    /** 数据库连接信息 */
    private Connection connection;
    /** 数据源 */
    private DataSource dataSource;
    /** 事务隔离级别 */
    private TransactionIsolationLevel transactionIsolationLevel;
    /** 是否自动提交事务 */
    private boolean autoCommit;

    public JdbcTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        this.dataSource = dataSource;
        this.transactionIsolationLevel = level;
        this.autoCommit = autoCommit;
    }

    public JdbcTransaction(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Connection getConnection() throws SQLException {
        this.connection = dataSource.getConnection();
        this.connection.setTransactionIsolation(transactionIsolationLevel.getLevel());
        this.connection.setAutoCommit(autoCommit);
        return this.connection;
    }

    @Override
    public void commit() throws SQLException {
        if (null != connection && !connection.getAutoCommit()) {
            connection.commit();
        }
    }

    @Override
    public void rollback() throws SQLException {
        if (null != connection && !connection.getAutoCommit()) {
            connection.rollback();
        }
    }

    @Override
    public void close() throws SQLException {
        if (null != connection && !connection.getAutoCommit()) {
            connection.close();
        }
    }
}