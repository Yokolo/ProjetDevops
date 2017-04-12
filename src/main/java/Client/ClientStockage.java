/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.io.IOException;
import java.net.InetAddress;
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
            client.start();
            client.connect(5000, InetAddress.getLocalHost(), 54555, 54777);

            String request = "Here is the request";
            client.sendTCP(request);

            client.addListener(new Listener() {
                @Override
                public void received(Connection connection, Object object) {
                    if (object instanceof String) {
                        String response = (String) object;
                        System.out.println(response);
                    }
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(ClientStockage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
