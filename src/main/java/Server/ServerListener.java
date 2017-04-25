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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author badWarden
 */
class ServerListener extends Listener {

    public ServerListener() {
    }

    @Override
    public void received(Connection connection, Object object) {
        Stockage s = (Stockage) connection;

        if (object instanceof Request) {
            Request request = (Request) object;
            //System.out.println("Received " + object + " from " + connection);
            System.out.println(request);

            Object res = executeRequest(request, s);
            connection.sendTCP(res);
        } else if (object instanceof FrameworkMessage) {
        } else {
            connection.sendTCP(new IncorrectRequestTypeException());
        }
    }

    private static Object executeRequest(Request request, Stockage s) {
        Object res;
        List<Object> args = request.getArgs();
        switch (request.getCommand()) {
            case set:
                res = s.set((String) args.get(0), args.get(1));
                break;
            case get:
            case getlist:
                try {
                    res = s.get((String) args.get(0));
                } catch (Stockage.IncorrectKeyException ex) {
                    res = ex;
                }
                break;
            case incr:
                try {
                    res = s.incr((String) args.get(0), (int) args.get(1));
                } catch (Stockage.IncorrectKeyException | Stockage.NotIntegerException ex) {
                    res = ex;
                }
                break;
            case setlist:
                List<Object> l = new ArrayList<>();
                for (int i = 1; i < args.size(); i++) {
                    l.add(args.get(i));
                }
                res = s.setlist((String) args.get(0), l);
                break;
            case listadd:
                List<Object> la = new ArrayList<>();
                for (int i = 1; i < args.size(); i++) {
                    la.add(args.get(i));
                }
                try {
                    res = s.listadd((String) args.get(0), la);
                } catch (Stockage.IncorrectKeyException ex) {
                    res = ex;
                }
                break;
            case listremove:
                try {
                    res = s.listremove((String) args.get(0), args.get(1));
                } catch (Stockage.IncorrectKeyException ex) {
                    res = ex;
                }

                break;

            default:
                res = new Request.IncorrectRequestException("Le premier argument n'est pas une commande.");
        }
        return res;
    }
}
