package com.ehret.mixit.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ehret.mixit.R;
import com.ehret.mixit.domain.Salle;
import com.ehret.mixit.domain.TypeFile;
import com.ehret.mixit.domain.talk.Conference;
import com.ehret.mixit.domain.talk.Talk;
import com.ehret.mixit.model.ConferenceFacade;
import com.ehret.mixit.ui.utils.UIUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SalleActivity extends AbstractActivity {
    private ImageView image;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salle);

        image = (ImageView) findViewById(R.id.image_salle);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
     }

}
