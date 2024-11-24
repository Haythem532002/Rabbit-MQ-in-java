package haythem.ServerPackage;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class RabbitServer {
    public static void main(String[] args) throws Exception {
        // Create a connection factory
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();


        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };

        System.out.println("I'm waiting for new messages....\n");

        // Start consuming messages from the queue
        channel.basicConsume("OnlineLecture", true, deliverCallback, consumerTag -> { });
        channel.basicConsume("PresentationLecture", true, deliverCallback, consumerTag -> { });
    }
}
