package com.lc.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producter {
    public static void main(String[] args) throws Exception{
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ相关信息
        factory.setHost("localhost");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //      交换机名称；交换机类型
//        channel.exchangeDeclare("exchange_name", "fanout");

        /*
            1.队列名
            2.是否定义持久化队列
            3.是否独占本次链接
            4.是否在不使用的时候自动删除队列
            5.队列其他参数
         */
        channel.queueDeclare("queue_name",true,false,false,null);
        channel.basicPublish("","queue_name",null,"发送发送".getBytes());

        channel.close();
        connection.close();
    }
}
