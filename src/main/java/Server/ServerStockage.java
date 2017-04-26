/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Communication.Registration;
import com.esotericsoftware.kryonet.Server;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marionsy
 */
class ServerStockage {

    public static void main(String[] args) {
        try {
            Server server = new Server();
            Registration.register(server);

            server.addListener(new ServerListener());

            server.bind(54555, 54777);
            server.start();
            
            // Pour pouvoir arreter le serveur
            Scanner s = new Scanner(System.in);
            System.out.println("Utilisez quit pour fermer le serveur");
            String c;
            do {
                c = s.nextLine().toLowerCase();
            } while (!"quit".equals(c));
            server.stop();

        } catch (IOException ex) {
            Logger.getLogger(ServerStockage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
