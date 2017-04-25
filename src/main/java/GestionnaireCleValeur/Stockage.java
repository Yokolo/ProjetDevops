/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionnaireCleValeur;

import com.esotericsoftware.kryonet.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author badWarden
 */
public class Stockage extends Connection {

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

    public boolean setlist(String key, List<Object> l) {
        List<Object> li = new ArrayList<>(l);
        stock.put(key, li);
        return true;
    }

    public List<Object> getlist(String key) throws IncorrectKeyException {
        Object o = stock.get(key);
        if (o == null || !(o instanceof List)) {
            throw new IncorrectKeyException();
        } else {
            return (List) o;
        }

    }

    public boolean listadd(String key, List<Object> l) throws IncorrectKeyException {
        List<Object> li = getlist(key);
        li.addAll(l);
        stock.put(key, li);
        return true;

    }
    public boolean listremove(String key, Object o) throws IncorrectKeyException{
        List<Object> li = getlist(key);
        boolean res = li.remove(o);
        stock.put(key, li);
        return res;

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
