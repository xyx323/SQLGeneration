package com;

/**
 * Created by Bruinx on 2017/11/29.
 */
import com.domain.UserIntent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static UserIntent userIntent = new UserIntent();
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}