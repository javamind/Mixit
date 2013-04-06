package com.ehret.mixit.ui.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.text.format.Time;
import com.ehret.mixit.R;
import com.ehret.mixit.domain.JsonFile;
import com.ehret.mixit.domain.TypeFile;
import com.ehret.mixit.domain.people.Membre;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Regroupe les classes utilitaires sur les fichiers
 */
public class FileUtils {

    /**
     * Indique si stockage dispo
     * @return
     */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }


    /**
     * Cette méthode  le fichier que l'on souhaite recuperer
     * @param context
     * @param typeFile
     * @return
     */
    public static File createFileJson(Context context, TypeFile typeFile) throws IOException {
        File myFile = new File(context.getExternalFilesDir(Environment.DIRECTORY_DCIM), typeFile.name() + ".json");
        if(myFile.exists()){
            myFile.delete();
        }
        myFile.createNewFile();
        return myFile;
    }


    public static void razFileJson(Context context)  {
        for(JsonFile json : JsonFile.values()){
            File myFile = new File(context.getExternalFilesDir(Environment.DIRECTORY_DCIM), json.getType().name() + ".json");
            if(myFile.exists()){
                myFile.delete();
            }
        }
        //TODO a commente
//        SharedPreferences settings = context.getSharedPreferences(UIUtils.PREFS_FAVORITES_NAME, 0);
//        SharedPreferences.Editor editor = settings.edit();
//        editor.clear();
//        editor.commit();

    }

    /**
     * Cette méthode  recupere le fichier correspondant à une ressource. Si la ressource n'a pu être téléchargée
     * on s'appuie sur la version en local
     * @param context
     * @param typeFile
     * @return
     */
    public static File getFileJson(Context context, TypeFile typeFile) throws IOException {
        File myFile = new File(context.getExternalFilesDir(Environment.DIRECTORY_DCIM), typeFile.name() + ".json");
        if(myFile.exists()){
            return myFile;
        }
        return null;

    }

    /**
     *
     * @param context
     * @param membre
     * @return
     */
    public static Bitmap getImage(Context context, Membre membre){
        if(membre.getUrlimage()!=null){
            //String ext = membre.getUrlimage().endsWith(".png") ? ".png" : ".jpg";
            File myFile = new File(context.getExternalFilesDir(Environment.DIRECTORY_DCIM),"membre"+ membre.getId()+".jpg");
            if(myFile.exists()){
                try {
                    return BitmapFactory.decodeStream(new FileInputStream(myFile));
                } catch (IOException e) {
                    return null;
                }
            }

        }
        return null;
    }

    /**
     *
     * @param context
     * @param typeFile
     * @return
     * @throws IOException
     */
    public static InputStream getRawFileJson(Context context, TypeFile typeFile) throws IOException {
        //Sinon on prend celui dans les raw file
        InputStream is = null;
        switch (typeFile){
            case interests:
                is=context.getResources().openRawResource(R.raw.interests);
                break;
            case lightningtalks:
                is=context.getResources().openRawResource(R.raw.ligthningtalk);
                break;
            case speaker:
                is=context.getResources().openRawResource(R.raw.speaker);
                break;
            case members:
                is=context.getResources().openRawResource(R.raw.membre);
                break;
            case sponsor:
                is=context.getResources().openRawResource(R.raw.sponsor);
                break;
            case staff:
                is=context.getResources().openRawResource(R.raw.staff);
                break;
            default:
                is=context.getResources().openRawResource(R.raw.talks);
        }
        return is;
    }


}
