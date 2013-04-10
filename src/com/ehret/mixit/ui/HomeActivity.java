package com.ehret.mixit.ui;

import android.os.Bundle;
import com.ehret.mixit.R;

public class HomeActivity extends AbstractActivity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    }

    /**
     * Recuperation des marques de la partie en cours
     */
    @Override
    protected void onResume() {
        super.onResume();
    }


}
