package bidchaserlogiclayer;

import bichaserdataaccesslayer.RabbitMQInstance;
import java.io.IOException;

/**
 *
 * @author tommy
 */
public class RabbitMQReceiver {
    public static String receive() {
        RabbitMQInstance.getInstance().setUpConnectionFactory();

        String reply = "";

        try {
            reply = RabbitMQInstance.getInstance().comsumeMessage();
        } catch (IOException ex) {}

        return reply;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
