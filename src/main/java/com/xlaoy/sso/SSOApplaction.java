package com.xlaoy.sso;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by Administrator on 2018/2/1 0001.
 */
@EnableSwagger2Doc
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = "com.xlaoy")
public class SSOApplaction {

    public static void main(String[] args) {
        SpringApplication.run(SSOApplaction.class, args);
    }
}
