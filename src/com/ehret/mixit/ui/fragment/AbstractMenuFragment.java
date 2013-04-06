package com.ehret.mixit.ui.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.ehret.mixit.R;
import com.ehret.mixit.ui.utils.TableRowBuilder;
import com.ehret.mixit.ui.utils.TextViewBuilder;

/**
 * Fragment utilise sur la page daccueil pour afficher les talks
 */
public abstract class AbstractMenuFragment extends Fragment {
    protected TableLayout menuTableLayout;
    protected TextView titleMenu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayout(), container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dessinerMenu();
    }


    /**
     * Affiche les données à l'écran
     */
    protected void dessinerMenu(){
        Context context = getActivity().getBaseContext();

        //Mise a jour du titre
        if(titleMenu == null){
            titleMenu = (TextView) getActivity().findViewById(getIdTitle());
        }
        titleMenu.setText(context.getText(getNameMenu()));

        //deux tableaux juxtaposer
        //Un d'une colonne pour gérer l'heure
        if(menuTableLayout==null){
            menuTableLayout = (TableLayout) getActivity().findViewById(getIdTable());
        }
        menuTableLayout.removeAllViews();

        createElementsMenu();
    }

    public abstract int getLayout();

    public abstract int getIdTitle();

    public abstract int getIdTable();

    /**
     * Template methode pour que les classes filles indiquent le nom du menu
     * @return
     */
    public abstract int getNameMenu();

    /**
     * * Template methode pour que les classes filles composent les lignes du menu
     */
    public abstract void createElementsMenu();

    /**
     * Créé une ligne dans le tableau afichant les données
     * @param color
     * @param nom
     * @param dernierligne
     */
    protected void createMenu(int color, String nom,  boolean dernierligne, View.OnClickListener listener) {
        TableRow tableRow = new TableRowBuilder().buildTableRow(getActivity())
                .addNbColonne(2)
                .addBackground(getResources().getColor(R.color.grey)).getView();

        TextView colorView = new TextViewBuilder()
                .buildTextView(getActivity())
                .addText("   \n ")
                .addPadding(4, 0, 4)
                .addBackground(getResources().getColor(color))
                .addNbLines(2)
                .addNbMaxLines(2)
                .getView();
        tableRow.addView(colorView);

        TextView textView = new TextViewBuilder()
                .buildTextView(getActivity())
                .addAlignement(Gravity.CENTER)
                .addText(nom + "\n ")
                .addBorders(true, true, dernierligne, true)
                .addPadding(4, 0, 4)
                .addNbLines(2)
                .addNbMaxLines(2)
                .addBold(true)
                .addBackground(getResources().getColor(android.R.color.white))
                .addTextColor(getResources().getColor(android.R.color.black))
                .getView();
        textView.setOnClickListener(listener);
        tableRow.addView(textView);



        menuTableLayout.addView(tableRow, TableRowBuilder.getLayoutParams());
    }


}
