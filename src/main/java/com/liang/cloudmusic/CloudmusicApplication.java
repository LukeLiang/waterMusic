package com.liang.cloudmusic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(basePackages = "com.liang.cloudmusic.mapping")
@EnableScheduling   // 开启定时任务
public class CloudmusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudmusicApplication.class, args);
    }

}
