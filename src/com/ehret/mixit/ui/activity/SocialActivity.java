package com.ehret.mixit.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import com.ehret.mixit.R;

public class SocialActivity extends AbstractActivity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
    }

    /**
     * Recuperation des marques de la partie en cours
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void refresh() {
        Intent refresh = new Intent(this, SocialActivity.class);
        startActivity(refresh);
        this.finish();
    }
}
