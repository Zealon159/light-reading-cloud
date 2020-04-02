package cn.zealon.bookstore.bookcenter.common.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;

/**
 * 图书资源中心数据源配置
 * @author: zealon
 * @since: 2020/4/2
 */
@Configuration
public class BookCenterDataSourceConfig {

    /** 数据源Bean */
    @Bean(name = "bookCenterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.book-center")
    public DataSource bookCenterDataSource(){
        return new DruidDataSource();
    }

    /** 事务管理器 */
    @Bean(name = "bookCenterTransactionManager")
    public DataSourceTransactionManager setTransactionManager(@Qualifier("bookCenterDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
