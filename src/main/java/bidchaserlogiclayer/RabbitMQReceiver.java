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
    
    
    private static final String DURABLE_EX = "topic_durable";
    private final static String Q_NAME_D = "bid_durable_q";
    private final static String Q_NAME_ND = "bid_non_durable";
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

    private static void startListener(Connection connection, String routingKey){

        try{
            Channel channel = connection.createChannel();
                       
            boolean isDurable = true;
            boolean isExclusive = false;
            boolean isAutoDelete = false;
            channel.exchangeDeclare(DURABLE_EX, "topic", true);
            //String queueName = channel.queueDeclare().getQueue();
            channel.queueDeclare(Q_NAME_D, isDurable, isExclusive, isAutoDelete, null);
            System.out.println("Queue Name: " + Q_NAME_D);
            channel.queueBind(Q_NAME_D, DURABLE_EX, routingKey);
            //System.out.println("QueingConsumerTest");
            consumer = new QueueingConsumer(channel);
            
            //System.out.println("BacicConsumeTest");
           channel.basicConsume(Q_NAME_D, true, consumer);
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
                System.out.println("4");
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                System.out.println("5");
                message = new String(delivery.getBody());
                System.out.println("6");
                String routingKey = delivery.getEnvelope().getRoutingKey();

                System.out.println(" [x] Received -> '" + routingKey + "':'" + message + "'");
                consuming = false;
            }
            
            
            
        } catch(ShutdownSignalException | ConsumerCancelledException | InterruptedException ex){
            Logger.getLogger(RabbitMQReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }

        return message;
    }
    
//    public static String consumerA(){
//        String message = "";
//        try{
//            System.out.println(" [x] Waiting for message.");
//            boolean consuming = true;
//            while(consuming){
//                QueueingConsumer.Delivery delivery = consumer2.nextDelivery();
//                message = new String(delivery.getBody());
//                String routingKey = delivery.getEnvelope().getRoutingKey();
//
//                System.out.println(" [x] Received -> '" + routingKey + "':'" + message + "'");
//                consuming = false;
//            }
//        } catch(ShutdownSignalException | ConsumerCancelledException | InterruptedException ex){
//            Logger.getLogger(RabbitMQReceiver.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return message;
//    }
}


//~ Formatted by Jindent --- http://www.jindent.com
