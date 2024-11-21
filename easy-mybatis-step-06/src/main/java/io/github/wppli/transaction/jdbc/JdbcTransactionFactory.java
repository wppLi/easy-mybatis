package io.github.wppli.transaction.jdbc;

import io.github.wppli.session.TransactionIsolationLevel;
import io.github.wppli.transaction.Transaction;
import io.github.wppli.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 事务工厂
 * @author li--jiaqiang 2024−11−21
 */
public class JdbcTransactionFactory implements TransactionFactory {

    @Override
    public Transaction newTransaction(Connection conn) {
        return new JdbcTransaction(conn);
    }

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        return new JdbcTransaction(dataSource, level, autoCommit);
    }
}