package bidchaserlogiclayer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tommy
 */
public class RegisterHandlerTest {
    
    public RegisterHandlerTest() {
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
     * Test of validateFirstname method, of class RegisterHandler.
     */
    @Test
    public void testValidateFirstname() {
        System.out.println("validateFirstname");
        String fname = "Tommy";
        RegisterHandler instance = new RegisterHandler();
        int expResult = 708113;
        int result = instance.validateFirstname(fname);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of validateLastname method, of class RegisterHandler.
     */
    @Test
    public void testValidateLastname() {
        System.out.println("validateLastname");
        String lname = "Cregan";
        RegisterHandler instance = new RegisterHandler();
        int expResult = 708114;
        int result = instance.validateLastname(lname);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of validateUsername method, of class RegisterHandler.
     */
    @Test
    public void testValidateUsername() {
        System.out.println("validateUsername");
        String uname = "Silken";
        RegisterHandler instance = new RegisterHandler();
        int expResult = 708115;
        int result = instance.validateUsername(uname);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of validatePassword method, of class RegisterHandler.
     */
    @Test
    public void testValidatePassword() {
        System.out.println("validatePassword");
        String pword = "mypass";
        RegisterHandler instance = new RegisterHandler();
        int expResult = 765220;
        int result = instance.validatePassword(pword);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of validateDOB method, of class RegisterHandler.
     */
    @Test
    public void testValidateDOB() {
        System.out.println("validateDOB");
        String dob = "12/05/1981";
        RegisterHandler instance = new RegisterHandler();
        int expResult = 708119;
        int result = instance.validateDOB(dob);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of validatePhoneNumber method, of class RegisterHandler.
     */
    @Test
    public void testValidatePhoneNumber() {
        System.out.println("validatePhoneNumber");
        String pnum = "0868946165";
        RegisterHandler instance = new RegisterHandler();
        int expResult = 708117;
        int result = instance.validatePhoneNumber(pnum);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of validateEmail method, of class RegisterHandler.
     */
    @Test
    public void testValidateEmail() {
        System.out.println("validateEmail");
        String email = "tommycregan@live.com";
        RegisterHandler instance = new RegisterHandler();
        int expResult = 708118;
        int result = instance.validateEmail(email);
        assertEquals(expResult, result);
        
    }

    
}
