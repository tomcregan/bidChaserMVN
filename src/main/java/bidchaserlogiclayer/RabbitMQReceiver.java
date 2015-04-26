package bidchaserlogiclayer;

import bidchaserdataaccesslayer.RabbitMQInstance;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tommy
 */

public class RabbitMQReceiver {
    
    
    private static final String EXCHANGE_NAME = "topic_bids";
    private static QueueingConsumer consumer;

    /**
     * Method description
     *
     *
     * @return
     *
     * @throws IOException
     * @throws InterruptedException
     */
    public static void receive() throws IOException, InterruptedException{
        Connection connection = RabbitMQInstance.getInstance().getConnection();
        startListener(connection, "bidchaser.auction01");
    }

    private static void startListener(Connection connection, String bindingKey){
         System.out.println("Listening for messages");
        
        try{
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");
            String queueName = channel.queueDeclare().getQueue();
            System.out.println("Queue Name: " + queueName);
            channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);
            consumer = new QueueingConsumer(channel);

            channel.basicConsume(queueName, true, consumer);

        } catch(IOException ex){
            Logger.getLogger(RabbitMQReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String consumer(){
        String message = "";
        try{
            System.out.println(" [x] Waiting for message.");
            boolean consuming = true;
            while(consuming){
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                message = new String(delivery.getBody());
                String routingKey = delivery.getEnvelope().getRoutingKey();

                System.out.println(" [x] Received -> '" + routingKey + "':'" + message + "'");
                consuming = false;
            }
        } catch(ShutdownSignalException | ConsumerCancelledException | InterruptedException ex){
            Logger.getLogger(RabbitMQReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }

        return message;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
