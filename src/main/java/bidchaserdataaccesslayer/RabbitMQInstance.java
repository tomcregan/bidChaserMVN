package bidchaserdataaccesslayer;

import com.rabbitmq.client.*;
import java.io.*;
import java.util.logging.*;

/**
 *
 * @author tommy
 * 
 */
public class RabbitMQInstance
{
    private static RabbitMQInstance instance;
    private final String QUEUE_NAME = "BidChaser";
    private final String USERNAME = "zgcjhbdl";
    private final String PASSWORD = "vMIBKnJATfGqNK7nOQuQFrogc54KiF8w";
    private final String VHOST = "zgcjhbdl";
    private final String HOST = "bunny.cloudamqp.com";
    private final int PORT = 5672;
    private Channel channel;
    private Connection connection;

    /**
     *
     */
    protected RabbitMQInstance()
    {
        // Exists only to defeat instantiation.
    }

    /**
     *
     * @return
     */
    public static RabbitMQInstance getInstance()
    {
        if (instance == null)
        {
            instance = new RabbitMQInstance();
        }
        return instance;
    }
    
    /**
     * Create an instance of the Rabbit Client ConnectionFactory
     * for TCP connection to the broker
     * 
     * @return 
     */
    public Connection getConnection(){
        Connection conn = null;
        try{
            ConnectionFactory factory = new ConnectionFactory();
            factory.setUsername(USERNAME);
            factory.setPassword(PASSWORD);
            factory.setVirtualHost(VHOST);
            factory.setHost(HOST);
            factory.setPort(PORT);
            conn = factory.newConnection();

        } catch(IOException ex){
            ex.printStackTrace(System.out);
        }
        return conn;
    }
        
}


//~ Formatted by Jindent --- http://www.jindent.com
