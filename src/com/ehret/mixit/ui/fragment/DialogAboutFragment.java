package com.ehret.mixit.ui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import com.ehret.mixit.R;
import com.ehret.mixit.ui.utils.FileUtils;

/**
 * Boite de dialogue permettant de synchroniser les données à partir des
 * webservices mises à disposition sur le sit eweb de mix-it
 */
public class DialogAboutFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.fragment_about, null))
                .setMessage(getResources().getString(R.string.about_titre))
                .setPositiveButton(getText(R.string.dial_raz), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        FileUtils.razFileJson(getActivity());
                    }
                })
                .setNegativeButton(getText(R.string.dial_OK),null);



        // Create the AlertDialog object and return it
        return builder.create();
    }



}
