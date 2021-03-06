/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author badWarden
 */
public class Request {

    /**
     * L'ensemble des commandes utilisables par un client
     */
    public enum Command {
        set("set key value - insére la valeur value pour la clé key"),
        get("get key - récupère la valeur associée à la clé key si elle existe"),
        incr("incr key [i=1] - Incrémente la valeur associée à la clé key de la valeur i s'il s'agit d'un entier"),
        setlist("setlist key v1 [v2,...] - crée une liste associée à la clé key et contenant les valeurs v1 v2 ... \n "
                + "\t\t" + "types de valeurs acceptés : boolean, char, string, integer, double"),
        getlist("getlist key - récupère la liste associée à la clé key si elle existe"),
        getelem("getelem key idx - récupère l'élément à l'index idx de la liste associée à la clé key"),
        listadd("listadd key v1 [v2,...] - ajoute dans la liste associée à la clé key les valeurs v1 v2.. "),
        listremove("listremove key v1 - supprime de la liste associée à la clé key la valeur v1");

        private final String helpText;

        Command(String s) {
            helpText = s;
        }

        public String getHelp() {
            return helpText;
        }
    };

    private String r;
    private String error;
    private Command command;
    private List<Object> args;

    public Request() {
    }

    public Request(String request) {
        error = null;
        r = request;
    }

    public void parseRequest() {
        String request = r;
        if ("".equals(request)) {
            error = "Requête vide.";
        }
        try {
            String[] splittedRequest = request.split(" ");

            // Récuperation de la commande
            switch (splittedRequest[0].toLowerCase()) {
                case "set":
                    command = Command.set;
                    break;
                case "get":
                    command = Command.get;
                    break;
                case "incr":
                    command = Command.incr;
                    break;
                case "getlist":
                    command = Command.getlist;
                    break;
                case "setlist":
                    command = Command.setlist;
                    break;
                case "listadd":
                    command = Command.listadd;
                    break;
                case "listremove":
                    command = Command.listremove;
                    break;
                case "getelem":
                    command = Command.getelem;
                    break;
                default:
                    throw new IncorrectRequestException("Le premier argument n'est pas une commande.");
            }

            // Recuperation des arguments
            args = new ArrayList<>();
            //System.out.println(getCommand());
            switch (getCommand()) {
                case set:
                    if (splittedRequest.length == 3) {
                        args.add(splittedRequest[1]);
                        args.add(findType(splittedRequest[2]));
                    } else {
                        throw new IncorrectRequestException("La commande SET attend 2 arguments.");
                    }
                    break;

                case get:
                    if (splittedRequest.length == 2) {
                        args.add(splittedRequest[1]);
                    } else {
                        throw new IncorrectRequestException("La commande GET attend 1 argument.");
                    }
                    break;

                case incr:
                    switch (splittedRequest.length) {
                        case 2:
                            args.add(splittedRequest[1]);
                            args.add(1);
                            break;
                        case 3:
                            try {
                                args.add(splittedRequest[1]);
                                args.add(Integer.parseInt(splittedRequest[2]));
                            } catch (Exception e) {
                                throw new IncorrectRequestException("Le second argument de la commande INCR doit être un entier.");
                            }
                            break;
                        default:
                            throw new IncorrectRequestException("La commande INCR attend 1 ou 2 argument(s).");
                    }
                    break;
                case getlist:
                    if (splittedRequest.length == 2) {
                        args.add(splittedRequest[1]);
                    } else {
                        throw new IncorrectRequestException("La commande GETLIST attend 1 argument.");
                    }
                    break;
                case setlist:
                    if (splittedRequest.length >= 3) {
                        args.add(splittedRequest[1]);
                        for (int i = 2; i < splittedRequest.length; i++) {
                            args.add(findType(splittedRequest[i]));
                        }
                    } else {
                        throw new IncorrectRequestException("La commande SETLIST attend au moins 2 arguments.");
                    }
                    break;
                case listadd:
                    if (splittedRequest.length >= 3) {
                        args.add(splittedRequest[1]);
                        for (int i = 2; i < splittedRequest.length; i++) {
                            args.add(findType(splittedRequest[i]));
                        }
                    } else {
                        throw new IncorrectRequestException("La commande LISTADD attend au moins 2 arguments.");
                    }
                    break;
                case listremove:
                    if (splittedRequest.length == 3) {
                        args.add(splittedRequest[1]);
                        args.add(findType(splittedRequest[2]));
                    } else {
                        throw new IncorrectRequestException("La commande LISTREMOVE attend 2 arguments.");
                    }
                    break;
                    
                case getelem:
                    if (splittedRequest.length == 3) {
                        Object i = findType(splittedRequest[2]);
                        if (i instanceof Integer) {
                            args.add(splittedRequest[1]);
                            args.add(i);
                        } else {
                            throw new IncorrectRequestException("Le second argument de la commande GETELEM doit être un entier.");
                        }
                    } else {
                        throw new IncorrectRequestException("La commande GETELEM attend 2 arguments.");
                    }
                    break;
                    
                default:
                    throw new IncorrectRequestException("Le premier argument n'est pas une commande.");
            }
        } catch (Exception e) {
            error = e.toString();
        }
    }

    /**
     * @return the request
     */
    public String getRequest() {
        return r;
    }

    /**
     * getCommand()
     *
     * @return la commande associée à cette requête
     */
    public Command getCommand() {
        return command;
    }

    public List<Object> getArgs() {
        return new ArrayList<>(args);
    }

    @Override
    public String toString() {
        return r;
    }

    public static Object findType(String strVal) {
        Object res;
        // Récupérer la valeur
        try {
            res = Integer.parseInt(strVal);
        } catch (NumberFormatException a) {
            try {
                res = Double.parseDouble(strVal);
            } catch (NumberFormatException b) {
                res = "true".equals(strVal) ? true : "false".equals(strVal) ? false : strVal;
            }
        }
        return res;
    }
    
    public boolean isCorrect() {
        return error == null;
    }
    
    public Exception getError() {
        return isCorrect() ? null : new IncorrectRequestException(error);
    }

    public static class IncorrectRequestException extends RuntimeException {

        private String s;

        public IncorrectRequestException(String ss) {
            s = ss;
        }
        
        public IncorrectRequestException() {
        }

        @Override
        public String toString() {
            return s;
        }
        
        @Override
        public boolean equals(Object o) {
            if (o instanceof IncorrectRequestException) {
                IncorrectRequestException oo = (IncorrectRequestException) o;
                return (this.s == null ? oo.s == null : this.s.equals(oo.s));
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 79 * hash + Objects.hashCode(this.s);
            return hash;
        }
    }

    public static class IncorrectRequestTypeException extends RuntimeException {

        public IncorrectRequestTypeException() {
        }
    }

}
