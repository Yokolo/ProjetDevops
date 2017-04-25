/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionnaireCleValeur;

import Communication.Request;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public Object get(String cle) throws Request.IncorrectRequestException {
        Object res = stock.get(cle);
        if (res == null) {
            throw new Request.IncorrectRequestException("Aucune variable associée à cette clé.");
        }
        return res;
    }

    public int incr(String key) throws Request.IncorrectRequestException {
        return incr(key, 1);
    }

    public int incr(String key, int i) throws Request.IncorrectRequestException {
        Object val = get(key);

        if (val instanceof Integer) {
            Integer t = (Integer) val;
            set(key, t + i);
            return t + i;
        } else {
            throw new Request.IncorrectRequestException("La variable associée à cette clé n'est pas un entier.");
        }
    }

    public boolean setlist(String key, List<Object> l) {
        List<Object> li = new ArrayList<>(l);
        stock.put(key, li);
        return true;
    }

    public List<Object> getlist(String key) throws Request.IncorrectRequestException {
        Object o = stock.get(key);
        if (o == null || !(o instanceof List)) {
            throw new Request.IncorrectRequestException("Aucune liste associée à cette clé.");
        } else {
            return (List) o;
        }

    }

    public boolean listadd(String key, List<Object> l) throws Request.IncorrectRequestException {
        List<Object> li = getlist(key);
        li.addAll(l);
        stock.put(key, li);
        return true;

    }
    public boolean listremove(String key, Object o) throws Request.IncorrectRequestException {
        List<Object> li = getlist(key);
        boolean res = li.remove(o);
        stock.put(key, li);
        return res;

    }
}
