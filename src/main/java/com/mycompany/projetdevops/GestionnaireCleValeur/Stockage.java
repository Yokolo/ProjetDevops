/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projetdevops.GestionnaireCleValeur;

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
    
}
