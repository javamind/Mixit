package com.ehret.mixit.ui.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.text.format.Time;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: EHRET_G
 * Date: 20/03/13
 * Time: 20:31
 * To change this template use File | Settings | File Templates.
 */
public class UIUtils {

    private static final Time sTime = new Time();
    public static final long CONFERENCE_START_MILLIS = parseTime("2013-04-25T08:30:00.000-07:00");
    public static final long CONFERENCE_END_MILLIS = parseTime("2013-04-26T18:30:00.000-07:00");
    public static final String MESSAGE = "message";
    public static final String TYPE = "type";

    /**
     * Parse the given string as a RFC 3339 timestamp, returning the value as
     * milliseconds since the epoch.
     */
    private static long parseTime(String time) {
        sTime.parse3339(time);
        return sTime.toMillis(false);
    }

    /**
     * Verifie si la connectivite reseau est OK
     * @return
     */
    public static boolean isNetworkAvailable(Context c) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    /**
     * Ouvre une Intent
     * @param activityClass
     * @param activity
     * @return
     */
    public static boolean startActivity(Class activityClass, Activity activity) {
        return startActivity(activityClass, activity, null);
    }


    /**
     * Ouvre une Intent
     * @param activityClass
     * @param activity
     * @param parametres on simplifie et on  ne prend en compte que les Longs et les Strings
     * @return
     */
    public static boolean  startActivity(Class activityClass, Activity activity, Map<String, Object> parametres) {
        Intent i;
        i = new Intent(activity, activityClass);
        i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if(parametres!=null){
            for(String key : parametres.keySet()){
                Object param = parametres.get(key);
                if(param!=null){
                    if(param instanceof Long){
                        i.putExtra(key,((Long) param).longValue());
                    }
                    else{
                        i.putExtra(key,param.toString());
                    }
                }
            }
        }
        activity.startActivity(i);
        return true;
    }

    /**
     *
     * @param activityClass
     * @param activity
     * @param id
     * @return
     */
    public static boolean  startActivity(Class activityClass, Activity activity, long id) {
        Intent i;
        i = new Intent(activity, activityClass);
        i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.putExtra(MESSAGE,id);
        activity.startActivity(i);
        return true;
    }

    /**
     * Permet de filtrer les intents proposées à l'utilisateur
     * @param activity
     * @param type
     * @param i
     * @return
     */
    public static boolean filterIntent(Activity activity,String type, Intent i) {
        List<ResolveInfo> resInfo = activity.getPackageManager().queryIntentActivities(i, 0);
        if (!resInfo.isEmpty()){
            boolean found=false;
            for (ResolveInfo info : resInfo) {
                if (info.activityInfo.packageName.toLowerCase().contains(type)) {
                    i.setPackage(info.activityInfo.packageName);
                    found = true;
                    break;
                }
            }
            return found;
        }
        return false;
    }

    /**
     * Creation de la bonne date
     * @param jour
     * @param heure
     * @param demiheure
     * @return
     */
    public static Date createPlageHoraire(int jour, int heure, boolean demiheure){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2013,3,jour,heure,demiheure ? 30 : 0,0);
        return calendar.getTime();
    }
}
