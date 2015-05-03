package bidchaserlogiclayer;

import bidchaserdataaccesslayer.*;
import com.rabbitmq.client.*;
import java.io.*;
import java.util.logging.*;

/**
 *
 * @author tommy
 */
public class RabbitMQReceiver {
    
    private static final String EXCHANGE_DUR = "topic_bids_dur";
    private static final String Q_NAME_DUR = "durable";
    
    private static final String EXCHANGE_NON_DUR = "topic_Non_Dur";
    private static final String Q_NAME_NON_DURABLE = "non_durable";
    
    private static final int        LOOP_COUNT    = 10000;
    private static QueueingConsumer consumer;

    /**
     * Method description
     * <p>
     *
     * @return <p>
     * @throws IOException
     * @throws InterruptedException
     */
    public static Connection receive() throws IOException, InterruptedException {
        Connection connection = RabbitMQInstance.getInstance().getConnection();

        return connection;
    }
    
 /*
 **************************************************
 * Title: Java - Working with message routing using Topic Exchanges
 * Author: RbbitMQ Cookbook
 * Pages: 30-32
 * Date: April 2015 
 * (Accessed March 2015)
 * 
 * *************************************************
 */
    
    public static void startDurableListener(Connection connection, String bindingKey) {
        System.out.println("Listening for messages");

        try {
            //creates a channel
            Channel channel = connection.createChannel();
            //declare an exchange   ( exchange name, exchange type, durable) 
            channel.exchangeDeclare(EXCHANGE_DUR, "topic", true);
            //declare a queue   (queue name, durable, exclusive, auto-delete, known-name)
            channel.queueDeclare(Q_NAME_DUR, true, false, false, null);
            //print the queue name
            System.out.println("Queue Name: " + Q_NAME_DUR);
            //binding the queue to exchange (queue name, exchange name, binding key)
            channel.queueBind(Q_NAME_DUR, EXCHANGE_DUR, bindingKey);
            //new queueing consumer to deliver messages
            consumer = new QueueingConsumer(channel);
            //start a consumer (queue name, auto-ack, callback)
            channel.basicConsume(Q_NAME_DUR, false, consumer);
        } catch (IOException ex) {
            Logger.getLogger(RabbitMQReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        
/*
 **************************************************
 * Title: Java - Working with message routing using Topic Exchanges
 * Author: RbbitMQ Cookbook
 * Pages: 30-32
 * Date: April 2015 
 * (Accessed March 2015)
 * 
 * *************************************************
 */   
    public static void startNonDurableListener(Connection connection, String bindingKey) {
        System.out.println("Listening for messages");

        try {
            Channel channel = connection.createChannel();
            //declare a non durable exchange
            channel.exchangeDeclare(EXCHANGE_NON_DUR, "topic");
            //declare a non durable queue
            channel.queueDeclare(Q_NAME_NON_DURABLE, false, false, false, null);
            
            System.out.println("Queue Name: " + Q_NAME_DUR);
            channel.queueBind(Q_NAME_NON_DURABLE, EXCHANGE_NON_DUR, bindingKey);
            consumer = new QueueingConsumer(channel);
            channel.basicConsume(Q_NAME_NON_DURABLE, false, consumer);
        } catch (IOException ex) {
            Logger.getLogger(RabbitMQReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

/*
    **************************************************
    * Title: Consuming Messages
    * Author: Sigismondo Boschi, Gabriele Santomaggio
    * Book: RabbitMQ CookBook
    * Pages: 14-16
    * Date April 2015
    * (Accessed April 2015)
    * *************************************************
 */   
    public static String consumer() {
        String message = "";
        try
        {
            System.out.println(" [x] Waiting for message.");
            for (int i = 1; i < LOOP_COUNT; i++)
            {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                message = new String(delivery.getBody());
                String routingKey = delivery.getEnvelope().getRoutingKey();
                System.out.println(" [" + i + "] Received -> '" + routingKey + "':'" + message + "'");
            }
        } catch (ShutdownSignalException | ConsumerCancelledException | InterruptedException ex)
        {
            Logger.getLogger(RabbitMQReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
    }
}

//~ Formatted by Jindent --- http://www.jindent.com
