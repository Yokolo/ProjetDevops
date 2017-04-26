package Server;

import Communication.Request;
import Communication.Response;
import GestionnaireCleValeur.Stockage;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author badWarden
 */
class ServerListener extends Listener {

    HashMap<Connection, Stockage> dataClients;

    public ServerListener() {
        dataClients = new HashMap<>();
    }

    @Override
    public void connected(Connection connection) {
        super.connected(connection);

        dataClients.put(connection, new Stockage());
    }

    @Override
    public void received(Connection connection, Object object) {
        if (object instanceof Request) {
            Request request = (Request) object;
            Response res;
            request.parseRequest();
            if (request.isCorrect()) {
                res = executeRequest(request, dataClients.get(connection));
            } else {
                res = new Response(request, request.getError());
            }
            connection.sendTCP(res);
        }
    }

    @Override
    public void disconnected(Connection connection) {
        super.disconnected(connection);
        
        dataClients.remove(connection);
    }

    private static Response executeRequest(Request request, Stockage s) {
        Object res;
        List<Object> args = request.getArgs();
        switch (request.getCommand()) {
            case set:
                res = s.set((String) args.get(0), args.get(1));
                break;

            case get:
                try {
                    res = s.get((String) args.get(0));
                } catch (Request.IncorrectRequestException ex) {
                    res = ex;
                }
                break;

            case getlist:
                try {
                    res = s.getlist((String) args.get(0));
                } catch (Request.IncorrectRequestException ex) {
                    res = ex;
                }
                break;

            case incr:
                try {
                    res = s.incr((String) args.get(0), (int) args.get(1));
                } catch (Request.IncorrectRequestException ex) {
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
                } catch (Request.IncorrectRequestException ex) {
                    res = ex;
                }
                break;

            case listremove:
                try {
                    res = s.listremove((String) args.get(0), args.get(1));
                } catch (Request.IncorrectRequestException ex) {
                    res = ex;
                }
                break;

            case getelem:
                try {
                    res = s.getelem((String) args.get(0), (int) args.get(1));
                } catch (Request.IncorrectRequestException ex) {
                    res = ex;
                }
                break;
            default:
                res = new Request.IncorrectRequestException("Le premier argument n'est pas une commande.");
        }
        Response r = new Response(request, res);
        return r;
    }
}
