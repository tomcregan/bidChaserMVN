package bidchaserlogiclayer;

import java.util.*;


/**
 *
 * @author tommy
 */
public class AuctionScheduler
{

    public AuctionScheduler()
    {
    }

    public void setupAuction(String productTitle, String descriptionText, String startPrice, Date startDate,
            String startTime, String endTime)
    {        
        updateRabbitMQ(productTitle, descriptionText, startPrice, startDate, startTime, endTime);
        auctionControl(startTime, endTime);
    }
    
    
    private void updateRabbitMQ(String productTitle, String descriptionText, String startPrice, Date startDate,
            String startTime, String endTime)
    {
        String message = "productTitle, " + productTitle
                + "\ndescriptionText, " + descriptionText
                + "\nstartPrice, " + startPrice
                + "\nstartDate, " + startDate
                + "\nstartTime, " + startTime
                + "\nendTime, " + endTime;
        RabbitMQSender.send(message);
        System.out.println(RabbitMQReceiver.receive());
    }

    private void auctionControl(String startTime, String endTime)
    {
        AuctionTimer auctionTimer = new AuctionTimer();
        auctionTimer.startTimer(startTime, endTime);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
