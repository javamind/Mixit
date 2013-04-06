package com.ehret.mixit.ui.activity;

import android.os.Bundle;
import com.ehret.mixit.R;
import com.ehret.mixit.ui.fragment.PlanningHoraireFragment;

import java.util.Date;

public abstract class AbstractPlanningActivity extends AbstractActivity {
    /**
     * Fragment utilisé pour afficher les resultats des différents utilsiateurs
     */
    protected PlanningHoraireFragment planningFragment;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getIdMainLayout());

        //Recup objet declare dans la vue et gerer dynamiquement
        planningFragment = (PlanningHoraireFragment) getFragmentManager().findFragmentById(R.id.planningHoraireFragment);
    }

    /**
     * Recuperation des marques de la partie en cours
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Cette méthode permet d'accéder au fragment affichant les conférences liée à la plage sélectionnee
     * a l'ecran. Cette méthode peut etre appelee par le fragment planning pour deleguer au fragment
     * planningHoraireFragment
     */
    public void refreshPlanningHoraire(Date heure) {
        planningFragment.refreshPlanningHoraire(heure);
    }

    public abstract int getIdMainLayout();
}
