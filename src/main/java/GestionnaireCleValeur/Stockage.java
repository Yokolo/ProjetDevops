/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionnaireCleValeur;

import java.util.HashMap;

/**
 *
 * @author badWarden
 */
public class Stockage {

    HashMap<String, Object> stock;

    public Stockage() {
        stock = new HashMap<>();
    }

    public boolean set(String cle, Object valeur) {
        stock.put(cle, valeur);
        return true;
    }

    public Object get(String cle) throws IncorrectKeyException {
        Object res = stock.get(cle);
        if (res == null) {
            throw new IncorrectKeyException();
        }
        return res;
    }

    public int incr(String key) throws IncorrectKeyException, NotIntegerException {
        return incr(key, 1);
    }

    public int incr(String key, int i) throws IncorrectKeyException, NotIntegerException {
        Object val = get(key);
        
        if (val instanceof Integer) {
            Integer t = (Integer) val;
            set(key, t + i);
            return t + i;
        } else {
            throw new NotIntegerException();
        }
    }

    public static class NotIntegerException extends Exception {

        public NotIntegerException() {
        }
    }

    public static class IncorrectKeyException extends Exception {

        public IncorrectKeyException() {
        }
    }

}
