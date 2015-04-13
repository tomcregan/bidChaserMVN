package bidchaserlogiclayer;

import bichaserdataaccesslayer.*;
import static com.mongodb.client.model.Filters.eq;
import java.util.*;
import org.bson.Document;

/**
 *
 * @author tommy
 */
public class RegisterHandler {

    /**
     * Checks the first name of the new user by performing two different tests:
     * <p>
     * Check the length of the string:
     *
     * (a.) cannot be -eq MIN_LENGTH
     * (b.) cannot be -gt MAX_FIRSTNAME_LENGTH
     * <p>
     * MIN_LENGTH (0) and MAX_FIRSTNAME_LENGTH (20) are defined as private class
     * variables. If the string fails the test at this stage the return
     * value is set to INVALID_FIRSTNAME_LENGTH (-1).
     * <p>
     * The second test is to check if the first name contains any non-valid
     * characters.  If the test fails at this stage the return value is set
     * to INVALID_FIRSTNAME_CHARS (-2).
     * <p>
     * If the first name String passes both tests then the return value is set to
     * VALID_FIRSTNAME (0).
     *
     * @param fname              the name to be validated.
     * @return                   current value of the validation process.
     */
    public int validateFirstname(String fname) {
        int retval = ValidationCodes.VALID_FIRSTNAME;

        if (fname.length() > ValidationCodes.MAX_FNAME_LEN) {
            retval = ValidationCodes.INVAL_FNAME_LEN;
        } else {
            if (!checkFristCharForNonValidCharacter(fname)) {
                retval = ValidationCodes.INVAL_FNAME_CHAR;
            } else {
                if (!checkStringForNonValidCharacters(fname)) {
                    retval = ValidationCodes.INVAL_FNAME_CHARS;
                }
            }
        }

        return retval;
    }

    /**
     * Checks the surname of the new user by performing two different tests:
     * <p>
     * Check the length of the string:
     *
     * (a.) cannot be -eq MIN_LENGTH
     * (b.) cannot be -gt MAX_SURNAME_LENGTH
     * <p>
     * MIN_LENGTH (0) and MAX_SURNAME_LENGTH (30) are defined as private class
     * variables. If the string fails the test at this stage the return
     * value is set to INVALID_SURNAME_LENGTH (-1).
     * <p>
     * The second test is to check if the surname contains any non-valid
     * characters.  If the test fails at this stage the return value is set
     * to INVALID_SURNAME_CHARS (-2).
     * <p>
     * If the surname String passes both tests then the return value is set to
     * VALID_SURNAME (0).
     *
     * @param lname              the name to be validated.
     * @return                   current value of the validation process.
     */
    public int validateLastname(String lname) {
        int retval = ValidationCodes.VALID_SURNAME;

        if (!checkFristCharForNonValidCharacter(lname)) {
            retval = ValidationCodes.INVAL_SNAME_CHAR;
        } else if (lname.length() > ValidationCodes.MAX_SNAME_LEN) {
            retval = ValidationCodes.INVAL_SNAME_LEN;
        } else if (!checkStringForNonValidCharacters(lname)) {
            retval = ValidationCodes.INVAL_SNAME_CHARS;
        }

        return retval;
    }

    /**
     * Checks the username of the new user by performing four different tests:
     * <p>
     * 
     * 
     * Check the length of the string:
     *
     * (a.) cannot be -eq MIN_LENGTH
     * (b.) cannot be -gt MAX_USERNAME_LENGTH
     * 
     * <p>
     * MIN_LENGTH (0) and MAX_UNAME_LEN (10) are defined as private class
     * variables. If the string fails the test at this stage the return
     * value is set to INVAL_UNAME_LEN (765217).
     * <p>
     * 
     * The second test is to check if the username contains any non-valid
     * characters. Non-valid characters are any characters that are neither a 
     * letter or a digit.If the test fails at this stage the return value is set
     * to INVAL_UNAME_CHARS (765190).
     * <p>
     * 
     * <p>
     * The third test is to check if the usernames first character is invalid. 
     * If the test fails at this stage the return value is set
     * to INVAL_UNAME_CHAR (765216)
     * <p>
     * 
     * <p>
     * The fourth test is to check if the username already exists in the database.
     * If the test fails at this stage the return value is set to 
     * INVAL_UNAME_EX (765218)
     * <p>
     * 
     * If the username String passes both tests then the return value is set to
     * VALID_UNAME (708115).
     *
     * @param uname              the name to be validated.
     * @return                   current value of the validation process.
     */
    public int validateUsername(String uname) {
        int retval = ValidationCodes.VALID_USERNAME;

        if (!checkFristCharForNonValidCharacter(uname)) {
            retval = ValidationCodes.INVAL_UNAME_CHAR;
        } else if (uname.length() > ValidationCodes.MAX_UNAME_LEN) {
            retval = ValidationCodes.INVAL_UNAME_LEN;
        } else if (isMember(uname)) {
            retval = ValidationCodes.INVAL_UNAME_EX;
        }

        return retval;
    }

    /**
     ** checks the password of the user by performing two tests
     * <p>
     * 
     * Check the length of the pword:
     *
     * (a.) cannot be -eq MIN_LENGTH
     * (b.) cannot be -gt MAX_PWORD_LENGTH
     * 
     * <p>
     * 
     * MIN_LENGTH (0) and MAX_PWORD_LEN (12) are defined as private class
     * variables. If the string fails the test at this stage the return
     * value is set to INVAL_PWORD_LEN (765219).
     * 
     * The second test is to check that the password contains any non
     * alpha numeric characters. If the test fails at this stage the return 
     * value is set to INVAL_PWORD_CHARS (765220)
     * 
     * @param pword
     * @return
     */
    public int validatePassword(String pword) {
        int retval = ValidationCodes.VALID_PASSWORD;

        if (pword.length() > ValidationCodes.MAX_PWORD_LEN) {
            retval = ValidationCodes.INVAL_PWORD_LEN;
        } else {
            if (checkStringForNonAlphaNumericCharacters(pword)) {
                retval = ValidationCodes.INVAL_PWORD_CHARS;
            }
        }

        return retval;
    }

    /**
     * * checks the date of birth of the user by performing four tests
     * <p>
     * 
     * Check the dob contains a contains a '/' character and that two '/'
     * characters exist in the dob. If the test fails at this stage the return
     * value is set to INVALID_DOB (765224).
     * 
     * The second test is to check the format of the day, month and year 
     * strings in the dob entry which must be in dd/mm/yyyy. If the test fails 
     * at this stage the return value is set to INVALID_DOB (765224)
     * 
     * The third test is to check the if the dayString, monthString and yearString
     * values contain non numeric characters. If the test fails at this stage
     * the return value is set to INVALID_DOB (765224)
     * 
     * The fourth test is to test for the days in a month are between 1 and 31,
     * the months are between 1 and 12 and that a user
     * cannot be less than 18 years of age. If the test fails at this stage
     * the return value is set to INVALID_DOB (765224)
     */
    public int validateDOB(String dob) {
        int    retval     = ValidationCodes.VALID_DOB;
        String findSlash  = "/";
        int    slashCount = 0;

        for (int i = 0; i < dob.length(); i++) {
            char ch = dob.charAt(i);

            if (ch == '/') {
                slashCount++;
            }
        }

        if (!dob.contains(findSlash) || (slashCount != 2)) {
            retval = ValidationCodes.INVALID_DOB;
        } else {
            String dayStringValue   = dob.substring(0, dob.indexOf(findSlash));
            String monthStringValue = dob.substring(dob.indexOf(findSlash) + 1, dob.indexOf(findSlash) + 3);
            String yearStringValue  = dob.substring(dob.lastIndexOf(findSlash) + 1);

            if ((dayStringValue.length() != 2) || (monthStringValue.length() != 2) || (yearStringValue.length() != 4)) {
                retval = ValidationCodes.INVALID_DOB;
            } else if (!checkStringForNonNumericCharacters(dayStringValue)
                       ||!checkStringForNonNumericCharacters(monthStringValue)
                       ||!checkStringForNonNumericCharacters(yearStringValue)) {
                retval = ValidationCodes.INVALID_DOB;
            } else {
                int dayIntValue   = Integer.parseInt(dayStringValue);
                int monthIntValue = Integer.parseInt(monthStringValue);
                int yearIntValue  = Integer.parseInt(yearStringValue);
                int currentYear   = Calendar.getInstance().get(Calendar.YEAR);

                if (((dayIntValue < 1) || (dayIntValue > 31)) && ((monthIntValue < 1) || (monthIntValue > 12))
                        && (yearIntValue < (currentYear - 18))) {
                    retval = ValidationCodes.INVALID_DOB;
                }
            }
        }

        return retval;
    }

    /**
     ** checks the phone number of the user by performing two tests:
     * 
     * Check the length of the pword:
     * 
     *<p>
     * (a.) cannot be -eq MIN_LENGTH
     * (b.) cannot be -gt MAX_PNUM_LENGTH
     * <p>
     * 
     * The first test checks the users phone number length. If the test fails
     * at this stage the return value is set to INVAL_PNUM_LEN (765221)
     * 
     * The second test checks the users phone number for non-numeric characters.
     * If the test fails at this stage the return value is set to 
     * INVAL_PNUM_CHARS (765202)
     * 
     * @param pnum
     * @return
     */
    public int validatePhoneNumber(String pnum) {
        int retval = ValidationCodes.VALID_PHONENUMBER;

        if (pnum.length() != ValidationCodes.MAX_PNUM_LEN) {
            retval = ValidationCodes.INVAL_PNUM_LEN;
        } else {
            if (!checkStringForNonNumericCharacters(pnum)) {
                retval = ValidationCodes.INVAL_PNUM_CHARS;
            }
        }

        return retval;
    }

    /**
     *
     * @param email
     * @return
     */
    public int validateEmail(String email) {
        int    retval  = ValidationCodes.VALID_EMAIL;
        String findAt  = "@";
        String findDot = ".";

        // next check that the email contains a '@' symbol
        // extract the preAtString: that is the string up to the '@' symbol
        // extract the postAtString: that is the string after the '@' symbol
        String preAtString  = email.substring(0, email.lastIndexOf(findAt));
        String postAtString = email.substring(email.lastIndexOf(findAt) + 1, email.length());

        // if the preAtString contains a dot: firstname.lastname@gmail.com
        // handle that here
        if (preAtString.contains(findDot)) {
            String firstName = preAtString.substring(0, preAtString.indexOf(findDot));
            String lastName  = preAtString.substring(preAtString.indexOf(findDot) + 1, preAtString.length());

            // check if the names contains non valid characters
            if (!checkStringForNonValidCharacters(firstName)) {
                retval = ValidationCodes.INVALID_EMAIL;
            } else if (!checkStringForNonValidCharacters(lastName)) {
                retval = ValidationCodes.INVALID_EMAIL;
            }
        }

        // if the preAtString does not contain a dot
        // handle that here
        else if (!preAtString.contains(findDot)) {
            if (!checkStringForNonAlphaNumericCharacters(preAtString)) {
                retval = ValidationCodes.INVALID_EMAIL;
            }
        } else {
            retval = ValidationCodes.INVALID_EMAIL;
        }

        if (postAtString.contains(findDot) && (retval == ValidationCodes.VALID_EMAIL)) {
            String emailProvider = postAtString.substring(0, postAtString.indexOf(findDot));
            String extension     = postAtString.substring(postAtString.indexOf(findDot) + 1, postAtString.length());

            if (extension.length() < 2) {
                retval = ValidationCodes.INVALID_EMAIL;
            } else if (!checkStringForNonValidCharacters(emailProvider)) {
                retval = ValidationCodes.INVALID_EMAIL;
            } else if (!checkStringForNonValidCharacters(extension)) {
                retval = ValidationCodes.INVALID_EMAIL;
            }
        } else {
            retval = ValidationCodes.INVALID_EMAIL;
        }

        return retval;
    }

    /**
     *
     * @param fname
     * @param lname
     * @param uname
     * @param pword
     * @param dob
     * @param pnum
     * @param email
     * @return
     */
    public String registerNewMember(String fname, String lname, String uname, String pword, String dob, String pnum,
                                    String email) {
        Document newUser = new Document("firstname", fname).append("surname", lname).append("username",
                                        uname).append("password", pword).append("dob", dob).append("phone",
                                            pnum).append("email", email);

        MongoDBInstance.getInstance().getMongoMembersCollection().insertOne(newUser);

        return uname + " :has been added to: " + MongoDBInstance.getInstance().getMongoDatabaseName() + "/"
               + MongoDBInstance.getInstance().getMongoMembersCollectionName();
    }

    /*
     * This method is used to check if the string contains any non-valid
     * characters. Non-valid character is any character that is not a letter.
     *
     *
     *
     *
     *
     *
     */
    private static boolean checkStringForNonValidCharacters(String testString) {
        boolean isValid = true;
        char    ch;

        for (int i = 0; i < testString.length(); i++) {
            ch = testString.charAt(i);

            // printString("" + ch);
            if (!Character.isLetter(ch)) {
                isValid = false;
            }
        }

        return isValid;
    }    // end method

    /*
     * This method is used to check if the string contains any non-valid
     * characters. Non-valid character is any character that is not a letter.
     *
     *
     *
     *
     *
     *
     */
    private static boolean checkStringForNonAlphaNumericCharacters(String testString) {
        boolean isValid = true;
        char    ch;

        for (int i = 0; i < testString.length(); i++) {
            ch = testString.charAt(i);

            if (!Character.isLetter(ch) &&!Character.isDigit(ch)) {
                isValid = false;
            }
        }

        return isValid;
    }    // end method

    /*
     * This method is used to check if the string contains any non-valid
     * characters. Non-valid character is any character that is not a letter.
     *
     *
     *
     *
     *
     *
     */
    private static boolean checkStringForNonNumericCharacters(String testString) {
        boolean isValid = true;
        char    ch;

        for (int i = 0; i < testString.length(); i++) {
            ch = testString.charAt(i);

            if (!Character.isDigit(ch)) {
                isValid = false;
            }
        }

        return isValid;
    }    // end method

    public void setMemberUsername(String uname) {

        // the username of the member
        BidChaserMember currentMember = new BidChaserMember();

        currentMember.setUsername(uname);
    }

    /*
     * This method is used to check if the first character in a string is
     * a letter or not.
     *
     *
     *
     *
     *
     *
     */
    private boolean checkFristCharForNonValidCharacter(String testString) {
        boolean isValid   = true;
        char    firstChar = testString.charAt(0);

        if (!Character.isLetter(firstChar)) {
            isValid = false;
        }

        return isValid;
    }

    /**
     * This method create a FindIterable object and using the Bson filter, to
     * find the document that is equal to the current username.  If the username
     * document already exists the the username is taken the current user will
     * have to select a new name.
     *
     * @param uname the user name of the current user.
     * @return true if the user name already exists or else false.
     */
    private boolean isMember(String uname) {
        boolean  isMember = false;
        Document username = MongoDBInstance.getInstance().getMongoMembersCollection().find(eq("username",
                                uname)).first();

        if (username != null) {
            isMember = true;
        }

        return isMember;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
