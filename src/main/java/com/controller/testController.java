package com.controller;

import com.BusinessConfig;
import com.DataSourceConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Bruinx on 2017/12/26.
 */
@RestController
@RequestMapping("/test")
public class testController {
//    @Value("${spring.datasource.businessDB.url}")
//    private String url;
//
//    @Value("${spring.datasource.businessDB.username}")
//    private String username;
//
//    @Value("${spring.datasource.businessDB.password}")
//    private String password;
//
//    @Value("${spring.datasource.businessDB.driver-class-name}")
//    private String driverClassName;
//
//    @RequestMapping("test")
//    public String test(){
//        return url+","+username+","+password+","+driverClassName;
//    }
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
//    @Autowired
////    BusinessConfig businessConfig;
//    DataSourceConfigBean configBean;
////
//    @RequestMapping("test")
//    public String hexo(){
////        BasicDataSource basicDS = (BasicDataSource) appcxt.getBean("dataSource");
////        configBean.universeDataSource();
////        businessConfig.setUrl("testtesttest");
////        return businessConfig.getUrl();
//    }
}