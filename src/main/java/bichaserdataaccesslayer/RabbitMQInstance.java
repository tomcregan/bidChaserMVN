package bichaserdataaccesslayer;

import com.rabbitmq.client.*;
import java.io.*;
import java.util.logging.*;

/**
 *
 * @author tommy
 */
public class RabbitMQInstance
{

    private static RabbitMQInstance instance;
    private final String QUEUE_NAME = "BID_CHASER";
    private final String USERNAME = "newUser";
    private final String PASSWORD = "password";
    private final String VHOST = "tester";
    private final String HOST = "192.168.1.4";
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

    public void setUpConnectionFactory()
    {
        ConnectionFactory factory = new ConnectionFactory();
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
        Connection conn = null;
        Channel chan = null;
        try
        {
            System.out.println("1");
            conn = factory.newConnection();
            System.out.println("2");
            chan = conn.createChannel();
            System.out.println("3");
            chan.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println("channel connected");
            setChannel(chan);
        } catch (IOException ex)
        {
            Logger.getLogger(RabbitMQInstance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String comsumeMessage() throws IOException
    {
        System.out.println("inside comsume meesage");
        QueueingConsumer consumer = new QueueingConsumer(getChannel());
        System.out.println("current queue: " + QUEUE_NAME);
        getChannel().basicConsume(QUEUE_NAME, true, consumer);
        QueueingConsumer.Delivery delivery;
        boolean cosuming = true;
        String message = "No Messages";
        while (cosuming)
        {
            try
            {
                delivery = consumer.nextDelivery();
                message = new String(delivery.getBody());
            } catch (InterruptedException | ShutdownSignalException | ConsumerCancelledException ex)
            {
            }
            closeChannel();
            closeConnection();
            cosuming = false;
        }
        return message;
    }

    public void publishProduct(String product) throws IOException
    {
        System.out.println(" publisher: [" + QUEUE_NAME + "] Waiting for messages.");
        
        if (product == null)
        {
            System.out.println("Object is null");
        } else
        {
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

    private void setConnection(Connection connection)
    {
        this.connection = connection;
    }

    private Connection getConnection()
    {
        return connection;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
