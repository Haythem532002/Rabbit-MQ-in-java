package haythem.ConfigPackage;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class BrokerConfigsFanout {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //Fanout exchange
        channel.exchangeDeclare("my-fanout-exchange", BuiltinExchangeType.FANOUT,true);

        //Create different queues
        channel.queueDeclare("OnlineLecture",true,false,false,null);
        channel.queueDeclare("PresentationLecture",true,false,false,null);

        //create bindings between our exchange and queues - (queue, exchange, routingKey)
        channel.queueBind("OnlineLecture","my-fanout-exchange","");
        channel.queueBind("PresentationLecture","my-fanout-exchange","");

        System.out.println("Exchange,Queues and bindings are successfully created!");


        channel.close();
        connection.close();

    }
}
