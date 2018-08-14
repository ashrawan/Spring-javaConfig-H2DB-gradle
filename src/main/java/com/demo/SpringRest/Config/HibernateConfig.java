package com.demo.SpringRest.Config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
//@ComponentScan(basePackages = {"com.demo.springRest.Dao"})
@EnableTransactionManagement
//@PropertySource("classpath:application.properties")
public class HibernateConfig {

//    private final static String DATABASE_URL = "jdbc:h2:tcp://localhost/~/test;AUTO_SERVER=TRUE";
    private final static String DATABASE_URL = "jdbc:h2:mem:db;DB_CLOSE_DELAY=-1";
    private final static String DATABASE_DRIVER = "org.h2.Driver";
    private final static String DATABASE_DIALECT = "org.hibernate.dialect.H2Dialect";
    private final static String DATABASE_USERNAME = "sa";
    private final static String DATABASE_PASSWORD = "";

//
//    private final static String DATABASE_URL = "jdbc:mysql://localhost:3306/springrest";
//    private final static String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
//    private final static String DATABASE_DIALECT = "org.hibernate.dialect.MySQL5Dialect";
//    private final static String DATABASE_USERNAME = "root";
//    private final static String DATABASE_PASSWORD = "";


    @Bean
    public LocalSessionFactoryBean hibernateSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan(new String[]{ "com.demo.SpringRest.Model" });
        sessionFactory.setHibernateProperties(getHibernateProperties());

        return sessionFactory;

//        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
//        builder.addProperties(getHibernateProperties());
//        builder.scanPackages(new String[]{ "com.demo.SpringRest.Model" });
//        return builder.buildSessionFactory();
    }

    @Bean
    @Autowired
    HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);

        return transactionManager;
    }

    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(DATABASE_DRIVER);
        dataSource.setUrl(DATABASE_URL);
        dataSource.setUsername(DATABASE_USERNAME);
        dataSource.setPassword(DATABASE_PASSWORD);

        return dataSource;
    }

    Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", DATABASE_DIALECT);
        properties.setProperty("hibernate.show_sql", "true");
//        properties.setProperty("hibernate.format_sql", "true");
        return properties;
    }


}
