/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionnaireCleValeur;

import Communication.Request;
import GestionnaireCleValeur.Stockage;
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
     *
     * @throws java.lang.Exception
     */
    @org.junit.Test
    public void testSet() throws Exception {
        // Fait avec testGetSet()
    }

    /**
     * Test of get method, of class Stockage.
     */
    @org.junit.Test
    public void testGet() {
        System.out.println("Get");
        Stockage instance = new Stockage();

        instance.set("aaa", "bbb");
        boolean isExRaised = false;
        try {
            instance.get("bbb");
        } catch (Request.IncorrectRequestException e) {
            isExRaised = true;
        } catch (Exception e) {
            fail("Mauvaise exception récupérée.");
            isExRaised = true;
        }
        assertTrue(isExRaised);
    }

    /**
     * Test of Get and Set methods, of class Stockage.
     *
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
    public void testIncr_String() {
        System.out.println("incr");
        String key = "t";
        Integer val = 0;
        Stockage instance = new Stockage();
        instance.set(key, val);
        int expResult = val + 1;
        int result = instance.incr(key);
        assertEquals(expResult, result);
        assertEquals(expResult, instance.get(key));
    }

    /**
     * Test of incr method, of class Stockage.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testIncr_String_int() throws Exception {
        System.out.println("incr");
        String key = "t";
        Integer val = 0;
        Integer inc = 5;
        Stockage instance = new Stockage();
        instance.set(key, val);
        int expResult = val + inc;
        int result = instance.incr(key, inc);
        assertEquals(expResult, result);
        assertEquals(expResult, instance.get(key));

        instance.set("str", "val");
        boolean isExRaised = false;
        try {
            instance.incr("str", 10);
        } catch (Request.IncorrectRequestException e) {
            isExRaised = true;
        } catch (Exception e) {
            fail("Mauvaise exception récupérée.");
            isExRaised = true;
        }
        assertTrue(isExRaised);
    }

    /**
     * Test of setlist method, of class Stockage.
     */
    @Test
    public void testSetlist() {
        System.out.println("setlist");
        String key = "list";
        List<Object> l = new ArrayList<>();
        l.add("a");
        l.add("test");
        l.add(15);
        l.add(true);
        Stockage instance = new Stockage();
        boolean expResult = true;
        boolean result = instance.setlist(key, l);
        assertEquals(expResult, result);
    }

    /**
     * Test of getlist method, of class Stockage.
     */
    @Test
    public void testGetlist() {
        System.out.println("getlist");
        String key = "list";
        List<Object> l = new ArrayList<>();
        l.add(9);
        l.add("test");
        l.add(15);
        l.add(false);
        Stockage instance = new Stockage();
        boolean ret = instance.setlist(key, l);
        if (ret) {
            List<Object> result = instance.getlist(key);
            for (Object result1 : result) {
                assertTrue(l.contains(result1));
                l.remove(result1);
            }
        }

        instance.set("notAList", "str");
        boolean isExRaised = false;
        try {
            instance.getlist("notAList");
        } catch (Request.IncorrectRequestException e) {
            isExRaised = true;
        } catch (Exception e) {
            fail("Mauvaise exception récupérée.");
            isExRaised = true;
        }
        assertTrue(isExRaised);
        
        try {
            instance.getlist("notEvenAKey");
        } catch (Request.IncorrectRequestException e) {
            isExRaised = true;
        } catch (Exception e) {
            fail("Mauvaise exception récupérée.");
            isExRaised = true;
        }
        assertTrue(isExRaised);
        

    }

    /**
     * Test of listadd method, of class Stockage.
     */
    @Test
    public void testListadd() {
        System.out.println("listadd");
        String key = "list";
        List<Object> l = new ArrayList<>();
        List<Object> add = new ArrayList<>();
        l.add(5);
        l.add("a");
        l.add(8);
        l.add(true);
        Stockage instance = new Stockage();
        boolean ret = instance.setlist(key, l);
        boolean result = instance.listadd(key, add);
        List<Object> newlist = instance.getlist(key);
        for (Object newlist1 : newlist) {
            assertTrue(newlist.contains(newlist1));
        }
    }

    /**
     * Test of listremove method, of class Stockage.
     */
    @Test
    public void testListremove() {
        System.out.println("listremove");
        String key = "list";
        List<Object> l = new ArrayList<>();
        List<Object> add = new ArrayList<>();
        l.add(5);
        l.add("a");
        l.add(8);
        l.add(true);
        Stockage instance = new Stockage();
        boolean ret = instance.setlist(key, l);
        boolean result = instance.listremove(key, 5);
        instance.listremove(key, "a");
        instance.listremove(key, true);
        instance.listremove(key, 8);
        List<Object> resultat = instance.getlist(key);
        assertTrue(resultat.isEmpty());
    }

}
