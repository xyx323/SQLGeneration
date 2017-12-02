package com;

/**
 * Created by Bruinx on 2017/11/29.
 */
import com.domain.UserIntent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages="com")
@EnableAutoConfiguration
@EntityScan(basePackages="com.entity")
@EnableJpaRepositories(basePackages="com.repository")
@SpringBootApplication
public class Application {
    public static UserIntent userIntent = new UserIntent();
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}