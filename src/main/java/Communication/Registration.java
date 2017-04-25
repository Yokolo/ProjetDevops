/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

/**
 *
 * @author badWarden
 */
public class Registration {
    
    public static void register(EndPoint ep) {
        Kryo kryo = ep.getKryo();
        kryo.register(Request.class);
        kryo.register(Request.IncorrectRequestException.class);
        kryo.register(Request.IncorrectRequestTypeException.class);
        kryo.register(Request.Command.class);
        kryo.register(Response.class);
        kryo.register(java.util.ArrayList.class);

        // Rajouter ici les types d'objets que l'on peut transmettre entre client et serveur qui ne sont pas des types de base...
    }
}
