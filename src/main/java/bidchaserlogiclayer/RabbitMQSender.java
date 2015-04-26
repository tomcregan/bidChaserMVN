package bidchaserlogiclayer;

import bidchaserdataaccesslayer.RabbitMQInstance;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;

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
   private static final String EXCHANGE_NAME = "topic_bids";

    /**
     * 
     * @param doc
     *
     * @throws IOException
     */
    public static void send(Document doc) throws IOException{
        Connection connection = RabbitMQInstance.getInstance().getConnection();
        publisher(connection, "bidchaser.auction01", doc);
    }

    private static void publisher(Connection connection, String routingKey, Document doc){
        try{
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");

            channel.basicPublish(EXCHANGE_NAME, routingKey, null, doc.toString().getBytes());
            System.out.println(" [*] Sent -> '" + routingKey + "':'" + doc.toString() + "'");
        } catch(IOException ex){
            Logger.getLogger(RabbitMQSender.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if(connection != null){
                try{
                    connection.close();
                } catch(IOException ex){
                    Logger.getLogger(RabbitMQSender.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
