//package com;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.sql.DataSource;
//
///**
// * Created by Bruinx on 2017/12/26.
// */
////@ConfigurationProperties(prefix = "spring.datasource.businessDB")
////public class DataSourceConfigBean {
////
////    private String url;
////
////    private String username;
////
////    private String password;
////
////    private String driverClassName;
////
////
////    public String getUrl() {
////        return url;
////    }
////
////    public void setUrl(String url) {
////        this.url = url;
////    }
////
////    public String getUsername() {
////        return username;
////    }
////
////    public void setUsername(String username) {
////        this.username = username;
////    }
////
////    public String getPassword() {
////        return password;
////    }
////
////    public void setPassword(String password) {
////        this.password = password;
////    }
////
////    public String getDriverClassName() {
////        return driverClassName;
////    }
////
////    public void setDriverClassName(String driverClassName) {
////        this.driverClassName = driverClassName;
////    }
////}
//@Configuration
//public class DataSourceConfigBean {
//
//    @Bean(name = "universeDataSource")
//    @Qualifier("universeDataSource")
//    @ConfigurationProperties(prefix="spring.datasource.universeDB")
//    @Primary
//    public DataSource universeDataSource() {
//        System.out.println("universe db built");
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "businessDataSource")
//    @Qualifier("businessDataSource")
//    @ConfigurationProperties(prefix="spring.datasource.businessDB")
//    public DataSource businessDataSource() {
//        System.out.println("business db built");
//        return DataSourceBuilder.create().build();
//    }
//
//}
