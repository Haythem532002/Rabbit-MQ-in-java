package haythem.ServerPackage;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitServer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        System.out.println("I'm waiting for new messages....\n");

        //Default exchange
        channel.basicConsume("OnlineLecture",true,((consumerTag,message)->
                {
                    System.out.println("A message has been received from OnlineLecture: \n'"+new String(message.getBody()) );
                }),consumerTag ->{}
        );
        //Default exchange
        channel.basicConsume("PresentationLecture",true,((consumerTag,message)->
                {
                    System.out.println("A message has been received from PresentationLecture: \n'"+new String(message.getBody()) );
                }),consumerTag ->{}
        );


    }
}