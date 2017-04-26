/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

import Communication.Request.Command;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mottetl
 */
public class RequestTest {
    
    public RequestTest() {
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
     * Test of parseRequest method, of class Request.
     */
    @Test
    public void testParseRequest() {
        System.out.println("parseRequest");
        Request instance = new Request("set a bonsoir");
        instance.parseRequest();
        Command c = instance.getCommand();
        assertEquals(null, c, c);
        List<Object> objets =instance.getArgs();
     
    }

    /**
     * Test of getRequest method, of class Request.
     */
    @Test
    public void testGetRequest() {
        System.out.println("getRequest");
        Request instance = new Request();
        String expResult = "";
        String result = instance.getRequest();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCommand method, of class Request.
     */
    @Test
    public void testGetCommand() {
        System.out.println("getCommand");
        Request instance = new Request();
        Request.Command expResult = null;
        Request.Command result = instance.getCommand();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArgs method, of class Request.
     */
    @Test
    public void testGetArgs() {
        System.out.println("getArgs");
        Request instance = new Request();
        List<Object> expResult = null;
        List<Object> result = instance.getArgs();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Request.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Request instance = new Request();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findType method, of class Request.
     */
    @Test
    public void testFindType() {
        System.out.println("findType");
        String strVal = "";
        Object expResult = null;
        Object result = Request.findType(strVal);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isCorrect method, of class Request.
     */
    @Test
    public void testIsCorrect() {
        System.out.println("isCorrect");
        Request instance = new Request();
        boolean expResult = false;
        boolean result = instance.isCorrect();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getError method, of class Request.
     */
    @Test
    public void testGetError() {
        System.out.println("getError");
        Request instance = new Request();
        Exception expResult = null;
        Exception result = instance.getError();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
