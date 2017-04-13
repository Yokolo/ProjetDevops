/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author badWarden
 */
public class Request {

    /**
     * L'ensemble des commandes utilisables par un client
     */
    public enum Command {
        get ("get key - récupère la valeur associée à la clé key si elle existe"), 
        set ("set key value - insére la valeur value pour la clé key"), 
        incr ("incr key [i=1] - Incrémente la valeur associée à la clé key de la valeur i s'il s'agit d'un entier");
        
        private final String helpText;
        Command(String s) {
            helpText = s;
        }
        
        public String getHelp() {
            return helpText;
        }
    };

    private final String r;
    private List<String> parsedRequest;

    public Request(String request) {
        r = request;
        if ("".equals(r)) {
        } else {
            String[] splittedRequest = r.split(" ");
            splittedRequest[0] = splittedRequest[0].toLowerCase();
            parsedRequest = Arrays.asList(splittedRequest);
        }
    }

    /**
     * @return the request
     */
    public String getRequest() {
        return r;
    }

    /**
     * @return the parsedRequest
     */
    public List<String> getParsedRequest() {
        return parsedRequest;
    }

    /**
     * getCommand()
     *
     * @return la commande associée à cette requête
     */
    public Command getCommand() {
        Command res;
        if (getParsedRequest() == null) {
            throw new NullPointerException();
        } else if (getParsedRequest().isEmpty()) {
            throw new IncorrectRequestException();
        } else {
            switch (getParsedRequest().get(0)) {
                case "set":
                    res = Command.set;
                    break;
                case "get":
                    res = Command.get;
                    break;
                case "incr":
                    res = Command.incr;
                default:
                    throw new IncorrectRequestException();
            }
        }
        return res;
    }

    /**
     * @return la clé associée à la commande si la commande est un set
     */
    public String getSetKey() {
        if (getCommand() == Command.set) {
            if (getParsedRequest().size() == 3) {
                return getParsedRequest().get(1);
            }
        }
        throw new IncorrectRequestException();
    }

    /**
     * @return La valeur associée à la commane si la commande est un set
     */
    public Object getSetVal() {
        if (getCommand() == Command.set) {
            if (getParsedRequest().size() == 3) {
                String strVal = getParsedRequest().get(2);
                try {
                    return Integer.parseInt(strVal);
                } catch (NumberFormatException notIntException) {
                    try {
                        return Double.parseDouble(strVal);
                    } catch (NumberFormatException notDoubleExpection) {
                        return strVal;
                    }
                }
            }
        }
        throw new IncorrectRequestException();
    }

    /**
     * @return La clé associée à la commande si la commande est un get
     */
    public String getGetKey() {
        if (getCommand() == Command.get) {
            if (getParsedRequest().size() == 2) {
                return getParsedRequest().get(1);
            }
        }
        throw new IncorrectRequestException();
    }
    
    /**
     * @return La clé associée à la commande si la commande est un incr
     */
    public String getIncrKey() {
        if (getCommand() == Command.incr) {
            if (getParsedRequest().size() == 2 || getParsedRequest().size() == 3) {
                return getParsedRequest().get(1);
            }
        }
        throw new IncorrectRequestException();
    }
    
    /**
     * @return Retourne la valeur dont il faut incrémenter la clé si la commande est un incr
     */
    public int getIncrVal() {
        if (getCommand() == Command.incr) {
            if (getParsedRequest().size() == 2) {
                return 1;
            } else if (getParsedRequest().size() == 3) {
                String strVal = getParsedRequest().get(2);
                try {
                    return Integer.parseInt(strVal);
                } catch (NumberFormatException notIntException) {
                    throw new IncorrectRequestException();
                }
            }
        }
        throw new IncorrectRequestException();
    }

    @Override
    public String toString() {
        return r;
    }

    public static class IncorrectRequestException extends RuntimeException {

        public IncorrectRequestException() {
        }
    }

    public static class IncorrectRequestTypeException extends RuntimeException {

        public IncorrectRequestTypeException() {
        }
    }

}
