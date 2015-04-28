package bidchaserlogiclayer;

//~--- non-JDK imports --------------------------------------------------------

import bidchaserdataaccesslayer.RabbitMQInstance;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;


import org.bson.Document;

//~--- JDK imports ------------------------------------------------------------

import java.io.*;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tommy
 */
public class RabbitMQSender {

    /**
     *
     * @param product
     */
    private static final String EXCHANGE_NAME = "topic_bid_ex";
    private static final String EXCHANGE_ND = "topic_bidND";
    private final static String Q_NAME_D = "bid_durable";
    private final static int LOOP_COUNT = 10000;
    //private final static String Q_NAME_ND = "bid_non_durable";
    //private final static String Q_NAME_D_BASICACK = "bid_basic_ack";
    

    /**
     *
     * @param doc
     *
     * @throws IOException
     */
    public static void send(Document doc) throws IOException {
        Connection connection = RabbitMQInstance.getInstance().getConnection();

        durablePublish(connection, "bidchaser.auction01", doc);
        nonDurablePublish(connection, "bidchaser.auction02", doc);
    }

    private static void durablePublish(Connection connection, String routingKey, Document doc) {
        try {
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, "topic", true);
            
            for (int i = 1; i <= LOOP_COUNT; i++) {
                try {
                    channel.basicPublish(EXCHANGE_ND, routingKey, null, doc.toString().getBytes());
                } catch (IOException ex) {
                    Logger.getLogger(RabbitMQSender.class.getName()).log(Level.SEVERE, null, ex);
                }

                System.out.println(" [*] Sent -> '" + routingKey + "':'" + doc.toString() + "'");
            }
        } catch (IOException ex) {
            Logger.getLogger(RabbitMQSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void nonDurablePublish(Connection connection, String routingKey, Document doc) {
        try {
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_ND, "topic");
           
            for (int i = 1; i <= LOOP_COUNT; i++) {
                try {
                    channel.basicPublish(EXCHANGE_ND, routingKey, null, doc.toString().getBytes());
                } catch (IOException ex) {
                    Logger.getLogger(RabbitMQSender.class.getName()).log(Level.SEVERE, null, ex);
                }

                System.out.println(" [*] Sent -> '" + routingKey + "':'" + doc.toString() + "'");
            }
        } catch (IOException ex) {
            Logger.getLogger(RabbitMQSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
}


//~ Formatted by Jindent --- http://www.jindent.com
