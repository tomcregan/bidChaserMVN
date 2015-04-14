/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of validateLastname method, of class RegisterHandler.
     */
    @Test
    public void testValidateLastname() {
        System.out.println("validateLastname");
        String lname = "Cregan12343";
        RegisterHandler instance = new RegisterHandler();
        int expResult = 708114;
        int result = instance.validateLastname(lname);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
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
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
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
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
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
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
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
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
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
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of registerNewMember method, of class RegisterHandler.
     */
//    @Test
//    public void testRegisterNewMember() {
//        System.out.println("registerNewMember");
//        String fname = "";
//        String lname = "";
//        String uname = "";
//        String pword = "";
//        String dob = "";
//        String pnum = "";
//        String email = "";
//        RegisterHandler instance = new RegisterHandler();
//        String expResult = "";
//        String result = instance.registerNewMember(fname, lname, uname, pword, dob, pnum, email);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setMemberUsername method, of class RegisterHandler.
//     */
//    @Test
//    public void testSetMemberUsername() {
//        System.out.println("setMemberUsername");
//        String uname = "";
//        RegisterHandler instance = new RegisterHandler();
//        instance.setMemberUsername(uname);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
