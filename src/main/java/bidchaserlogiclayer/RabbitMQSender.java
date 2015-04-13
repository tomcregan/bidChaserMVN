package bidchaserlogiclayer;

import bichaserdataaccesslayer.*;
import java.io.*;

/**
 *
 * @author tommy
 */
public class RabbitMQSender
{

    public static void send(String product)
    {
        RabbitMQInstance.getInstance().setUpConnectionFactory();
        try
        {
            RabbitMQInstance.getInstance().publishProduct(product);
        } catch (IOException ex)
        {
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
