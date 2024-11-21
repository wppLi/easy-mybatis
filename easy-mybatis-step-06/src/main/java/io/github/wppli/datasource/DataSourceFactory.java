package io.github.wppli.datasource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 数据源工厂
 * @author li--jiaqiang 2024−11−21
 */
public interface DataSourceFactory {

    void setProperties(Properties props);

    DataSource getDataSource();
}