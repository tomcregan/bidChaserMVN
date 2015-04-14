package bidchaserlogiclayer;


import bichaserdataaccesslayer.MongoDBInstance;
import com.mongodb.client.MongoCursor;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author tommy
 */
public class DisplayAuctionsHandler {

    /**
     *
     * @return
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
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
