package com.fan;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@MapperScan("com.fan.mapper")
@SpringBootApplication
@EnableSwagger2
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BackApplication {


    public static void main(String[] args) {
        SpringApplication.run(BackApplication.class, args);
    }

}
