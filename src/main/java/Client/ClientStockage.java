/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Communication.Registration;
import Communication.Request;
import Communication.Response;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.FrameworkMessage;
import com.esotericsoftware.kryonet.Listener;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marionsy
 */
public class ClientStockage {

    public static void main(String[] args) {
        try {
            Client client = new Client();
            Registration.register(client);
            client.start();
            client.connect(5000, InetAddress.getLocalHost(), 54555, 54777);
            
            client.addListener(new Listener() {
                @Override
                public void received(Connection connection, Object object) {
                    if (object instanceof Response) {
                        Response r = (Response) object;
                        if (r.isCorrect()) {
                            System.out.println("> " + r.getResponseInfo());
                        } 
                        else {
                            System.out.println("> Erreur : " + r.getResponseInfo());
                        }
                    }
                }
            });
            //while(true);

            Scanner s = new Scanner(System.in);
            boolean b = true;
            do {
                String request = s.nextLine();
                System.out.println("Request : " + request);
                switch (request.toLowerCase()) {
                    // Commandes client-side
                    case "quit":
                    case "exit":
                        b = false;
                        break;
                    case "help":
                        printHelp();
                        break;
                    // Commandes server-side
                    default:
                        try {
                            Request r = new Request(request);
                            client.sendTCP(r);
                        } catch (Exception re) {
                            System.err.println(re);
                        }
                        break;
                }
            } while (b);

        } catch (IOException ex) {
            Logger.getLogger(ClientStockage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void printHelp() {
        System.out.println(" - - - Help - - - ");
        System.out.println("help - affiche cette aide");
        System.out.println("quit - ferme l'application");
        System.out.println("exit - ferme l'application");
        for (Request.Command c : Request.Command.values()) {
            System.out.println(c.getHelp());
        }
        System.out.println(" - - - Help - - - ");
    }
}
