/*
 * Copyright 2013 Guillaume EHRET
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
    SALLE2("2 (100p)", R.color.violet1),
    SALLE3("3 (80p)", R.color.red1),
    SALLE4("4 (60p)", R.color.yellow1),
    SALLE5("5 (25p)", R.color.pink1),
    INCONNU("Inconnue", R.color.grey);

    private String nom;
    private int color;

    private Salle(String nom, int color) {
        this.nom = nom;
        this.color = color;
    }

    public String getNom() {
        return nom;
    }

    public int getColor() {
        return color;
    }

    public static Salle getSalle(String nom) {
        if (nom != null) {
            for (Salle salle : values()) {
                if (salle.getNom().equals(nom)) {
                    return salle;
                }
            }
        }
        return INCONNU;
    }
}
