package com.yupi.springbootinit.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class TopicConsumer {

    private static final String EXCHANGE_NAME = "topic-exchange";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        // 创建队列，随机分配一个队列名称
        String queueName1 = "frontend_queue";
        channel.queueDeclare(queueName1, true, false, false, null);
        channel.queueBind(queueName1, EXCHANGE_NAME, "#.前端.#");

        String queueName2 = "backend_queue";
        channel.queueDeclare(queueName2, true, false, false, null);
        channel.queueBind(queueName2, EXCHANGE_NAME, "#.后端.#");

        String queueName3 = "product_queue";
        channel.queueDeclare(queueName3, true, false, false, null);
        channel.queueBind(queueName3, EXCHANGE_NAME, "#.产品.#");


        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback xiaoadeliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [小a] Received '" +
                    delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        };

        DeliverCallback xiaobdeliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [小b] Received '" +
                    delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        };

        DeliverCallback xiaocdeliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [小c] Received '" +
                    delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        };

        DeliverCallback xiaoddeliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [小d] Received '" +
                    delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
        };
        channel.basicConsume(queueName1, true, xiaoadeliverCallback, consumerTag -> {
        });
        channel.basicConsume(queueName2, true, xiaobdeliverCallback, consumerTag -> {
        });
        channel.basicConsume(queueName3, true, xiaocdeliverCallback, consumerTag -> {
        });
        channel.basicConsume(queueName1, true, xiaoddeliverCallback, consumerTag -> {
        });
    }
}