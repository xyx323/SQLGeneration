//package com;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.persistence.EntityManager;
//import javax.sql.DataSource;
//import java.util.Map;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef="entityManagerFactoryBusiness",
//        transactionManagerRef="transactionManagerBusiness",
//        basePackages= { "com.repository" }) //设置Repository所在位置
//public class BusinessConfig {
//
//    private String url;
//
//    private String username;
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getDriverClassName() {
//        return driverClassName;
//    }
//
//    public void setDriverClassName(String driverClassName) {
//        this.driverClassName = driverClassName;
//    }
//
//    private String password;
//
//    private String driverClassName;
//
//    @Autowired @Qualifier("businessDataSource")
//    private DataSource businessDataSource;
//
//    @Bean(name = "entityManagerBusiness")
//    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
//        return entityManagerFactoryBusiness(builder).getObject().createEntityManager();
//    }
//
//    @Bean(name = "entityManagerFactoryBusiness")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBusiness (EntityManagerFactoryBuilder builder) {
//        return builder
//                .dataSource(businessDataSource)
//                .properties(getVendorProperties(businessDataSource))
//                .packages("com.entity") //设置实体类所在位置
//                .persistenceUnit("businessPersistenceUnit")
//                .build();
//    }
//
//    @Autowired
//    private JpaProperties jpaProperties;
//
//    private Map<String, String> getVendorProperties(DataSource dataSource) {
//        return jpaProperties.getHibernateProperties(dataSource);
//    }
//
//    @Bean(name = "transactionManagerBusiness")
//    PlatformTransactionManager transactionManagerBusiness(EntityManagerFactoryBuilder builder) {
//        return new JpaTransactionManager(entityManagerFactoryBusiness(builder).getObject());
//    }
//
//}
