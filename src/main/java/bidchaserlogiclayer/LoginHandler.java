package bidchaserlogiclayer;

import bidchaserdataaccesslayer.MongoDBInstance;
import com.mongodb.client.*;
import org.bson.*;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

/**
 * 
 * 
 *
 * @author tommy
 */
public class LoginHandler {

    /**
     * This method is used to validate the member login details. The are checked
     * against the MongoDB Members Collection, if the current details exist then
     * the member is retval is set to true.
     * <p>
     * The details are checked by creating an FindIterable object which is
     * included in the MongoDB API. Once the FindIterable object has been
     * returned it is checked to see if contains the current member details.
     * <p>
     * @param uname the current user name to checked.
     * @param pword the current user password to be checked.
     * <p>
     * @return the boolean value of the isRetval() method.
     * <p>
     * @see FindIterable
     */
    public boolean validateUserDetails(String uname, String pword) {
        return isMember(uname, pword);
    }

    /**
     * Finds the first document in a collection based on the collection.find().first()
     * method
     * 
     * This returns the first document or else null if no documents in the collection
     * exist matching the username and password entries. 
     * 
     **************************************************
     * Title: Connection Settings - Find the First Document in a Collection
     * Author: mongoDB
     * Site Owner: MongoDB.org
     * Date 2015
     * Availibilty: http://mongodb.github.io/mongo-java-driver/3.0/driver/getting-started/quick-tour/ 
     * (Accessed March 2015)
     *
     **************************************************
     *
     * */
    private boolean isMember(String uname, String pword) {
        boolean  isMember = false;
        Document username = MongoDBInstance.getInstance().getMongoMembersCollection().find(and(eq("username", uname),
                                eq("password", pword))).first();

        if (username != null) {
            isMember = true;
        }

        return isMember;
    }
}//end of rafactored code


//~ Formatted by Jindent --- http://www.jindent.com
