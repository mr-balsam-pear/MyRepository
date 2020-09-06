package com.balsam.lucene;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = {"classpath:test.xml"})
public class LuceneApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuceneApplication.class, args);
    }

}
