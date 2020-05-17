package com.lc.rabbitmq.consumer;
import com.rabbitmq.client.*;

public class Consumer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("/");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();
        /*
            参数1：
            参数2：是否持久化
            参数3：exclusive
            参数4：autoDelete
            参数5：arguments
         */
        channel.queueDeclare("test1",true,false,false,null);
//        创建消费者
        QueueingConsumer consumer=new QueueingConsumer(channel);
        /*     参数1：
               参数2：是否自动接收
               参数3：消费者
        * */
        channel.basicConsume("test1",true,consumer);

//        获取消息
        while (true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();

            String string=new String(delivery.getBody());
            System.out.println("consumer: "+string);

            Envelope envelope = delivery.getEnvelope();

        }


    }
}
