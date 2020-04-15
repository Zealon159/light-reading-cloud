package cn.zealon.readingcloud.account.common.config;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * MyBatis配置
 * @author: zealon
 * @since: 2020/4/2
 */
@Configuration
@MapperScan(basePackages = "cn.zealon.readingcloud.account.dao",
        sqlSessionTemplateRef="accountCenterSqlSessionTemplate")
public class MybatisConfig {

    private final static String MAPPER_LOCATIONS = "classpath*:mappers/*.xml";

    /** 工厂配置 */
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(@Qualifier("accountCenterDataSource") DataSource dataSource) throws Exception {
        // 设置数据源
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);

        // 添加XML映射
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factory.setMapperLocations(resolver.getResources(MAPPER_LOCATIONS));

        //添加插件
        factory.setPlugins(new Interceptor[]{ this.getPageHelper() });
        return factory.getObject();
    }

    /** 会话模板 */
    @Bean(name = "accountCenterSqlSessionTemplate")
    public SqlSessionTemplate setSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /** 分页插件 */
    private PageHelper getPageHelper(){
        //配置分页插件，详情请查阅官方文档
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        //分页尺寸为0时查询所有纪录不再执行分页
        properties.setProperty("pageSizeZero", "true");
        //页码<=0 查询第一页，页码>=总页数查询最后一页
        properties.setProperty("reasonable", "true");
        //支持通过 Mapper 接口参数来传递分页参数
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("params", "count=countSql");
        //切换数据源，自动解析不同数据库的分页
        properties.setProperty("autoRuntimeDialect", "true");
        pageHelper.setProperties(properties);
        return pageHelper;
    }

    /**
     * swagger 配置类
     * http://localhost:8080/swagger-ui.html
     * @author zealon
     * @since 2019-07-04
     */
    @Configuration
    @EnableSwagger2
    public static class AccountSwaggerConfig {

        /**
         * swagger生成
         * @return Docket
         */
        @Bean
        public Docket customDocket() {
            Docket docket = new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("cn.zealon.readingcloud.account.controller"))
                    .paths(PathSelectors.any())
                    .build()
                    .apiInfo(apiInfo());
            return docket;
        }

        /**
         * swagger基础信息
         * @return ApiInfo swagger信息
         */
        private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title("账户中心接口")
                    .description("账户中心")
                    .termsOfServiceUrl("")
                    .contact(new Contact("", "", ""))
                    .license("")
                    .licenseUrl("")
                    .version("1.0.0")
                    .build();
        }
    }
}
