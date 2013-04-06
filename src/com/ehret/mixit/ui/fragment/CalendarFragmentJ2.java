package com.ehret.mixit.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import com.ehret.mixit.R;
import com.ehret.mixit.ui.utils.UIUtils;


/**
 * Planning de la seconde journée
 */
public class CalendarFragmentJ2 extends AbstractCalendarFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }
    @Override
    protected  void dessinerCalendrier() {
        //deux tableaux juxtaposer
        //Un d'une colonne pour gérer l'heure
        heureTableLayout = (TableLayout) getActivity().findViewById(R.id.heureTableLayout);
        heureTableLayout.removeAllViews();
        //Un deuxième pour le contenu
        calendarTableLayout = (TableLayout) getActivity().findViewById(R.id.calendarTableLayout);
        calendarTableLayout.removeAllViews();

        TableRow tableRow = null;

        //On commence par les heures
        createRowHeure(" ", false, false);
        createRowHeure(" 9      ", false, false);
        createRowHeure(" ", false, false);
        createRowHeure("10      ", false, true);
        createRowHeure(" ", false, false);
        createRowHeure("11      ", false, false);
        createRowHeure(" ", false, true);
        createRowHeure("12      ", false, false);
        createRowHeure(" ", false, false);
        createRowHeure("13      ", false, false);
        createRowHeure(" ", false, false);
        createRowHeure("14      ", false, true);
        createRowHeure(" ", false, false);
        createRowHeure("15      ", false, false);
        createRowHeure(" ", false, true);
        createRowHeure("16      ", false, false);
        createRowHeure(" ", false, false);
        createRowHeure("17      ", false, true);
        createRowHeure(" ", false, false);
        createRowHeure("18      ", false, false);
        createRowHeure(" ", false, true);
        createRowHeure("19      ", false, false);
        createRowHeure(" ", true, true);


        //Une ligne d'entete
        tableRow = createRowCalendar(2, UIUtils.createPlageHoraire(25, 9, false));
        addHeaderCalendarTableLayout(tableRow, getResources().getString(R.string.calendrier_confs), false);
        addHeaderCalendarTableLayout(tableRow, getResources().getString(R.string.calendrier_ateliers), true);

        //On construit le tableau demi-heure par demi-heure

        //On commence par la keynote 9H->9H30
        addEventCommun(getResources().getString(R.string.calendrier_keynote), false, true, UIUtils.createPlageHoraire(25, 9, false));

        //Conf + atelier 9H->10H00
        tableRow = createRowCalendar(2, UIUtils.createPlageHoraire(25, 9, true));
        addConferenceDebut(tableRow);
        addAtelierDebut(tableRow);

        //Conf + atelier 10H00->10H30
        tableRow = createRowCalendar(2, UIUtils.createPlageHoraire(25,10 , false));
        addConferenceFin(tableRow);
        addAtelierIntermediaire(tableRow);

        //Pause  + pas pour atelier 10H30->11H
        tableRow = createRowCalendar(2, UIUtils.createPlageHoraire(25,10 , true));
        addEvent(tableRow, getResources().getString(R.string.calendrier_pause));
        addAtelierFin(tableRow);

        //Conf + atelier  11H->11H30
        tableRow = createRowCalendar(2, UIUtils.createPlageHoraire(25, 11, false));
        addConferenceDebut(tableRow);
        addAtelierDebut(tableRow);

        //11H30->12H00
        tableRow = createRowCalendar(2, UIUtils.createPlageHoraire(25, 11, true));
        addConferenceFin(tableRow);
        addAtelierIntermediaire(tableRow);

        //Repas  + pas pour atelier 12H->12H30
        tableRow = createRowCalendar(2, UIUtils.createPlageHoraire(25, 12, false));
        addEvent(tableRow, getString(R.string.calendrier_repas));
        addAtelierFin(tableRow);


        //Repas  12H30->13H
        addEventCommun(getResources().getString(R.string.calendrier_repas), false, true, UIUtils.createPlageHoraire(25, 12, true));

        //Lightning  + Atelier  13H->13H30
        tableRow = createRowCalendar(2, UIUtils.createPlageHoraire(25, 13, false));
        addEvent(tableRow, getString(R.string.calendrier_ligthning));
        addAtelierDebut(tableRow);
        ;

        //Conf + atelier 13H30->14H
        tableRow = createRowCalendar(2, UIUtils.createPlageHoraire(25, 13, true));
        addConferenceDebut(tableRow);
        addAtelierIntermediaire(tableRow);

        // 14H->14H30
        tableRow = createRowCalendar(2, UIUtils.createPlageHoraire(25, 14, false));
        addConferenceFin(tableRow);
        addAtelierFin(tableRow);

        //14H30->15H Pause + nouveau atelier
        tableRow = createRowCalendar(2, UIUtils.createPlageHoraire(25, 14, true));
        addEvent(tableRow, getString(R.string.calendrier_pause));
        addAtelierDebut(tableRow);
        ;

        // 15H->15H30
        tableRow = createRowCalendar(2, UIUtils.createPlageHoraire(25, 15, false));
        addConferenceDebut(tableRow);
        addAtelierIntermediaire(tableRow);

        //15H30->16H
        tableRow = createRowCalendar(2, UIUtils.createPlageHoraire(25, 15, true));
        addConferenceFin(tableRow);
        addAtelierFin(tableRow);

        // 16H->16H30
        tableRow = createRowCalendar(2, UIUtils.createPlageHoraire(25, 16, false));
        addEvent(tableRow, getString(R.string.calendrier_pause));
        addAtelierDebut(tableRow);
        ;

        //16H30->17H Pause + nouveau atelier
        tableRow = createRowCalendar(2, UIUtils.createPlageHoraire(25, 16, true));
        addConferenceDebut(tableRow);
        addAtelierIntermediaire(tableRow);

        // 17H->17H30
        tableRow = createRowCalendar(2, UIUtils.createPlageHoraire(25, 17, false));
        addConferenceFin(tableRow);
        addAtelierFin(tableRow);




        //17H30->19H
        addEventCommun(getResources().getString(R.string.blank), false, true, UIUtils.createPlageHoraire(25,17 , true));
        addEventCommun(getResources().getString(R.string.blank), false, false, UIUtils.createPlageHoraire(25, 18, false));
        addEventCommun(getResources().getString(R.string.blank), false, false, UIUtils.createPlageHoraire(25, 18, true));
        addEventCommun(getResources().getString(R.string.blank), false, false, UIUtils.createPlageHoraire(25, 19, false));
        addEventCommun(getResources().getString(R.string.blank), true, false, UIUtils.createPlageHoraire(25, 19, true));


    }
}
