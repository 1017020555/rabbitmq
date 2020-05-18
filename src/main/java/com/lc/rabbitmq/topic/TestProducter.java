package com.lc.rabbitmq.topic;

import com.lc.rabbitmq.utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class TestProducter {
    public final static String EXCHANGE_NAME="topics_exchange";
    public static void main(String[] args) throws Exception{
        RabbitMQUtil.checkServer();

        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ相关信息
        factory.setHost("localhost");

        //创建一个新的连接
        Connection connection = factory.newConnection();
        //创建一个通道
        Channel channel = connection.createChannel();

//        交换机名称；交换机类型
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        String[] routing_keys = new String[] { "usa.news", "usa.weather",
                "europe.news", "europe.weather" };
        String[] messages = new String[] { "美国新闻", "美国天气",
                "欧洲新闻", "欧洲天气" };

        for (int i = 0; i < routing_keys.length; i++) {
            String routingKey = routing_keys[i];
            String message = messages[i];
            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message
                    .getBytes());
            System.out.printf("发送消息到路由：%s, 内容是: %s%n ", routingKey,message);
        }

        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
