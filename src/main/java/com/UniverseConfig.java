//package com;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
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
//
///**
// * Created by Bruinx on 2017/12/26.
// */
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef="entityManagerFactoryUniverse",
//        transactionManagerRef="transactionManagerUniverse",
//        basePackages= { "com.repository" }) //设置Repository所在位置
//public class UniverseConfig {
//
//    @Autowired
//    @Qualifier("universeDataSource")
//    private DataSource universeDataSource;
//
//    @Primary
//    @Bean(name = "entityManagerUniverse")
//    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
//        return entityManagerFactoryUniverse(builder).getObject().createEntityManager();
//    }
//
//    @Primary
//    @Bean(name = "entityManagerFactoryUniverse")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryUniverse (EntityManagerFactoryBuilder builder) {
//        return builder
//                .dataSource(universeDataSource)
//                .properties(getVendorProperties(universeDataSource))
//                .packages("com.entity") //设置实体类所在位置
//                .persistenceUnit("universePersistenceUnit")
//                .build();
//    }
//
//    @Autowired(required=false)
//    private JpaProperties jpaProperties;
//
//    private Map<String, String> getVendorProperties(DataSource dataSource) {
//        return jpaProperties.getHibernateProperties(dataSource);
//    }
//
//    @Primary
//    @Bean(name = "transactionManagerUniverse")
//    public PlatformTransactionManager transactionManagerUniverse(EntityManagerFactoryBuilder builder) {
//        return new JpaTransactionManager(entityManagerFactoryUniverse(builder).getObject());
//    }
//
//}