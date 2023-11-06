package com.example.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication@SpringBootApplication
public class ForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForumApplication.class, args);
        SpringApplication.run(Application.class, args);
    }
    }

}
