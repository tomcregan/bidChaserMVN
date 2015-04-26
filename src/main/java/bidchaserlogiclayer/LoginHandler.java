package bidchaserlogiclayer;

//~--- non-JDK imports --------------------------------------------------------

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
     * This method creates a FindIterable object and using the Bson filter, to
     * find the document that is equal to the current username. If the username
     * document already exists the the username is taken the current user will
     * have to select a new name.
     * <p>
     * @param uname the user name of the current user.
     * <p>
     * @return true if the user name already exists or else false.
     *
     * Find the First Document in a Collection
     *  call the first() method on the result of the find() of method
     *
     *
     *   To get the first document in the collection, call the first
     *   () method on the find() operation. collection.find().first()
     *   returns the first document or null rather than a cursor. This is
     *   useful for queries that should only match a single document,
     *   or if you are interested in the first document only.
     *
     **************************************************
     * Title: Connection Settings - Get A Single Document with a Query Filter
     * Author: mongoDB
     * Site Owner: MongoDB.org
     * Date 2015
     * Availibilty: http://mongodb.github.io/mongo-java-driver/3.0/driver
                   /reference/connecting/connection-settings/ 
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
}


//~ Formatted by Jindent --- http://www.jindent.com
