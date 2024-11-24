package haythem.ClientPackage;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitClient {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();


        String messagePresence = "Hello, RabbitMQ QR code presence!";
        String messageOnline = "Hello, RabbitMQ QR code online!";

        channel.basicPublish("my-direct-exchange", "QR code", null, messagePresence.getBytes());
        System.out.println(" [x] Sent '" + messagePresence + "'");
//        channel.basicPublish("my-direct-exchange", "QR code online", null, messageOnline.getBytes());
//        System.out.println(" [x] Sent '" + messageOnline + "'");

    }
}
