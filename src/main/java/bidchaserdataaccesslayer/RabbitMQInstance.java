package bidchaserdataaccesslayer;

import com.rabbitmq.client.*;
import java.io.*;


/**
 *
 * @author tommy
 * 
 */
public class RabbitMQInstance
{
    private static RabbitMQInstance instance;
    private final String USERNAME = "zgcjhbdl";
    private final String PASSWORD = "vMIBKnJATfGqNK7nOQuQFrogc54KiF8w";
    private final String VHOST = "zgcjhbdl";
    private final String HOST = "bunny.cloudamqp.com";
    private final int PORT = 5672;
    

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
     * Creates an instance of the Rabbit Client ConnectionFactory
     * for TCP connection to the broker
     * 
     *
     **************************************************
     * Title: Interface Connection
     * Author: RabbitMQ
     * Site Owner: RabbitMQ.com
     * Date 2015
     * Availibilty: http://www.rabbitmq.com/javadoc/com/rabbitmq/client/Connection.html 
     * (Accessed March 2015)
     * @return 
     */
    public Connection getConnection(){
        //initialise the connection
        Connection conn = null;
        try{
            ConnectionFactory factory = new ConnectionFactory();
            factory.setUsername(USERNAME);
            factory.setPassword(PASSWORD);
            factory.setVirtualHost(VHOST);
            factory.setHost(HOST);
            factory.setPort(PORT);
            //open the channel
            conn = factory.newConnection();

        } catch(IOException ex){
            ex.printStackTrace(System.out);
        }
        return conn;
    }
        
}//end of refactored code


//~ Formatted by Jindent --- http://www.jindent.com
