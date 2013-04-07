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
package com.ehret.mixit.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import com.ehret.mixit.R;
import com.ehret.mixit.ui.activity.AbstractPlanningActivity;
import com.ehret.mixit.ui.utils.TableRowBuilder;
import com.ehret.mixit.ui.utils.TextViewBuilder;

import java.util.Date;


/**
 * Classe mère regroupant les fonctions communes permettant de construire le planning
 * d'une journée sous la forme d'un calendrier
 */
public abstract class AbstractCalendarFragment extends Fragment {
    protected TableLayout calendarTableLayout;
    protected TableLayout heureTableLayout;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dessinerCalendrier();
    }

    protected abstract void dessinerCalendrier();

    protected void addConferenceDebut(TableRow tableRow) {
        createElementCalendarTableLayout(tableRow, R.color.blue, getResources().getColor(android.R.color.black), getResources().getString(R.string.calendrier_conf_small), true, false, false, true, 1);

    }

    protected void addConferenceFin(TableRow tableRow) {
        createElementCalendarTableLayout(tableRow, R.color.blue, getResources().getColor(android.R.color.white), getResources().getString(R.string.calendrier_detail), true, false, false, false, 1);
    }

    protected void addAtelierDebut(TableRow tableRow) {
        createElementCalendarTableLayout(tableRow, R.color.yellow1, getResources().getColor(android.R.color.black), getResources().getString(R.string.calendrier_atelier), true, true, false, true, 1);

    }

    protected void addAtelierIntermediaire(TableRow tableRow) {
        createElementCalendarTableLayout(tableRow, R.color.yellow1, getResources().getColor(android.R.color.white), getResources().getString(R.string.calendrier_detail), false, true, false, false, 1);
    }

    protected void addAtelierBlankIntermediaire(TableRow tableRow) {
        createElementCalendarTableLayout(tableRow, R.color.yellow1, getResources().getColor(android.R.color.black), getResources().getString(R.string.blank), false, true, false, false, 1);
    }

    protected void addAtelierFin(TableRow tableRow) {
        createElementCalendarTableLayout(tableRow, R.color.yellow1, getResources().getColor(android.R.color.black), getResources().getString(R.string.blank), true, true, false, false, 1);
    }

    protected void addEvent(TableRow tableRow, String libelle) {
        createElementCalendarTableLayout(tableRow, android.R.color.white, getResources().getColor(android.R.color.black), libelle, true, false, false, true, 1);
    }

    protected void addEventCommun(String libelle, boolean bottomBorder, boolean topBorder, Date heure) {
        TableRow tableRow = createRowCalendar(2, heure);
        createElementCalendarTableLayout(tableRow, android.R.color.white, getResources().getColor(android.R.color.black), libelle, true, true, bottomBorder, topBorder, 2);
    }

    /**
     * Permet d'ajouter une heure dans le tableau heureTableLayout
     *
     * @param heure
     * @param derniereligne
     */
    protected void createRowHeure(String heure, boolean derniereligne, boolean intermediaire) {
        TableRow tableRow = new TableRowBuilder().buildTableRow(getActivity())
                .addNbColonne(1)
                .addBackground(getResources().getColor(R.color.grey)).getView();

        tableRow.addView(new TextViewBuilder()
                .buildTextView(getActivity())
                .addAlignement(Gravity.CENTER)
                .addText(heure)
                .addBorders(true, false, derniereligne, !intermediaire)
                .addPadding(4, 0, 4)
                .addSize(TypedValue.COMPLEX_UNIT_SP, getResources().getInteger(R.integer.text_size_cal))
                .addBackground(getResources().getColor(R.color.grey_light))
                .addTextColor(getResources().getColor(android.R.color.black))
                .getView());
        heureTableLayout.addView(tableRow, TableRowBuilder.getLayoutParams());
    }

    /**
     * Permet de creer une ligne dans calendarTableLayout
     *
     * @param nbcolonne
     */
    protected TableRow createRowCalendar(int nbcolonne, final Date heure) {
        TableRow tableRow = new TableRowBuilder()
                .buildTableRow(getActivity())
                .addBackground(R.color.grey)
                .addNbColonne(nbcolonne)
                .getView();

        tableRow.setClickable(true);

        //Sur un clic on va faire un zoom sur une session
        tableRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afficherPlanningSalleSurPlageHoraire(heure);
            }
        });

        calendarTableLayout.addView(tableRow, TableRowBuilder.getLayoutParams());
        return tableRow;
    }

    /**
     * Ajout d'un entête de colonne dans le tableau calendarTableLayout
     *
     * @param tableRow
     * @param text
     * @param derniereColonne
     */
    protected void addHeaderCalendarTableLayout(TableRow tableRow, String text, boolean derniereColonne) {
        tableRow.addView(new TextViewBuilder()
                .buildTextView(getActivity())
                .addAlignement(Gravity.CENTER)
                .addText(text)
                .addPadding(4, 10, 4)
                .addBorders(true, derniereColonne, false, true)
                .addSize(TypedValue.COMPLEX_UNIT_SP, getResources().getInteger(R.integer.text_size_cal))
                .addBackground(getResources().getColor(R.color.grey_light))
                .addTextColor(getResources().getColor(android.R.color.black))
                .addUpperCase()
                .getView());
    }

    /**
     * Ajout d'un element de colonne dans le tableau calendarTableLayout
     *
     * @param tableRow
     * @param text
     * @param derniereColonne
     */
    protected void createElementCalendarTableLayout(TableRow tableRow, int color, int textcolor, String text,
                                                    boolean bold, boolean derniereColonne, boolean bottomBorder, boolean topBorder, int nbcol) {
        tableRow.addView(new TextViewBuilder()
                .buildTextView(getActivity())
                .addText(text)
                .addAlignement(Gravity.CENTER)
                .addPadding(4, 10, 4)
                .addBold(bold)
                .addSize(TypedValue.COMPLEX_UNIT_SP, getResources().getInteger(R.integer.text_size_cal))
                .addBorders(true, derniereColonne, bottomBorder, topBorder)
                .addBackground(getResources().getColor(color))
                .addTextColor(textcolor)
                .addSpan(nbcol)
                .getView());
    }

    protected void afficherPlanningSalleSurPlageHoraire(Date heure) {
        if (getActivity() instanceof AbstractPlanningActivity) {
            ((AbstractPlanningActivity) getActivity()).refreshPlanningHoraire(heure);
        }

    }
}
