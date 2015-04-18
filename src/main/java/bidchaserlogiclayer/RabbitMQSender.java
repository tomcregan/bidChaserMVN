package bidchaserlogiclayer;

import bichaserdataaccesslayer.*;
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
    public static void send(String product)
    {
        
        RabbitMQInstance.getInstance().setUpConnectionFactory();
        try {
            RabbitMQInstance.getInstance().publishProduct(product);
        } catch (IOException ex) {
            Logger.getLogger(RabbitMQSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
