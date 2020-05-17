package com.lc.rabbitmq.producter;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producter {

    public static void main(String[] args) throws Exception{
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("/");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        for (int i = 0; i < 5; i++) {
            /*
                1.exchange
                2.routtingkey
                3.
                4.
                5.
            * */
            channel.basicPublish("","test1",null,"hello".getBytes());
        }

        channel.close();
        connection.close();
    }
}
