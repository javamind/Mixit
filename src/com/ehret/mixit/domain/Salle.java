package com.ehret.mixit.domain;

import com.ehret.mixit.R;

/**
 * Created with IntelliJ IDEA.
 * User: EHRET_G
 * Date: 04/04/13
 * Time: 21:27
 * To change this template use File | Settings | File Templates.
 */
public enum Salle {
    SALLE1("1 (100p)", R.color.green1),
    SALLE2("2 (100p)",R.color.violet1),
    SALLE3("3 (80p)",R.color.red1),
    SALLE4("4 (60p)",R.color.yellow1),
    SALLE5("5 (25p)",R.color.pink1),
    INCONNU("Inconnue",R.color.grey);

    private String nom;
    private int color;

    private Salle(String nom, int color) {
        this.nom = nom;
        this.color=color;
    }

    public String getNom() {
        return nom;
    }

    public int getColor() {
        return color;
    }

    public static Salle getSalle(String nom){
        if(nom!=null){
            for(Salle salle : values()){
                if(salle.getNom().equals(nom)){
                    return salle;
                }
            }
        }
        return INCONNU;
    }
}
