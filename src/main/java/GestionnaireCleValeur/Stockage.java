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
    
    public boolean set(String cle, Object valeur) throws Exception {
        stock.put(cle, valeur);
        return true;
    }
    
    public Object get(String cle) throws Exception {
        return stock.get(cle);
    }
    
    public int incr(String key) throws NotIntegerException, Exception {
        return incr(key, 1);
    }

    public int incr(String key, int i) throws NotIntegerException, Exception {
        Object val = get(key);
        if (val instanceof Integer) {
            Integer t = (Integer) val;
            set(key, t+i);
            return t+i;
        }
        else {
            throw new NotIntegerException();
        }
    }

    public static class NotIntegerException extends Exception {

        public NotIntegerException() {
        }
    }
    
}
