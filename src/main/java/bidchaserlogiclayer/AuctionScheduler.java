package bidchaserlogiclayer;

import com.rabbitmq.client.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import org.bson.*;

/**
 *
 * @author tommy
 */
public class AuctionScheduler
{

    public AuctionScheduler()
    {
    }

    public void setupDurableAuction(String productTitle, String descriptionText, String startPrice, Date startDate,
            String startTime, String endTime)
    {
        Document message = new Document("productTitle", productTitle).append("descriptionText",
                descriptionText).append("startPrice", startPrice).append("startDate",
                        startDate).append("startTime", startTime).append("endTime",
                        endTime);
        try
        {
            try
            {
                Connection conn = RabbitMQReceiver.receive();
                RabbitMQReceiver.startDurableListener(conn, "bidchaser_durable");
            } catch (InterruptedException ex)
            {
                Logger.getLogger(AuctionScheduler.class.getName()).log(Level.SEVERE, null, ex);
            }
            RabbitMQSender.sendDurable(message, "bidchaser_durable");
        } catch (IOException ex)
        {
            Logger.getLogger(AuctionScheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println(RabbitMQReceiver.consumer());
    }

    public void setupNonDurableAuction(String productTitle, String descriptionText, String string, Date now, String startTime, String endTime)
    {
        Document message = new Document("productTitle", productTitle).append("descriptionText",
                descriptionText).append("startPrice", string).append("startDate",
                        now).append("startTime", startTime).append("endTime",
                        endTime);
        try
        {
            try
            {
                Connection conn = RabbitMQReceiver.receive();
                RabbitMQReceiver.startDurableListener(conn, "bidchaser_nonDurable");
            } catch (InterruptedException ex)
            {
                Logger.getLogger(AuctionScheduler.class.getName()).log(Level.SEVERE, null, ex);
            }
            RabbitMQSender.sendNonDurable(message, "bidchaser.nonDurable");
        } catch (IOException ex)
        {
            Logger.getLogger(AuctionScheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println(RabbitMQReceiver.consumer());
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
