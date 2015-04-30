package bidchaserlogiclayer;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;


/**
 *
 * @author tommy
 */
public class AuctionScheduler
{
    public AuctionScheduler()
    {
    }
    
    public void setupAuctionDurable(String productTitle, String descriptionText, String startPrice, Date startDate,
            String startTime, String endTime)
    {        
        testRabbitMQDurable(productTitle, descriptionText, startPrice, startDate, startTime, endTime);
        
    }

    public void setupAuctionNonDurable(String productTitle, String descriptionText, String startPrice, Date startDate,
            String startTime, String endTime)
    {        
        testRabbitMQNonDurable(productTitle, descriptionText, startPrice, startDate, startTime, endTime);
        //auctionControl(startTime, endTime);
    }
    
    
    private void testRabbitMQDurable(String productTitle, String descriptionText, String startPrice, Date startDate,
            String startTime, String endTime)
    {
        Document message = new Document("productTitle", productTitle).append("descriptionText",
                                          descriptionText).append("startPrice", startPrice).append("startDate",
                                              startDate).append("startTime", startTime).append("endTime",
                                                  endTime);
        try {
            try {
                RabbitMQReceiver.receive();
                System.out.println("1");
            } catch (InterruptedException ex) {
                Logger.getLogger(AuctionScheduler.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("2");
            RabbitMQSender.send(message);
            
        } catch (IOException ex) {
            Logger.getLogger(AuctionScheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("3");
        System.out.println(RabbitMQReceiver.consumer());
    }
    
    private void testRabbitMQNonDurable(String productTitle, String descriptionText, String startPrice, Date startDate,
            String startTime, String endTime)
    {
        Document message = new Document("productTitle", productTitle).append("descriptionText",
                                          descriptionText).append("startPrice", startPrice).append("startDate",
                                              startDate).append("startTime", startTime).append("endTime",                                               endTime);
        try {
            try {
                RabbitMQReceiver.receive();
            } catch (InterruptedException ex) {
                Logger.getLogger(AuctionScheduler.class.getName()).log(Level.SEVERE, null, ex);
            }
            RabbitMQSender.send(message);
            
        } catch (IOException ex) {
            Logger.getLogger(AuctionScheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(RabbitMQReceiver.consumer());
    }


//    private void auctionControl(String startTime, String endTime)
//    {
//        AuctionTimer auctionTimer = new AuctionTimer();
//        auctionTimer.startTimer(startTime, endTime);
//    }
}


//~ Formatted by Jindent --- http://www.jindent.com
