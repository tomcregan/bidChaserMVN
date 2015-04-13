package bidchaserlogiclayer;

import bichaserdataaccesslayer.*;
import com.mongodb.gridfs.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.text.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import org.bson.*;

/**
 *
 * @author tommy
 */
public class AddNewProductHandler {
    private final int BUFFER_SIZE = 1024;
    private String    filepath;
    private String    filename;

    /**
     * Instantiate a new AddNewProductHandler object, reset filename + filepath
     * String objects to null.
     * <p>
     * Creates new AuctionTimer object to start the auction.
     */
    public AddNewProductHandler() {
        filepath = null;
        filename = null;
    }

    /**
     * Finds the image of the new product and returns it as an Icon allowing it
     * to be display on the calling UI display panel. The selected image is
     * resized to fit the calling UI display panel.
     * <p>
     * In order to resize the image the height and width of the calling UI
     * display panel needs to passed in as parameters.
     * <p>
     * @param height the height of the calling UI display panel.
     * @param width  the width of the calling UI display panel.
     * <p>
     * @return the image as a resized Icon
     * <p>
     * @see ImageIcon
     */
    public Icon selectProductImage(int height, int width) {

        // size of the read buffer
        JFileChooser jFile       = selectProductImage();
        File         fileDetails = getSelectedFileDetails(jFile);

        // ImageIcon to be returned
        ImageIcon icon = writeProductImageIcon(fileDetails, height, width);

        return icon;
    }

    /**
     * Returns the filepath of the selected file as a String.
     * <p>
     * @return the filepath
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * Sets the filepath of the selected file.
     * <p>
     * @param filepath the filepath to set
     */
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Returns the filename of the selected file as a String. Returns filename
     * using filepath e.g:
     * <p>
     * filepath = "C:\Documents\Images\SelectedImage.jpg"; filename =
     * "SelectedImage.jpg";
     * <p>
     * @return the filename
     * <p>
     * @see File
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Sets the filename of the selected image.
     * <p>
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     *
     * @param productTitle <p>
     * @return
     */
    public int validateTitle(String productTitle) {
        int retval = ValidationCodes.VALID_TITLE;

        if ((productTitle.length() < ValidationCodes.MIN_LENGTH)
                || (productTitle.length() > ValidationCodes.MAX_PROC_TITLE)) {
            retval = ValidationCodes.INVAL_TITLE_LEN;
        } else if (checkStringForNonAlphaNumericCharacters(productTitle)) {
            retval = ValidationCodes.INVAL_TITLE_CHARS;
        }

        return retval;
    }

    /**
     *
     * This is the product description that is read in from the Text Area. The
     * description cannot be empty (length() == 0) and the description can only
     * contain a maximum of 140 characters (length() < 140).
     * <p>
     * @param descriptionText the product decscritpion text.
     * <p>
     * @return the current value of the description validation.
     */
    public int validateDescription(String descriptionText) {
        int retval = ValidationCodes.VALID_DESCRIPTION;

        if ((descriptionText.length() <= ValidationCodes.MIN_LENGTH)
                || (descriptionText.length() > ValidationCodes.MAX_DESCR_LEN)) {
            retval = ValidationCodes.INVAL_DESCR_LEN;
        }

        return retval;
    }

    /**
     *
     * @param startPrice <p>
     * @return
     */
    public int validatePrice(String startPrice) {
        int retval = ValidationCodes.VALID_PRICE;

        if ((startPrice.length() < ValidationCodes.MIN_LENGTH)
                || (startPrice.length() > ValidationCodes.MAX_PRICE_LEN)) {
            retval = ValidationCodes.INVAL_PRICE_LEN;
        } else if (!checkStringForNonNumericCharacters(startPrice)) {
            retval = ValidationCodes.INVAL_PRICE_CHARS;
        }

        return retval;
    }

    /**
     *
     * @param startDate
     * <p>
     * @return
     * <p>
     * @see SimpleDateFormat
     */
    public int validateStartDate(Date startDate) {
        int retval = ValidationCodes.VALID_STARTDATE;

        if (startDate == null) {
            retval = ValidationCodes.INVAL_STDATE_LEN;
        } else {
            try {
                Date             dNow = new Date();
                SimpleDateFormat ft   = new SimpleDateFormat("dd/MM/yyyy");

                System.out.println("Start Date: \t\t" + ft.format(startDate));
                System.out.println("Current Date: \t\t" + ft.format(dNow));

                Date ftStartDate = ft.parse((ft.format(startDate)));
                Date ftDateNow   = ft.parse((ft.format(dNow)));

                if (ftStartDate.before(ftDateNow)) {
                    retval = ValidationCodes.INVAL_STDATE_TIME;
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }

        return retval;
    }

    /**
     *
     * @param startTime <p>
     * @return
     */
    public int validateStartTime(String startTime) {
        int retval = ValidationCodes.VALID_STARTTIME;

        if (startTime == null) {
            retval = ValidationCodes.INVAL_STTIME_LEN;
        } else {
            if ((startTime.length() < ValidationCodes.MIN_LENGTH)
                    || (startTime.length() > ValidationCodes.MAX_STTIME_LEN)) {

                // invalid format
                retval = ValidationCodes.INVAL_STTIME_TYPE;
            }
        }

        return retval;
    }

    /**
     *
     * @param endTime <p>
     * @return
     */
    public int validateEndTime(String endTime) {
        int retval = ValidationCodes.VALID_ENDTIME;

        if (endTime == null) {
            retval = ValidationCodes.INVAL_ETIME_LEN;
        } else {
            if ((endTime.length() < ValidationCodes.MIN_LENGTH) || (endTime.length() > ValidationCodes.MAX_ETIME_LEN)) {

                // invalid format
                retval = ValidationCodes.INVAL_ETIME_TYPE;
            }
        }

        return retval;
    }

    /**
     *
     * @param productImage <p>
     * @return
     */
    public int validateImage(Image productImage) {
        int retval = ValidationCodes.VALID_IMAGE;

        // TOD0: create checksum value for image and compare with current checksum
        return retval;
    }

    /**
     *
     * @param productTitle
     * @param desriptionText
     * @param startPrice
     * @param startDate
     * @param startTime
     * @param endTime <p>
     * @return <p>
     * @throws Exception
     */
    public String addNewProduct(String productTitle, String desriptionText, String startPrice, Date startDate,
                                String startTime, String endTime)
            throws Exception {
        GridFSInputFile gridFSImage = covertProductImageToGridFSImage();
        Document        newProduct  = new Document("productTitle", productTitle).append("desriptionText",
                                          desriptionText).append("startPrice", startPrice).append("startDate",
                                              startDate).append("startTime", startTime).append("endTime",
                                                  endTime).append("productImage", gridFSImage);

        MongoDBInstance.getInstance().getMongoProductsCollection().insertOne(newProduct);

        return productTitle + ":\n has been added to: \n" + MongoDBInstance.getInstance().getMongoDatabaseName() + "/"
               + MongoDBInstance.getInstance().getMongoProductsCollectionName();
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type, int height, int width) {
        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D    g            = resizedImage.createGraphics();

        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();

        return resizedImage;
    }

    private static byte[] loadImage(String filePath) throws Exception {
        File   file   = new File(filePath);
        int    size   = (int) file.length();
        byte[] buffer = new byte[size];

        try (FileInputStream in = new FileInputStream(file)) {
            in.read(buffer);
            in.close();
        }

        return buffer;
    }

    /*
     * This method is used to check if the string contains any non-valid
     * characters. Non-valid character is any character that is not a letter.
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
     *
     * @param fileDetails
     * @param height
     * @param width
     * @param BUFFER_SIZE
     * @return
     * @throws HeadlessException
     */
    private ImageIcon writeProductImageIcon(File fileDetails, int height, int width) throws HeadlessException {
        ImageIcon icon = null;

        try {
            BufferedImage         thumbnail = getThumbnailImage(fileDetails, height, width);
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();

            ImageIO.write(thumbnail, "jpeg", outStream);

            InputStream inStream = new ByteArrayInputStream(outStream.toByteArray());
            byte[]      buffer   = new byte[BUFFER_SIZE];

            try {
                for (int readNum; (readNum = inStream.read(buffer)) != -1; ) {
                    outStream.write(buffer, 0, readNum);
                    System.out.println("Reading: " + readNum + " bytes ->");
                }

                icon = new ImageIcon(thumbnail);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "Images only can be uploaded");
            ioe.printStackTrace();
        }

        return icon;
    }

    /*
     *
     * @param fileDetails
     * @param height
     * @param width
     * @return
     * @throws IOException
     */
    private BufferedImage getThumbnailImage(File fileDetails, int height, int width) throws IOException {

        // get the and resize the selected file
        BufferedImage bufferedImage = ImageIO.read(fileDetails);
        int           type          = bufferedImage.getType();
        BufferedImage thumbnail     = resizeImage(bufferedImage, type, height, width);

        return thumbnail;
    }

    /*
     *
     * @param jFile
     * @return
     */
    private File getSelectedFileDetails(JFileChooser jFile) {

        // get the details
        File fileDetails = jFile.getSelectedFile();

        setFilepath(fileDetails.getAbsolutePath());
        setFilename(fileDetails.getName());

        return fileDetails;
    }

    /*
     *
     * @return
     * @throws HeadlessException
     */
    private JFileChooser selectProductImage() throws HeadlessException {

        // select the image file using JFileChooser
        JFileChooser jFile = new JFileChooser();

        jFile.showOpenDialog(null);

        return jFile;
    }

    /*
     *
     * @return
     * @throws Exception
     */
    private GridFSInputFile covertProductImageToGridFSImage() throws Exception {

        // convert image to girdfs
        byte[] imageBytes = loadImage(getFilepath());

        // Create GridFS object
        GridFS fs = new GridFS(MongoDBInstance.getInstance().getMongoDB());

        // Save image into database
        GridFSInputFile gridFSImage = fs.createFile(imageBytes);

        gridFSImage.setFilename(getFilename());

        return gridFSImage;
    }

    /*
     *
     * @param testString
     * @return
     */
    private boolean checkStringForNonNumericCharacters(String testString) {
        boolean isValid = true;
        char    ch;

        for (int i = 0; i < testString.length(); i++) {
            ch = testString.charAt(i);

            if (!Character.isDigit(ch)) {
                isValid = false;
            }
        }

        return isValid;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
