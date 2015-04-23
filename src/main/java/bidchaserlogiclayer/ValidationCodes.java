package bidchaserlogiclayer;

/**
 * This class contains a list of the validation codes that are used to signal
 * if a user entered value has been successfully validated or not. Each
 * code is written in a hexadecimal number, and is a unique value.  All valid
 * codes start with a 0xACE** value to signal success, while invalid codes start
 * with 0xBAD** to signal failure.
 *
 * @author tommy
 */
public class ValidationCodes {

    // -------------------------------------------------------------------------
    // --- AddNewProductHandler ValidationCodes --------------------------------
    // -------------------------------------------------------------------------

    /**
     * Code for valid product title:
     * decimal = 708097
     */
    public final static int VALID_TITLE = 0xACE01;

    /**
     * Code for invalid product title due to the length of the string:
     * decimal = 765185
     */
    public final static int INVAL_TITLE_LEN = 0xBAD01;

    /**
     * Code for invalid product title due to an illegal character in the string:
     * decimal = 765186
     */
    public final static int INVAL_TITLE_CHARS = 0xBAD02;

    /**
     * Code for valid product description:
     * decimal = 708098
     */
    public final static int VALID_DESCRIPTION = 0xACE02;

    /**
     * Code for invalid product description due to the length of the string:
     * decimal = 765187
     */
    public final static int INVAL_DESCR_LEN = 0xBAD03;

    /**
     * Code for valid product price:
     * decimal = 708099
     */
    public final static int VALID_PRICE = 0xACE03;

    /**
     * Code for invalid product price due to an length of the string:
     * decimal = 765188
     */
    public final static int INVAL_PRICE_LEN = 0xBAD04;

    /**
     * Code for invalid product price due to an illegal character in the string:
     * decimal = 765189
     */
    public final static int INVAL_PRICE_CHARS = 0xBAD05;

    /**
     * Code for valid product start date:
     * decimal = 708100
     */
    public final static int VALID_STARTDATE = 0xACE04;

    /**
     * Code for invalid product start date due to an length of the string:
     * decimal = 765190
     */
    public final static int INVAL_STDATE_LEN = 0xBAD06;

    /**
     * Code for invalid product start date due to illegal time format:
     * decimal = 765191
     */
    public final static int INVAL_STDATE_TIME = 0xBAD07;

    /**
     * Code for valid product start time:
     * decimal = 708101
     */
    public final static int VALID_STARTTIME = 0XACE05;

    /**
     * Code for invalid product start time due to an length of the string:
     * decimal = 765192
     */
    public final static int INVAL_STTIME_LEN = 0xBAD08;

    /**
     * Code for invalid product start time due to illegal time format:
     * decimal = 765193
     */
    public final static int INVAL_STTIME_TYPE = 0xBAD09;

    /**
     * Code for valid product end time:
     * decimal = 708102
     */
    public final static int VALID_ENDTIME = 0xACE06;

    /**
     * Code for invalid product end time due to an length of the string:
     * decimal = 765200
     */
    public final static int INVAL_ETIME_LEN = 0xBAD11;

    /**
     * Code for invalid product end time due to illegal time format:
     * decimal = 765201
     */
    public final static int INVAL_ETIME_TYPE = 0xBAD12;

    /**
     * Code for valid product image:
     * decimal = 708103
     */
    public final static int VALID_IMAGE = 0xACE07;

    /**
     * Code for invalid product image due to illegal image type:
     * decimal = 765203
     */
    public final static int INVAL_IMAGE_TYPE = 0xBAD13;

    // -------------------------------------------------------------------------
    // --- AddNewProductHandler ValidationStringLengths ------------------------
    // -------------------------------------------------------------------------

    /**
     * The maximum length of the product title.
     */
    public final static int MAX_PROC_TITLE = 20;

    /**
     * The maximum length of the product description.
     */
    public final static int MAX_DESCR_LEN = 140;

    /**
     * The maximum length of the product start price.
     */
    public final static int MAX_PRICE_LEN = 5;

    /**
     * The maximum length of the product start time.
     */
    public final static int MAX_STTIME_LEN = 5;

    /**
     * The maximum length of the product end time.
     */
    public final static int MAX_ETIME_LEN = 5;
        
    /**
     * 
     */

    // -------------------------------------------------------------------------
    // --- RegisterHandler ValidationCodes -------------------------------------
    // -------------------------------------------------------------------------

    /**
     * Code for valid member first name:
     * decimal = 708113
     */
    public final static int VALID_FIRSTNAME = 0xACE11;

    /**
     * Code for invalid member first name due to an illegal first character:
     * decimal = 765204
     */
    public final static int INVAL_FNAME_CHAR = 0xBAD14;

    /**
     * Code for invalid member first name due to the length of the string:
     * decimal = 765205
     */
    public final static int INVAL_FNAME_LEN = 0xBAD15;

    /**
     * Code for invalid member first name due to an illegal character:
     * decimal = 765206
     */
    public final static int INVAL_FNAME_CHARS = 0xBAD16;

    /**
     * Code for valid member surname:
     * decimal = 708114
     */
    public final static int VALID_SURNAME = 0xACE12;

    /**
     * Code for invalid member surname due to an illegal first character:
     * decimal = 765207
     */
    public final static int INVAL_SNAME_CHAR = 0xBAD17;

    /**
     * Code for invalid member surname due to the length of the string:
     * decimal = 765208
     */
    public final static int INVAL_SNAME_LEN = 0xBAD18;

    /**
     * Code for invalid member first name due to an illegal character:
     * decimal = 765209
     */
    public final static int INVAL_SNAME_CHARS = 0xBAD19;

    /**
     * Code for valid member username:
     * decimal = 708115
     */
    public final static int VALID_USERNAME = 0xACE13;

    /**
     * Code for invalid member username due to an illegal first character:
     * decimal = 765216
     */
    public final static int INVAL_UNAME_CHAR = 0xBAD20;

    /**
     * Code for invalid member username due to the length of the string:
     * decimal = 765217
     */
    public final static int INVAL_UNAME_LEN = 0xBAD21;

    /**
     * Code for invalid member user already exists:
     * decimal = 765218
     */
    public final static int INVAL_UNAME_EX = 0xBAD22;

    /**
     * Code for valid member password:
     * decimal = 708116
     */
    public final static int VALID_PASSWORD = 0xACE14;

    /**
     * Code for invalid member password due to the length of the string:
     * decimal = 765219
     */
    public final static int INVAL_PWORD_LEN = 0xBAD23;

    /**
     * Code for invalid member password due to illegal characters:
     * decimal = 765220
     */
    public final static int INVAL_PWORD_CHARS = 0xBAD24;

    /**
     * Code for valid member phone number:
     * decimal = 708117
     */
    public final static int VALID_PHONENUMBER = 0xACE15;

    /**
     * Code for invalid member phone number due to the length of the string:
     * decimal = 765221
     */
    public final static int INVAL_PNUM_LEN = 0xBAD25;

    /**
     * Code for invalid member phone number due to illegal characters:
     * decimal = 765222
     */
    public final static int INVAL_PNUM_CHARS = 0xBAD26;

    /**
     * Code for valid member email:
     * decimal = 708118
     */
    public final static int VALID_EMAIL = 0xACE16;

    /**
     * Code for invalid member email:
     * decimal = 765223
     */
    public final static int INVALID_EMAIL = 0xBAD27;

    /**
     * Code for valid member date of birth:
     * decimal = 708119
     */
    public final static int VALID_DOB = 0xACE17;

    /**
     * Code for invalid member date of birth:
     * decimal = 765224
     */
    public final static int INVALID_DOB = 0xBAD28;

    // -------------------------------------------------------------------------
    // --- RegisterHandler ValidationStringLengths -----------------------------
    // -------------------------------------------------------------------------

    /**
     * The minimum value for any string.
     */
    public final static int MIN_LENGTH = 0;

    /**
     * The maximum length of the members first name.
     */
    public final static int MAX_FNAME_LEN = 10;

    /**
     * The maximum length of the members surname.
     */
    public final static int MAX_SNAME_LEN = 30;

    /**
     * The maximum length of the members username.
     */
    public final static int MAX_UNAME_LEN = 10;

    /**
     * The maximum length of the members password.
     */
    public final static int MAX_PWORD_LEN = 20;

    /**
     * The maximum length of the members phone number.
     */
    public final static int MAX_PNUM_LEN = 10;

    /**
     * The maximum length of the members email address.
     */
    public final static int MAX_EMAIL_LEN = 30;
}


//~ Formatted by Jindent --- http://www.jindent.com
