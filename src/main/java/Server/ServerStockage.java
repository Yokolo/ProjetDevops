/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marionsy
 */
public class ServerStockage {

    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.start();
            server.bind(54555, 54777);

            server.addListener(new Listener() {
                @Override
                public void received(Connection connection, Object object) {
                    if (object instanceof String) {
                        String request = (String) object;
                        System.out.println(request);

                        String response = "Thanks";
                        connection.sendTCP(response);
                    }
                }
            });

        } catch (IOException ex) {
            Logger.getLogger(ServerStockage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
