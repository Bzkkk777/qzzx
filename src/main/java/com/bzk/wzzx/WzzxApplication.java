package com.bzk.wzzx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bzk.wzzx.dao")
public class WzzxApplication {

    public static void main(String[] args) {
        SpringApplication.run(WzzxApplication.class, args);
    }

}
