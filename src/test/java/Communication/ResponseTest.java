/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

import static Communication.Request.findType;
import GestionnaireCleValeur.Stockage;
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
public class ResponseTest {

    public ResponseTest() {
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
     * Test of isCorrect method, of class Response.
     */
    @Test
    public void testIsCorrect() {
        System.out.println("isCorrect");
        Request req = new Request("set i 10");
        req.parseRequest();

        Stockage stock = new Stockage();
        Response instance = new Response(req, stock.set((String) req.getArgs().get(0), req.getArgs().get(1)));
        boolean expResult = true;
        boolean result = instance.isCorrect();
        assertEquals(expResult, result);
        req = new Request("a i 10");
        req.parseRequest();
        instance = new Response(req, req.getError());
        result = instance.isCorrect();
        expResult = false;
        assertEquals(expResult, result);
    }

    /**
     * Test of getError method, of class Response.
     */
    @Test
    public void testGetError() {
        System.out.println("getError");
        Request req = new Request("set i 10 50");
        req.parseRequest();
        Stockage stock = new Stockage();
        Response res = new Response(req, req.getError());
        assertEquals(res.getError(), req.getError());
        req = new Request("set i 10 ");
        req.parseRequest();
        Response instance = new Response(req, stock.set((String) req.getArgs().get(0), req.getArgs().get(1)));
        assertEquals(instance.getError(), null);
    }

    /**
     * Test of getResponseObject method, of class Response.
     */
    @Test
    public void testGetResponseObject() {
        System.out.println("getResponseObject");
        Request req = new Request("set i 10 ");
        Stockage stock = new Stockage();
        req.parseRequest();
        Response instance = new Response(req, stock.set((String) req.getArgs().get(0), req.getArgs().get(1)));
        Object o = instance.getResponseObject();
        assertEquals(o,true);
        req = new Request("set i 10 10");
        req.parseRequest();
        instance = new Response(req,req.getError());
        o = instance.getResponseObject();
        assertEquals(o, null);



    }

    /**
     * Test of getInitialRequest method, of class Response.
     */
    @Test
    public void testGetInitialRequest() {
        System.out.println("getInitialRequest");
        Request req = new Request("set i 10 ");
        Stockage stock = new Stockage();
        req.parseRequest();
        Response instance = new Response(req, stock.set((String) req.getArgs().get(0), req.getArgs().get(1)));
        Request r = instance.getInitialRequest();
        assertEquals(r, req);
    }

    /**
     * Test of getResponseInfo method, of class Response.
     */
    @Test
    public void testGetResponseInfo() {
        System.out.println("getResponseInfo");
        Request req = new Request("set i 10 ");
        Stockage stock = new Stockage();
        req.parseRequest();
        Response instance = new Response(req, stock.set((String) req.getArgs().get(0), req.getArgs().get(1)));
        Object r = instance.getResponseInfo();
        assertEquals(r,r.toString());
        req = new Request("set i 10 10");
        req.parseRequest();
        instance = new Response(req,req.getError());
        r=instance.getResponseInfo();
        assertEquals(r,req.getError().toString());



    }

}
