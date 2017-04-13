/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import java.io.IOException;
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
            
            server.start();
            server.bind(54555, 54777);
            
            server.addListener(new ServerListener());

        } catch (IOException ex) {
            Logger.getLogger(ServerStockage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
