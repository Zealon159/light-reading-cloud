package cn.zealon.readingcloud.account.common.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 账户中心数据源配置
 * @author: zealon
 * @since: 2020/4/2
 */
@Configuration
public class AccountCenterDataSourceConfig {

    /** 数据源Bean */
    @Bean(name = "accountCenterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.account-center")
    public DataSource accountCenterDataSource(){
        return new DruidDataSource();
    }

    /** 事务管理器 */
    @Bean(name = "accountCenterTransactionManager")
    public DataSourceTransactionManager setTransactionManager(@Qualifier("accountCenterDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
