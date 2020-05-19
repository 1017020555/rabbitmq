package com.lc.rabbitmq.springboot;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "queue_hello")
public class Consumer {

    @RabbitHandler
    public void get(String h){
        System.out.println("get: "+h);
    }

}
