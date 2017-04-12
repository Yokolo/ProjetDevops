/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projetdevops.GestionnaireCleValeur;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marionsy
 */
public class StockageTest {
    
    public StockageTest() {
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
     * Test of set method, of class Stockage.
     * @throws java.lang.Exception
     */
    @org.junit.Test
    public void testSet() throws Exception {
        // Fait avec testGetSet()
    }

    /**
     * Test of get method, of class Stockage.
     * @throws java.lang.Exception
     */
    @org.junit.Test
    public void testGet() throws Exception {
        // Fait avec testGetSet()
    }

        /**
     * Test of Get and Set methods, of class Stockage.
     * @throws java.lang.Exception
     */
    @org.junit.Test
    public void testGetSet() throws Exception {
        System.out.println("Getter / Setter");
        Stockage instance = new Stockage();
        int i = 0;

        List<Object> objets = new ArrayList<>();
        objets.add("aaa");
        objets.add(10);
        objets.add(new Point(10, 20));
        
        for (Object obj : objets) {
            String cle = "obj " + i;
            instance.set(cle, obj);
            Object expResult = obj;
            Object result = instance.get(cle);
            assertEquals(expResult, result);
        }
    }
    
    /**
     * Test of incr method, of class Stockage.
     */
    @Test
    public void testIncr_String() throws Exception {
        System.out.println("incr");
        String key = "t";
        Integer val = 0;
        Stockage instance = new Stockage();
        instance.set(key, val);
        int expResult = val+1;
        int result = instance.incr(key);
        assertEquals(expResult, result);
        assertEquals(expResult, instance.get(key));
    }

    /**
     * Test of incr method, of class Stockage.
     */
    @Test
    public void testIncr_String_int() throws Exception {
        System.out.println("incr");
        String key = "t";
        Integer val = 0;
        Integer inc = 5;
        Stockage instance = new Stockage();
        instance.set(key, val);
        int expResult = val+inc;
        int result = instance.incr(key, inc);
        assertEquals(expResult, result);
        assertEquals(expResult, instance.get(key));
    }
    
}
