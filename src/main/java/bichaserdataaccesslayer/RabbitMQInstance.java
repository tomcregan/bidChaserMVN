package bichaserdataaccesslayer;

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
     */
    public void setUpConnectionFactory()
    {
        ConnectionFactory factory = new ConnectionFactory();
        //set the connection factory options
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        factory.setVirtualHost(VHOST);
        factory.setHost(HOST);
        factory.setPort(PORT);
        System.out.println("connection factory set up");
        getConnectionChannel(factory);
    }
    
    private void getConnectionChannel(ConnectionFactory factory)
    {
        System.out.println("setting channel");
        Connection conn;
        Channel chan;
        try
        {
            System.out.println("1");
            //create a new broker connection
            conn = factory.newConnection();
            System.out.println("2");
            //opening a channel
            chan = conn.createChannel();
            System.out.println("3");
            //declare a queue for connection. (queue name, durable, exclusive, autodelete)
            chan.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println("channel connected");
            //setting the channel  
            setChannel(chan);
        } catch (IOException ex)
        {
            Logger.getLogger(RabbitMQInstance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * For the consumer to receive the produced message from the queue
     * @return
     * @throws IOException 
     */
    public String comsumeMessage() throws IOException
    {
        System.out.println("inside comsume meesage");
        // Create the QueueingConsumer and have it return the channel
        QueueingConsumer consumer = new QueueingConsumer(getChannel());
        System.out.println("current queue: " + QUEUE_NAME);
        //start a no local, non exclusive consumer with a generated tag 
        getChannel().basicConsume(QUEUE_NAME, true, consumer);
        //encapsulates an arbitrary message
        QueueingConsumer.Delivery delivery;
        boolean cosuming = true;
        String message = "No Messages";
        while (cosuming)
        {
            try
            {
                //wait for the next message delivery and return it
                delivery = consumer.nextDelivery();
                
                //retrieve the message body
                message = new String(delivery.getBody());
            } catch (InterruptedException | ShutdownSignalException | ConsumerCancelledException ex)
            {
            }
            //release the channel
            closeChannel();
            //release the connection
            closeConnection();
            //finished consuming
            cosuming = false;
        }
        return message;
    }
    
   

    //print the product details to the console
    public void publishProduct(String product) throws IOException
    {
        System.out.println(" publisher: [" + QUEUE_NAME + "] Waiting for messages.");
        
        if (product == null)
        {
            System.out.println("Object is null");
        } else
        {
            //                  (default exchange, queue name, routing key, sent message) 
            channel.basicPublish("", QUEUE_NAME, null, product.getBytes());
        }
    }
    
    private void setChannel(Channel channel)
    {
        this.channel = channel;
    }

    private Channel getChannel()
    {
        return channel;
    }

    private void closeChannel()
    {
        if (channel != null)
        {
            try
            {
                channel.close();
            } catch (IOException ex)
            {
            }
        }
    }

    private void closeConnection()
    {
        if (connection != null)
        {
            try
            {
                connection.close();
            } catch (IOException ex)
            {
            }
        }
    }

}


//~ Formatted by Jindent --- http://www.jindent.com
