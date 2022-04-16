package com.cao.score;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类123
 */
@SpringBootApplication
@MapperScan("com.cao.score.dao")
public class ScoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScoreApplication.class, args);
    }

}
