/*
 * Copyright 2012 Google Inc.
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

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.ehret.mixit.R;
import com.ehret.mixit.domain.JsonFile;
import com.ehret.mixit.domain.TypeFile;
import com.ehret.mixit.domain.people.Membre;
import com.ehret.mixit.model.ConferenceFacade;
import com.ehret.mixit.model.MembreFacade;
import com.ehret.mixit.model.Synchronizer;
import com.ehret.mixit.ui.fragment.DialogAboutFragment;
import com.ehret.mixit.ui.utils.FileUtils;
import com.ehret.mixit.ui.utils.UIUtils;

import java.util.List;

/**
 * Top activity with all the commons declarations
 */
public abstract class AbstractActivity extends Activity {
    private int progressStatus = 0;
    private ProgressDialog progressDialog ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Association du bon fichier de menu
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        //Le menu refresh est masque pour les activities qui n'en ont pas besoin
        if(!(this instanceof SocialActivity)){
            menu.removeItem(R.id.menu_refresh);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = null;
        switch (item.getItemId()) {
            case android.R.id.home:
                return UIUtils.startActivity(HomeActivity.class, this);
            case R.id.menu_about:
                DialogAboutFragment dial = new DialogAboutFragment();
                dial.show(getFragmentManager(), getResources().getString(R.string.about_titre));
                return true;
            case R.id.menu_twitter:
                return UIUtils.startActivity(SocialActivity.class, this);
            case R.id.menu_refresh:
                refresh();
                return true;
            case R.id.menu_compose_google:
                sendMessage(SendSocial.plus);
                return true;
            case R.id.menu_compose_twitter:
                sendMessage(SendSocial.twitter);
                return true;
            case R.id.menu_sync:
                chargementDonnees();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void chargementDonnees() {
        if(UIUtils.isNetworkAvailable(getBaseContext())){
            if(FileUtils.isExternalStorageWritable()){
                if(progressDialog==null){
                    progressDialog = new ProgressDialog(this);
                }
                progressDialog.setCancelable(true);
                progressDialog.setMax( MembreFacade.getInstance().getMembres(getBaseContext(), TypeFile.members.name()).size() + JsonFile.values().length);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setMessage(getResources().getString(R.string.sync_message));
                progressDialog.show();
                SynchronizeMixitAsync synchronizeMixitAsync = new SynchronizeMixitAsync();
                synchronizeMixitAsync.execute();
            }
            else{
                Toast.makeText(getBaseContext(), getText(R.string.sync_erreur), Toast.LENGTH_LONG);
            }
        }
        else{
            Toast.makeText(getBaseContext(), getText(R.string.sync_erreur_reseau), Toast.LENGTH_LONG);
        }
    }

    /**
     * Template methode pouvant être surchargée par les écrans pour gérer le refresh
     */
    public void refresh(){}

    private enum SendSocial {twitter, plus}
    /**
     * Permet d'envoyer un message en filtrant les intents
     * @param type
     */
    private void sendMessage(SendSocial type){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_TEXT,"#MixIT_lyon");
        if(!UIUtils.filterIntent(this, type.name(), i)){
            Toast.makeText(getBaseContext(), SendSocial.plus.equals(type) ? R.string.description_no_google : R.string.description_no_twitter, Toast.LENGTH_SHORT);
        }
        startActivity(Intent.createChooser(i, "Share URL"));
    }


    /**
     * Lance en asynchrone la recuperation des fichiers
     */
    private class SynchronizeMixitAsync extends AsyncTask<Void, Integer, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressStatus = 0;
        }

        @Override
        protected Void doInBackground(Void... params) {
            for(JsonFile json : JsonFile.values()){
                try{
                    if(!Synchronizer.downloadJsonFile(getBaseContext(), json.getUrl(), json.getType())){
                        //Si une erreur de chargement on sort
                        break;
                    }
                    publishProgress(progressStatus++);
                }
                catch (RuntimeException e){
                    Log.w("DialogSynchronizeFragment", "Impossible de synchroniser", e);
                }
            }
            //Une fois finie on supprime le cache
            MembreFacade.getInstance().viderCache();
            ConferenceFacade.getInstance().viderCache();

            //On pren les membres s'ils viennent d'etre recharge
            List<Membre> membres = MembreFacade.getInstance().getMembres(getBaseContext(), TypeFile.members.name());
            for(Membre membre : membres){
                if(membre.getUrlimage()!=null){
                    Synchronizer.downloadImage(getBaseContext(), membre.getUrlimage(),"membre"+ membre.getId());
                    publishProgress(progressStatus++);
                }
            }

            return null;
        }

        /** This callback method is invoked when publishProgress()
         * method is called */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(progressStatus);
        }

        /** This callback method is invoked when the background function
         * doInBackground() is executed completely */
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
        }
    }


}