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
package com.ehret.mixit.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.ehret.mixit.R;
import com.ehret.mixit.domain.TypeFile;
import com.ehret.mixit.domain.people.Membre;
import com.ehret.mixit.model.MembreFacade;
import com.ehret.mixit.ui.utils.FileUtils;
import com.ehret.mixit.ui.utils.UIUtils;

/**
 * Activity permettant d'afficher les informations sur une personne participant à Mix-IT
 */
public class MembreActivity extends AbstractActivity {

    private TextView temp;
    private Long id;
    private String type;
    private TextView membreTitle;
    private ImageView profileImage;
    private TextView membreUserName;
    private TextView personDesciptif;
    private TextView personShortDesciptif;
    private TextView membreEntreprise;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membre);

        this.membreTitle = (TextView) findViewById(R.id.membre_title);
        this.membreUserName = (TextView) findViewById(R.id.membre_user_name);
        this.personDesciptif = (TextView) findViewById(R.id.membre_desciptif);
        this.personShortDesciptif = (TextView) findViewById(R.id.membre_shortdesciptif);
        this.membreEntreprise = (TextView) findViewById(R.id.membre_entreprise);
        this.profileImage = (ImageView) findViewById(R.id.membre_image);

        if (getIntent().getExtras() != null) {
            id = getIntent().getExtras().getLong(UIUtils.MESSAGE);
            type = getIntent().getExtras().getString(UIUtils.TYPE);
        }
    }

    /**
     * Recuperation des marques de la partie en cours
     */
    @Override
    protected void onResume() {
        super.onResume();

        //On commence par recuperer le Membre que l'on sohaite afficher
        Membre membre = MembreFacade.getInstance().getMembre(getBaseContext(), type, id);
        TypeFile typeFile = TypeFile.getTypeFile(type);
        switch (typeFile) {
            case staff:
                this.membreTitle.setText(getText(R.string.membre_staff) + " \n");
                this.membreTitle.setBackgroundResource(R.color.orange);
                break;
            case members:
                this.membreTitle.setText(getText(R.string.membre_membre) + " \n");
                this.membreTitle.setBackgroundResource(R.color.violet1);
                break;
            case sponsor:
                this.membreTitle.setText(getText(R.string.membre_sponsor) + " \n");
                this.membreTitle.setBackgroundResource(R.color.pink1);
                break;
            default:
                this.membreTitle.setText(getText(R.string.membre_speaker) + " \n");
                this.membreTitle.setBackgroundResource(R.color.green1);
        }
        this.membreTitle.setLines(2);
        this.membreUserName.setText(membre.getFirstname() + " " + membre.getLastname());
        this.membreEntreprise.setText(membre.getCompany());
        this.personDesciptif.setText(membre.getLongdesc().trim());
        this.personShortDesciptif.setText(membre.getShortdesc().trim());

        //Recuperation de l'mage liee au profil
        Bitmap image = FileUtils.getImage(getBaseContext(), membre);
        if (image == null) {
            profileImage.setImageDrawable(getResources().getDrawable(R.drawable.person_image_empty));
        } else {
            //On regarde dans les images embarquees
            profileImage.setImageBitmap(image);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("ID_MEMBRE", id);
        outState.putString("TYPE_MEMBRE", type);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Long myId = savedInstanceState.getLong("ID_MEMBRE");
        if (myId != null) {
            id = myId;
        }
        String myType = savedInstanceState.getString("TYPE_MEMBRE");
        if (myType != null) {
            type = myType;
        }
    }
}
