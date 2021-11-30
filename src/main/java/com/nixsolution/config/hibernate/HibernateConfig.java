package com.nixsolution.config.hibernate;

import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScans(value = { @ComponentScan("com.nixsolution") })
@EnableTransactionManagement
@PropertySource(value = "classpath:db.properties")
public class HibernateConfig {

    final Environment environment;

    public HibernateConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.nixsolution");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty(
                "hibernate.connection.driver_class"));
        dataSource.setUrl(
                environment.getRequiredProperty("hibernate.connection.url"));
        dataSource.setUsername(environment.getRequiredProperty(
                "hibernate.connection.username"));
        dataSource.setPassword(environment.getRequiredProperty(
                "hibernate.connection.password"));
        return dataSource;
    }

    @Bean
    public HibernateTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect",
                environment.getRequiredProperty("hibernate.dialect"));
        hibernateProperties.put("hibernate.show_sql",
                environment.getRequiredProperty("hibernate.show_sql"));
        hibernateProperties.put("hibernate.hbm2ddl.auto",
                environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        hibernateProperties.put("hibernate.hbm2ddl.import_files",
                environment.getRequiredProperty(
                        "hibernate.hbm2ddl.import_files"));
        return hibernateProperties;
    }

}
