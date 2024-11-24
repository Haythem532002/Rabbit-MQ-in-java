package haythem.ConfigPackage;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConfigBroker {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //Create a direct exchange
        channel.exchangeDeclare("my-direct-exchange", BuiltinExchangeType.DIRECT,true);

        //Create different queues
        channel.queueDeclare("OnlineLecture",true,false,false,null);
        channel.queueDeclare("PresentationLecture",true,false,false,null);

        //create bindings between our exchange and queues - (queue, exchange, routingKey)
        channel.queueBind("OnlineLecture","my-direct-exchange","QR code");
        channel.queueBind("PresentationLecture","my-direct-exchange","QR code presence");

        System.out.println("Exchange,Queues and bindings are successfully created!");


        channel.close();
        connection.close();

    }
}
