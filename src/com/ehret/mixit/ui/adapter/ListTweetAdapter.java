package com.ehret.mixit.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ehret.mixit.R;
import com.ehret.mixit.domain.twitter.Tweet;

import java.util.List;

public class ListTweetAdapter extends BaseAdapter {
    private List<Tweet> tweets;
    private Context context;
    
    public ListTweetAdapter(Context context, List<Tweet> tweets) {
        this.tweets = tweets;
        this.context=context;
    }

    @Override
    public int getCount() {
	    return tweets.size();
    }

    @Override
    public Tweet getItem(int position) {
	    return tweets.get(position);
    }

    @Override
    public long getItemId(int position) {
	    return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.tweet_item, null);
            holder = new ViewHolder();
            holder.content = (TextView) convertView.findViewById(R.id.tweet_content);
            holder.userName = (TextView) convertView.findViewById(R.id.tweet_username);
            holder.user = (TextView) convertView.findViewById(R.id.tweet_user);
            holder.since = (TextView) convertView.findViewById(R.id.tweet_since);
            holder.profile_image = (ImageView) convertView.findViewById(R.id.tweet_profile_image);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Tweet tweet = tweets.get(position);
        holder.user.setText("@"+tweet.getFrom_user());
        holder.userName.setText(tweet.getFrom_user_name());
        holder.content.setText(tweet.getText());
        holder.since.setText(tweet.getCreatedSince(context));

        //Recuperation de l'mage liee au profil
        if(tweet.getImageToDisplay()!=null){
            holder.profile_image.setImageBitmap(tweet.getImageToDisplay());
        }
        else{
            holder.profile_image.setImageDrawable(context.getResources().getDrawable(R.drawable.tweetmixit));
        }


        return convertView;
    }

    static class ViewHolder {
        TextView content;
        TextView userName;
        TextView user;
        TextView since;
        ImageView profile_image;
    }

}
