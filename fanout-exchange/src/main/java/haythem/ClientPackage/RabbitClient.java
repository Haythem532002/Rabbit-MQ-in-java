package haythem.ClientPackage;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeoutException;

public class RabbitClient {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String msgToPublish = "Welcome to everyone! It's "+ LocalDateTime.now();

        channel.basicPublish("my-fanout-exchange","",false,null,msgToPublish.getBytes());
        System.out.println("Our message is successfully published from OnlineLecture!");

        channel.close();
        connection.close();
    }
}
