package me.zji.config;

import me.zji.entity.TestClazz1;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Spring 主配置
 * Created by imyu on 2017/1/18.
 */
@Configuration
public class RootConfig {
    @Bean
    public TestClazz1 testClazz1() {
        return new TestClazz1();
    }

    /******************cc持久化层部分配置*******************/
    // 配置数据源
    @Bean
    public DriverManagerDataSource jdbcDataSource(@Qualifier("applicationProperties") Properties properties) {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(properties.getProperty("jdbc.driver"));
        driverManagerDataSource.setUrl(properties.getProperty("jdbc.url"));
        driverManagerDataSource.setUsername(properties.getProperty("jdbc.username"));
        driverManagerDataSource.setPassword(properties.getProperty("jdbc.password"));
        return driverManagerDataSource;
    }
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DriverManagerDataSource jdbcDataSource) throws IOException{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(jdbcDataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setConfigLocation(resolver.getResource("classpath:me/zji/config/mybatis-config.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("me.zji.entity");
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:me/zji/dao/impl/*.xml"));
        return sqlSessionFactoryBean;
    }
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("me.zji.dao");
        return mapperScannerConfigurer;
    }
    @Bean
    public Properties applicationProperties() throws IOException{
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resources = resolver.getResource("classpath:me/zji/config/application.properties");
        Properties properties = new Properties();
        InputStream inputStream = new BufferedInputStream(resources.getInputStream());
        properties.load(inputStream);
        return properties;
    }
}
