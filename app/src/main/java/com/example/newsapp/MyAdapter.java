package com.example.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<News> {

    private final Context context;
    int resource;

    public MyAdapter(@NonNull Context context, int resource, ArrayList<News> array) {
        super(context, resource, array);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String caption = getItem(position).getCaptionString();
        String content = getItem(position).getContentString();
        String picpath = getItem(position).getPicturePathString();

        //News news = new News(caption, content, picpath);

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent , false);

        TextView tVcaption = convertView.findViewById(R.id.tV_Caption);
        TextView tVcontent = convertView.findViewById(R.id.tV_Content);
        ImageView imgView = convertView.findViewById(R.id.img_View);

        tVcaption.setText(caption);
        tVcontent.setText(content);
        new DownloadImage(imgView).execute(picpath);

        return convertView;
    }
}























