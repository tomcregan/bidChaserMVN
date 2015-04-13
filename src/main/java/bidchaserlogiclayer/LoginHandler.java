package bidchaserlogiclayer;

import bichaserdataaccesslayer.*;
import com.mongodb.client.*;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import org.bson.*;

/**
 *
 * @author tommy
 */
public class LoginHandler
{

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
    public boolean validateUserDetails(String uname, String pword)
    {
        return isMember(uname, pword);
    }

    public void setMemberUsername(String uname)
    {
        // the username of the member
        BidChaserMember currentMember = new BidChaserMember();
        currentMember.setUsername(uname);
    }

    /**
     * This method create a FindIterable object and using the Bson filter, to
     * find the document that is equal to the current username. If the username
     * document already exists the the username is taken the current user will
     * have to select a new name.
     * <p>
     * @param uname the user name of the current user.
     * <p>
     * @return true if the user name already exists or else false.
     */
    private boolean isMember(String uname, String pword)
    {
        boolean isMember = false;
        Document username
                = MongoDBInstance.getInstance().getMongoMembersCollection().find(and(eq("username", uname),
                                eq("password", pword))).first();
        if (username != null)
        {
            isMember = true;
        }
        return isMember;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
