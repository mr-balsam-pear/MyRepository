package com.balsam.lucene;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LuceneApplication.class)
public class LuceneApplicationTest {
    @Autowired
    ApplicationContext ioc;

    @Test
    public void test_01() {
        boolean helloController = ioc.containsBean("helloService");
        System.out.println(helloController);
    }
}