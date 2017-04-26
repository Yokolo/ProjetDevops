/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

import Communication.Request.Command;
import static Communication.Request.findType;
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

        testCommand();

        testArgs();

    }

    private List<String> getOneRequestOfEach() {
        List<String> requests = new ArrayList<>();
        requests.add("set a b");
        requests.add("get a");
        requests.add("incr a");
        requests.add("setlist a aa c");
        requests.add("getlist a");
        requests.add("listadd a b v");
        requests.add("listremove a c");
        return requests;
    }

    private void testCommand() {
        List<String> requests = getOneRequestOfEach();

        Request instance;
        for (String s : requests) {
            instance = new Request(s);
            instance.parseRequest();

            String[] splittedRequest = s.split(" ");
            Command expRes = null;
            switch (splittedRequest[0].toLowerCase()) {
                case "set":
                    expRes = Command.set;
                    break;
                case "get":
                    expRes = Command.get;
                    break;
                case "incr":
                    expRes = Command.incr;
                    break;
                case "getlist":
                    expRes = Command.getlist;
                    break;
                case "setlist":
                    expRes = Command.setlist;
                    break;
                case "listadd":
                    expRes = Command.listadd;
                    break;
                case "listremove":
                    expRes = Command.listremove;
                    break;
                default:
                    fail("Mauvais argument");
            }
            assertEquals(expRes, instance.getCommand());
        }

        // TODO : faire une requete fausse
    }

    private void testArgs() {
        List<String> requests = getOneRequestOfEach();

        for (String s : requests) {

            Request r = new Request(s);
            r.parseRequest();
            
            String[] splittedRequest = s.split(" ");
            Command command;

            // Récuperation de la commande
            switch (splittedRequest[0].toLowerCase()) {
                case "set":
                    command = Command.set;
                    break;
                case "get":
                    command = Command.get;
                    break;
                case "incr":
                    command = Command.incr;
                    break;
                case "getlist":
                    command = Command.getlist;
                    break;
                case "setlist":
                    command = Command.setlist;
                    break;
                case "listadd":
                    command = Command.listadd;
                    break;
                case "listremove":
                    command = Command.listremove;
                    break;
                default:
                    throw new Request.IncorrectRequestException("Le premier argument n'est pas une commande.");
            }

            ArrayList<Object> args = new ArrayList<>();
            try {
                switch (command) {
                    case set:
                        if (splittedRequest.length == 3) {
                            args.add(splittedRequest[1]);
                            args.add(findType(splittedRequest[2]));
                        } else {
                            throw new Request.IncorrectRequestException("La commande SET attend 2 arguments.");
                        }
                        break;

                    case get:
                        if (splittedRequest.length == 2) {
                            args.add(splittedRequest[1]);
                        } else {
                            throw new Request.IncorrectRequestException("La commande GET attend 1 argument.");
                        }
                        break;

                    case incr:
                        switch (splittedRequest.length) {
                            case 2:
                                args.add(splittedRequest[1]);
                                args.add(1);
                                break;
                            case 3:
                                try {
                                    args.add(splittedRequest[1]);
                                    args.add(Integer.parseInt(splittedRequest[2]));
                                } catch (Exception e) {
                                    throw new Request.IncorrectRequestException("Le second argment de la commande INCR doit être un entier.");
                                }
                                break;
                            default:
                                throw new Request.IncorrectRequestException("La commande INCR attend 1 ou 2 argument(s).");
                        }
                        break;
                    case getlist:
                        if (splittedRequest.length == 2) {
                            args.add(splittedRequest[1]);
                        } else {
                            throw new Request.IncorrectRequestException("La commande GETLIST attend 1 argument.");
                        }
                        break;
                    case setlist:
                        if (splittedRequest.length >= 3) {
                            args.add(splittedRequest[1]);
                            for (int i = 2; i < splittedRequest.length; i++) {
                                args.add(findType(splittedRequest[i]));
                            }
                        } else {
                            throw new Request.IncorrectRequestException("La commande SETLIST attend au moins 3 arguments.");
                        }
                        break;
                    case listadd:
                        if (splittedRequest.length >= 3) {
                            args.add(splittedRequest[1]);
                            for (int i = 2; i < splittedRequest.length; i++) {
                                args.add(findType(splittedRequest[i]));
                            }
                        } else {
                            throw new Request.IncorrectRequestException("La commande LISTADD attend au moins 3 arguments.");
                        }
                        break;
                    case listremove:
                        if (splittedRequest.length == 3) {
                            args.add(splittedRequest[1]);
                            args.add(findType(splittedRequest[2]));
                        } else {
                            throw new Request.IncorrectRequestException("La commande LISTREMOVE attend 3 arguments.");
                        }
                        break;

                    default:
                        throw new Request.IncorrectRequestException("Le premier argument n'est pas une commande.");
                }
                
                r.getArgs().stream().map((i) -> {
                    assertTrue(args.contains(i));
                    return i;
                }).forEach((i) -> {
                    args.remove(i);
                });
                
            } catch (Exception e) {
                fail("Pas normal d'arriver ici");
            }
        }

    }

    /**
     * Test of toString method, of class Request.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String request = "get a";
        Request instance = new Request(request);
        String expResult = request;
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of findType method, of class Request.
     */
    @Test
    public void testFindType() {
        System.out.println("findType");

        String strVal = "str";
        String expResult1 = "str";
        Object result = Request.findType(strVal);
        assertEquals(expResult1, result);

        strVal = "90";
        Integer expResult2 = 90;
        result = Request.findType(strVal);
        assertEquals(expResult2, result);

        strVal = "70.5";
        Double expResult3 = 70.5;
        result = Request.findType(strVal);
        assertEquals(expResult3, result);

        strVal = "true";
        Boolean expResult4 = true;
        result = Request.findType(strVal);
        assertEquals(expResult4, result);
    }

    /**
     * Test of isCorrect method, of class Request.
     */
    @Test
    public void testIsCorrect() {
        System.out.println("isCorrect");

        Request instance = new Request("ahgag hahada");
        instance.parseRequest();
        boolean expResult = false;
        boolean result = instance.isCorrect();
        assertEquals(expResult, result);

        instance = new Request("set a b");
        instance.parseRequest();
        expResult = true;
        result = instance.isCorrect();
        assertEquals(expResult, result);
    }

    /**
     * Test of getError method, of class Request.
     */
    @Test
    public void testGetError() {
        System.out.println("getError");
        Request instance = new Request("set a b");
        instance.parseRequest();
        Exception result = instance.getError();
        assertNull(result);

        instance = new Request("not a command");
        instance.parseRequest();
        Exception expResult = new Request.IncorrectRequestException("Le premier argument n'est pas une commande.");
        result = instance.getError();
        assertEquals(expResult, result);
    }

}
