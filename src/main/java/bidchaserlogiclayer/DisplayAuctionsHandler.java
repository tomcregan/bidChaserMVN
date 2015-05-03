package bidchaserlogiclayer;


import bidchaserdataaccesslayer.MongoDBInstance;
import com.mongodb.client.MongoCursor;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author tommy
 */
public class DisplayAuctionsHandler {

    
    /*
    **************************************************
     * Title: Find all documents in a collection
     * Author: MongoDB
     * Site Owner: MongoDB.com
     * Date: 29/09/2009
     * Availibilty: http://mongodb.github.io/mongo-java-driver/3.0/driver/getting-started/quick-tour/ 
     * (Accessed April 2015)
     * 
     * ************************************************** 
     */
    public List findAllProducts() {
        List<Document>        currentAuctions = new ArrayList<>();
        MongoCursor<Document> cursor          = null;

        try {
            cursor = MongoDBInstance.getInstance().getMongoProductsCollection().find().iterator();

            while (cursor.hasNext()) {
                currentAuctions.add(cursor.next());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return currentAuctions;
    }//end of refactored code
}


//~ Formatted by Jindent --- http://www.jindent.com
