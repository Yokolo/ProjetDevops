/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import GestionnaireCleValeur.Stockage;
import Server.Request.IncorrectRequestTypeException;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.FrameworkMessage;
import com.esotericsoftware.kryonet.Listener;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author badWarden
 */
class ServerListener extends Listener {

    ConcurrentHashMap<Connection, Stockage> clientsData;

    public ServerListener() {
        clientsData = new ConcurrentHashMap<>();
    }
    
    @Override
    public void connected(Connection connection) {
        super.connected(connection);
        clientsData.put(connection, new Stockage());
    }

    @Override
    public void received(Connection connection, Object object) {
        System.out.println("Received " + object + " from " + connection);
        
        if (object instanceof Request) {
            Request request = (Request) object;
            System.out.println(request);

            Stockage clientData = clientsData.get(connection);
            Object res = executeRequest(request, clientData);
            connection.sendTCP(res);
        } else if (object instanceof FrameworkMessage) {
        } else {
            connection.sendTCP(new IncorrectRequestTypeException());
        }
    }
    
    @Override
    public void disconnected(Connection connection) {
        super.disconnected(connection);
        clientsData.remove(connection);
    }

    private static Object executeRequest(Request request, Stockage s) {
        Object res;
        switch (request.getCommand()) {
            case set:
                res = s.set(request.getSetKey(), request.getSetVal());
                break;
            case get:
                try {
                    res = s.get(request.getGetKey());
                } catch (Stockage.IncorrectKeyException ex) {
                    res = ex;
                }
                break;
            case incr:
                try {
                    res = s.incr(request.getIncrKey(), request.getIncrVal());
                } catch (Stockage.IncorrectKeyException | Stockage.NotIntegerException ex) {
                    res = ex;
                }
                break;
            default:
                res = new Request.IncorrectRequestException();
        }
        return res;
    }
}
