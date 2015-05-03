package bidchaserlogiclayer;


import bidchaserdataaccesslayer.*;
import com.rabbitmq.client.*;
import java.io.*;
import java.util.logging.*;
import org.bson.*;

/**
 *
 * @author tommy
 */
public class RabbitMQSender
{

    /**
     *
     * @param product
     */
    private static final String EXCHANGE_DUR = "topic_bids_dur";
    
    private static final String EXCHANGE_NON_DUR = "topic_Non_Dur";
    
    private static final int LOOP_COUNT = 10000;

    /**
     *
     * @param doc        <p>
     * @param routingKey <p>
     * @throws IOException
     */
    public static void sendDurable(Document doc, String routingKey) throws IOException
    {
        Connection connection = RabbitMQInstance.getInstance().getConnection();
        publisherDurable(connection, routingKey, doc);
    }
    
/*
 **************************************************
 * Title: Publishing Messages
 * Author: RabbitMQ
 * Site Owner: RabbitMQ.com
 * Date 2015
 * Availibilty: https://www.rabbitmq.com/api-guide.html
 *
 * (Accessed April 2015)
 * 
 * *************************************************
 */
    private static void publisherDurable(Connection connection, String routingKey, Document doc)
    {
       try
        {
            System.out.println("1");
            Channel channel = connection.createChannel();
            System.out.println("2");
            channel.exchangeDeclare(EXCHANGE_DUR, "topic", true);
            System.out.println("3");
            for (int i = 0; i < LOOP_COUNT; i++)
            {
                try
                {
                    System.out.println("4");
                    channel.basicPublish(EXCHANGE_DUR, routingKey, 
                            MessageProperties.PERSISTENT_TEXT_PLAIN, doc.toString().getBytes());
                    System.out.println(" [" + i + "] Sent -> '" + routingKey + "':'" + doc.toString() + "'");
                } catch (IOException ex)
                {
                    Logger.getLogger(RabbitMQSender.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex)
        {
            Logger.getLogger(RabbitMQSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//end of refactored code
    
    public static void sendNonDurable(Document doc, String routingKey) throws IOException
    {
        Connection connection = RabbitMQInstance.getInstance().getConnection();
        publisherNonDurable(connection, routingKey, doc);
    }
    
    private static void publisherNonDurable(Connection connection, String routingKey, Document doc)
    {
        try
        {
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NON_DUR, "topic");
            for (int i = 0; i < LOOP_COUNT; i++)
            {
                try
                {
                    channel.basicPublish(EXCHANGE_NON_DUR, routingKey, null, doc.toString().getBytes());
                    System.out.println(" [" + i + "] Sent -> '" + routingKey + "':'" + doc.toString() + "'");
                } catch (IOException ex)
                {
                    Logger.getLogger(RabbitMQSender.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex)
        {
            Logger.getLogger(RabbitMQSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//end of refactored code
}

//~ Formatted by Jindent --- http://www.jindent.com

