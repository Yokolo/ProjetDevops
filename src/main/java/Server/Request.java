/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.util.ArrayList;
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
        get("get key - récupère la valeur associée à la clé key si elle existe"),
        set("set key value - insére la valeur value pour la clé key"),
        incr("incr key [i=1] - Incrémente la valeur associée à la clé key de la valeur i s'il s'agit d'un entier");

        private final String helpText;

        Command(String s) {
            helpText = s;
        }

        public String getHelp() {
            return helpText;
        }
    };

    private String r;
    private Command command;
    private List<Object> args;

    public Request() {};
    public Request(String request) {
        r = request;
        if ("".equals(r)) {
        } else {
            parseRequest(request);
        }
    }

    private void parseRequest(String request) {
        if ("".equals(request)) {
            throw new IncorrectRequestException("Requête vide.");
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
                default:
                    throw new IncorrectRequestException("Le premier argument n'est pas une commande.");
            }

            // Recuperation des arguments
            args = new ArrayList<>();
            switch (getCommand()) {
                case set:
                    if (splittedRequest.length == 3) {
                        args.add(splittedRequest[1]);
                        // Récupérer la valeur
                        String strVal = splittedRequest[2];
                        try {
                            args.add(Integer.parseInt(strVal));
                        } catch (NumberFormatException a) {
                            try {
                                args.add(Double.parseDouble(strVal));
                            } catch (NumberFormatException b) {
                                args.add(strVal);
                            }
                        }
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
                    if (splittedRequest.length == 2) {
                        args.add(splittedRequest[1]);
                        args.add(1);
                    } else if (splittedRequest.length == 2) {
                        try {
                            args.add(splittedRequest[1]);
                            args.add(Integer.parseInt(splittedRequest[2]));
                        } catch (Exception e) {
                            throw new IncorrectRequestException("Le second argment de la commande INCR doit être un entier.");
                        }
                    } else {
                        throw new IncorrectRequestException("La commande INCR attend 1 ou 2 argument(s).");
                    }
                    break;

                default:
                    throw new IncorrectRequestException("Le premier argument n'est pas une commande.");
            }
        } catch (Exception e) {
            throw new IncorrectRequestException("La requête n'est pas valide.");
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

    public static class IncorrectRequestException extends RuntimeException {

        String s;

        public IncorrectRequestException(String ss) {
            s = ss;
        }
    }

    public static class IncorrectRequestTypeException extends RuntimeException {

        public IncorrectRequestTypeException() {
        }
    }

}
