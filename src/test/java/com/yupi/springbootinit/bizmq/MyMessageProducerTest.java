package com.yupi.springbootinit.bizmq;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MyMessagerProducerTest {

    @Resource
    private MyMessagerProducer myMessagerProducer;

    @Test
    void sendMessage() {
        myMessagerProducer.sendMessage("code_exchange", "my_routingKey", "hello！！！");
    }
}