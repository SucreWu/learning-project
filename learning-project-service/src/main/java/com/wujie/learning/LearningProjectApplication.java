package com.wujie.learning;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableApolloConfig
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.wujie.**"})
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@ComponentScan("com.wujie.**")
public class LearningProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningProjectApplication.class, args);
    }

}
