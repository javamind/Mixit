package com.ehret.mixit.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ehret.mixit.R;
import com.ehret.mixit.domain.people.Link;
import com.ehret.mixit.domain.people.Membre;
import com.ehret.mixit.ui.utils.FileUtils;
import com.ehret.mixit.ui.utils.UIUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListMembreAdapter extends BaseAdapter {
    private List<Membre> datas;
    private Context context;

    public ListMembreAdapter(Context context, List<Membre> datas) {
        this.datas = datas;
        this.context=context;
    }

    @Override
    public int getCount() {
	    return datas.size();
    }

    @Override
    public Membre getItem(int position) {
	    return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
	    return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.person_item, null);
            holder = new ViewHolder();
            holder.profile_image = (ImageView) convertView.findViewById(R.id.person_user_image);
            holder.userName = (TextView) convertView.findViewById(R.id.person_user_name);
            holder.descriptif = (TextView) convertView.findViewById(R.id.person_shortdesciptif);
            holder.level = (TextView) convertView.findViewById(R.id.person_level);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Membre person = datas.get(position);
        holder.userName.setText(person.getFirstname() + " " + person.getLastname());
        if (person.getShortdesc()!=null){
            holder.descriptif.setText(person.getShortdesc().trim());
        }

        if(person.getLevel()!=null && !person.getLevel().isEmpty()){
            holder.level.setText("[" + person.getLevel().trim() +"]");
        }

        //Recuperation de l'mage liee au profil
        Bitmap image = FileUtils.getImage(context, person);
        if(image==null){
            holder.profile_image.setImageDrawable(context.getResources().getDrawable(R.drawable.person_image_empty));
        }
        else{
            //On regarde dans les images embarquees
            holder.profile_image.setImageBitmap(image);
        }

        //Pour le moment on ne

        return convertView;
    }

    static class ViewHolder {
        TextView userName;
        TextView descriptif;
        TextView level;
        ImageView profile_image;

    }

}
