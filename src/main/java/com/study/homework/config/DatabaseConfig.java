package com.study.homework.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.study.homework.Repository.mapper"}, sqlSessionFactoryRef = "mainSqlSessionFactory")
public class DatabaseConfig {

    /**
     * 데이터소스를 반환
     *
     * @return the data source
     * @since 1.0
     */
    //2개이상의 DB 붙일때 기동순위 결정 (primary = 첫번째)
    @Primary
    @Bean(name = "mainDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * Mybatis에 관련된 SqlSessionFactory 객체를 반환
     *
     * @param dataSource
     *            the data source
     * @return the sql session factory
     * @throws Exception
     *             the exception
     * @since 1.0
     */
    @Bean(name = "mainSqlSessionFactory")
    public SqlSessionFactory mainSqlSessionFactory(@Qualifier("mainDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        sessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("mybatis/mybatis-config.xml"));

        return sessionFactory.getObject();
    }
}
