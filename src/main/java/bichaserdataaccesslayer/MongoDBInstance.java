package bichaserdataaccesslayer;

import com.mongodb.*;
import com.mongodb.client.*;
import org.bson.Document;

/**
 *
 * @author tommy
 */
public class MongoDBInstance {
    private static MongoDBInstance instance;
    private final String           MONGO_USERNAME            = "silken77";
    private final String           MONGO_PASSWORD            = "silken7";
    private final String           MONGO_DATABASE            = "bidchaser";
    private final String           MONGO_MEMBERS_COLLECTION  = "Members";
    private final String           MONGO_PRODUCTS_COLLECTION = "Products";
    private final String           MONGO_AUCTION_COLLECTION  = "Auctions";

    /**
     *
     */
    protected MongoDBInstance() {

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
    }

    /*
     * This method is used to get the mongodb connection String.
     */
    private String getMongoConnectionString() {
        return "mongodb://" + getMongoPassword() + ":" + getMongoUsername() + "@ds049171.mongolab.com:49171/"
               + getMongoDatabaseName();
    }

    private MongoDatabase getMongoDatabase() {
        MongoClient   mongoClient = new MongoClient(new MongoClientURI(getMongoConnectionString()));
        MongoDatabase db          = mongoClient.getDatabase(getMongoDatabaseName());

        return db;
    }

    /**
     *
     * @return
     */
    public DB getMongoDB() {
        MongoClient mongoClient = new MongoClient(new MongoClientURI(getMongoConnectionString()));
        DB          db          = mongoClient.getDB(MongoDBInstance.getInstance().getMongoDatabaseName());

        return db;
    }

    /*
     * This method is used to get the mongodb membesr collection that is going to
     * be written to by the user.
     */

    /**
     *
     * @return
     */
    public MongoCollection<Document> getMongoMembersCollection() {
        MongoCollection<Document> collection = getMongoDatabase().getCollection(getMongoMembersCollectionName());

        return collection;
    }

    /*
     * This method is used to get the mongodb products collection that is going to
     * be written to by the user.
     */

    /**
     *
     * @return
     */
    public MongoCollection<Document> getMongoProductsCollection() {
        MongoCollection<Document> collection = getMongoDatabase().getCollection(getMongoProductsCollectionName());

        return collection;
    }

    /*
     * This method is used to get the mongodb products collection that is going to
     * be written to by the user.
     */

    /**
     *
     * @return
     */
    public MongoCollection<Document> getMongoAuctionsCollection() {
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
}


//~ Formatted by Jindent --- http://www.jindent.com
