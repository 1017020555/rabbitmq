package com.lc.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer {
    public static void main(String[] args) throws Exception{
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ地址
        factory.setHost("localhost");

        //创建一个新的连接
        Connection connection = factory.newConnection();
        //创建一个通道
        Channel channel = connection.createChannel();
         /*
            1.队列名
            2.是否定义持久化队列
            3.是否独占本次链接
            4.是否在不使用的时候自动删除队列
            5.队列其他参数
         */
        channel.queueDeclare("queue_name",true,false,false,null);

//        channel.queueBind("queue_name","exchange_name","");

        DefaultConsumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String string =new String(body,"UTF-8");
                System.out.println("消息id: "+envelope.getDeliveryTag());
                System.out.println("交换机: "+envelope.getExchange());
                System.out.println("路由key: "+envelope.getRoutingKey());
                System.out.println("接收到的消息： "+string);
            }
        };

        channel.basicConsume("queue_name",true,consumer);
    }
}
