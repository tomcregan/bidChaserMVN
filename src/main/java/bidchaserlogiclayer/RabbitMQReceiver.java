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
    
    
    private static final String EXCHANGE_NAME = "topic_bid_ex";
    private static final String EXCHANGE_ND = "topic_bidND";
    private final static String Q_NAME_D = "bid_durable";
    private final static String Q_NAME_ND = "bid_non_durable";
    //private final static String Q_NAME_D_BASICACK = "bid_basic_ack";
    private static QueueingConsumer consumer;
    private static QueueingConsumer consumer2;

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
        startListenerNew(connection, "bidchaser.auction02");
    }

    private static void startListener(Connection connection, String bindingKey){
         System.out.println("Listening for messages");
        
        try{
            Channel channel = connection.createChannel();
            
            channel.exchangeDeclare(EXCHANGE_NAME, "topic", true);
            channel.queueDeclare(Q_NAME_D, false, true, false, null);
            String queueName = channel.queueDeclare().getQueue();
            System.out.println("Queue Name: " + queueName);
            channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);
            consumer = new QueueingConsumer(channel);

            channel.basicConsume(queueName, true, consumer);
        } catch(IOException ex){
            Logger.getLogger(RabbitMQReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private static void startListenerNew(Connection connection, String bindingKey){
         System.out.println("Listening for messages");
        
        try{
            Channel channel = connection.createChannel();
            
            channel.exchangeDeclare(EXCHANGE_ND, "topic");
            channel.queueDeclare(Q_NAME_ND, false, false, false, null);
            String queueName = channel.queueDeclare().getQueue();
            System.out.println("Queue Name: " + queueName);
            channel.queueBind(queueName, EXCHANGE_ND, bindingKey);
            consumer2 = new QueueingConsumer(channel);

            channel.basicConsume(queueName, true, consumer2);
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
    
    public static String consumerA(){
        String message = "";
        try{
            System.out.println(" [x] Waiting for message.");
            boolean consuming = true;
            while(consuming){
                QueueingConsumer.Delivery delivery = consumer2.nextDelivery();
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
