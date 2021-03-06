package bidchaserdataaccesslayer;

import com.mongodb.*;
import com.mongodb.client.*;
import org.bson.Document;

/*
 **************************************************
 * Title: Java - How to use Singleton Class?
 * Author: Tutorials Point: SimplyEasyLearning
 * Site Owner: tutorialspoint.com
 * Date 2015
 * Availibilty: http://www.tutorialspoint.com/java/java_using_singleton.htm 
 * (Accessed March 2015)
 * 
 * *************************************************
 * @author tommy
 */
public class MongoDBInstance {
    private static MongoDBInstance instance;
    private final String           MONGO_USERNAME            = "silken77";
    private final String           MONGO_PASSWORD            = "silken7";
    private final String           MONGO_DATABASE            = "bidchaser";
    private final String           MONGO_MEMBERS_COLLECTION  = "Members";
    private final String           MONGO_PRODUCTS_COLLECTION = "Products";
    private final String           MONGO_BIDS_COLLECTION = "Bids";
    private final String           MONGO_AUCTION_COLLECTION  = "Auctions";

    public MongoDBInstance() {

        // Exists only to defeat instantiation.
    }
    
    /**
     *
     * @return     
     */
    public static MongoDBInstance getInstance() {
        if (instance == null) {
            instance = new MongoDBInstance();
        }
        return instance;
    }//End of [non-original or refactored code

    
    /*
     *
     **************************************************
     * Title: Connection Settings - MongoClient
     * Author: mongoDB
     * Site Owner: MongoDB.org
     * Date 2015
     * Availibilty: http://mongodb.github.io/mongo-java-driver/3.0/driver
                   /reference/connecting/connection-settings/ 
     * (Accessed March 2015)
     *
     **************************************************
     *
     * This method is used to get the mongodb connection String.
     * 
     */
    private String getMongoConnectionString() {
        return "mongodb://" + getMongoPassword() + ":" + getMongoUsername() 
               + "@ds049171.mongolab.com:49171/"
               + getMongoDatabaseName();
    }

    /*
     *
     **************************************************
     * Title: Connection Settings - Make a Connection
     * Author: mongoDB
     * Site Owner: MongoDB.org
     * Date 2015
     * Availibilty: http://mongodb.github.io/mongo-java-driver/3.0/driver
                   /reference/connecting/connection-settings/ 
     * (Accessed March 2015)
     *
     **************************************************
     *
     * This method is used to get the mongodb database name.
     * 
     */
    private MongoDatabase getMongoDatabase() {
        MongoClient   mongoClient = new MongoClient(new MongoClientURI(getMongoConnectionString()));
        MongoDatabase db          = mongoClient.getDatabase(getMongoDatabaseName());

        return db;
    }
    
    /*
     *
     **************************************************
     * Title: Connection Settings - Get a Collection
     * Author: mongoDB
     * Site Owner: MongoDB.org
     * Date 2015
     * Availibilty: http://mongodb.github.io/mongo-java-driver/3.0/driver
                   /reference/connecting/connection-settings/ 
     * (Accessed March 2015)
     *
     **************************************************
     *
     * This method is used to get the mongodb database name.
     *     
     * This method is used to get the database name for connection 
     * @return      mongo database name     
     */
    public DB getMongoDB() {
        MongoClient mongoClient = new MongoClient(new MongoClientURI(getMongoConnectionString()));
        DB          db          = mongoClient.getDB(MongoDBInstance.getInstance().getMongoDatabaseName());

        return db;       
    }//End of [non-original or refactored] code

    /*
     *
     **************************************************
     * Title: Connection Settings - Get a Collection
     * Author: mongoDB
     * Site Owner: MongoDB.org
     * Date 2015
     * Availibilty: http://mongodb.github.io/mongo-java-driver/3.0/driver
                   /reference/connecting/connection-settings/ 
     * (Accessed March 2015)
     *
     **************************************************
     * This method is used to get the mongodb members collection that is going to
     * be written to by the user.
     * 
     * @return      the members collection name
     */
    public MongoCollection<Document> getMongoMembersCollection() {
        MongoCollection<Document> collection = getMongoDatabase().getCollection(getMongoMembersCollectionName());

        return collection;
    }//End of [non-original or refactored] code

    /*
     *
     **************************************************
     * Title: Connection Settings - Get a Collection
     * Author: mongoDB
     * Site Owner: MongoDB.org
     * Date 2015
     * Availibilty: http://mongodb.github.io/mongo-java-driver/3.0/driver
                   /reference/connecting/connection-settings/ 
     * (Accessed March 2015)
     *
     **************************************************
     *
     * This method is used to get the mongodb products collection that is going to
     * be written to by the user.
     *
     * @return      mongo products collection name
     */
    public MongoCollection<Document> getMongoProductsCollection() {
        MongoCollection<Document> collection = getMongoDatabase().getCollection(getMongoProductsCollectionName());

        return collection;
    }//End of [non-original or refactored] code

    /*
     *
     **************************************************
     * Title: Connection Settings - Get a Collection
     * Author: mongoDB
     * Site Owner: MongoDB.org
     * Date 2015
     * Availibilty: http://mongodb.github.io/mongo-java-driver/3.0/driver
                   /reference/connecting/connection-settings/ 
     * (Accessed March 2015)
     *
     **************************************************
     * This method is used to get the mongodb bids collection that is going to
     * be written to by the user.
     */
    public MongoCollection<Document> getMongoBidsCollection(){
        MongoCollection<Document> collection = getMongoDatabase().getCollection(getMongoBidsCollectionName());

        return collection;
    }
    
    
    /*
     *
     **************************************************
     * Title: Connection Settings - Get a Collection
     * Author: mongoDB
     * Site Owner: MongoDB.org
     * Date 2015
     * Availibilty: http://mongodb.github.io/mongo-java-driver/3.0/driver
                   /reference/connecting/connection-settings/ 
     * (Accessed March 2015)
     *
     **************************************************
     * This method is used to get the mongodb bids collection that is going to
     * be written to by the user.
     */
    public MongoCollection<Document> getMongoAuctionsCollection(){
        MongoCollection<Document> collection = getMongoDatabase().getCollection(getMongoAuctionsCollectionName());

        return collection;
    }

    /**
     * @return the MONGO_USERNAME
     */
    public String getMongoUsername() {
        return MONGO_USERNAME;
    }

    /**
     * @return the MONGO_PASSWORD
     */
    public String getMongoPassword() {
        return MONGO_PASSWORD;
    }

    /**
     * @return the MONGO_DATABASE
     */
    public String getMongoDatabaseName() {
        return MONGO_DATABASE;
    }

    /**
     * @return the MONGO_MEMBERS_COLLECTION
     */
    public String getMongoMembersCollectionName() {
        return MONGO_MEMBERS_COLLECTION;
    }

    /**
     * @return the MONGO_PRODUCTS_COLLECTION
     */
    public String getMongoProductsCollectionName() {
        return MONGO_PRODUCTS_COLLECTION;
    }

    /**
     * @return the MONGO_AUCTION_COLLECTION
     */
    public String getMongoAuctionsCollectionName() {
        return MONGO_AUCTION_COLLECTION;
    }
    
    public String getMongoBidsCollectionName(){
        return MONGO_BIDS_COLLECTION;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
