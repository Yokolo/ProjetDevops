/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projetdevops;

import com.mycompany.projetdevops.GestionnaireCleValeur.Stockage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author badWarden
 */
public class Main {
    public static void main(String[] args) {
        try {
            Stockage s = new Stockage();
            
            s.set("Bonjour", 15);
            Object v = s.get("bonsoir");
            
            System.out.println(v);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
