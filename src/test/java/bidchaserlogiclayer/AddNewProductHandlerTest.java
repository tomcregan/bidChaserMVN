package bidchaserlogiclayer;

import java.awt.Image;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tommy
 * 
 *
 */
public class AddNewProductHandlerTest {
    
    public AddNewProductHandlerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of validateTitle method, of class AddNewProductHandler.
     */
    @Test
    public void testValidateTitle() {
        System.out.println("validateTitle");
        String productTitle = "Dodge Viper";
        AddNewProductHandler instance = new AddNewProductHandler();
        int expResult = 708097;
        int result = instance.validateTitle(productTitle);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of validateDescription method, of class AddNewProductHandler.
     */
    @Test
    public void testValidateDescription() {
        System.out.println("validateDescription");
        String descriptionText = "";
        AddNewProductHandler instance = new AddNewProductHandler();
        int expResult = 708098;
        int result = instance.validateDescription(descriptionText);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of validatePrice method, of class AddNewProductHandler.
     */
    @Test
    public void testValidatePrice() {
        System.out.println("validatePrice");
        String startPrice = "";
        AddNewProductHandler instance = new AddNewProductHandler();
        int expResult = 708099;
        int result = instance.validatePrice(startPrice);
        assertEquals(expResult, result);
           
    }

    /**
     * Test of validateStartDate method, of class AddNewProductHandler.
     */
    @Test
    public void testValidateStartDate() {
        System.out.println("validateStartDate");
        Date startDate = null;
        AddNewProductHandler instance = new AddNewProductHandler();
        int expResult = 765190;
        int result = instance.validateStartDate(startDate);
        assertEquals(expResult, result);   
    }

    /**
     * Test of validateStartTime method, of class AddNewProductHandler.
     */
    @Test
    public void testValidateStartTime() {
        System.out.println("validateStartTime");
        String startTime = "01:00";
        AddNewProductHandler instance = new AddNewProductHandler();
        int expResult = 708101;
        int result = instance.validateStartTime(startTime);
        assertEquals(expResult, result);      
    }

    /**
     * Test of validateEndTime method, of class AddNewProductHandler.
     */
    @Test
    public void testValidateEndTime() {
        System.out.println("validateEndTime");
        String endTime = "";
        AddNewProductHandler instance = new AddNewProductHandler();
        int expResult = 708102;
        int result = instance.validateEndTime(endTime);
        assertEquals(expResult, result);    
    }

    /**
     * Test of validateImage method, of class AddNewProductHandler.
     */
    @Test
    public void testValidateImage() {
        System.out.println("validateImage");
        Image productImage = null;
        AddNewProductHandler instance = new AddNewProductHandler();
        int expResult = 708103;
        int result = instance.validateImage(productImage);
        assertEquals(expResult, result);   
    }
    
}
