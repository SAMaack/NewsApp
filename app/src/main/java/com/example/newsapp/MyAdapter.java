package com.example.newsapp;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    ArrayList<News> newsArr;

    public MyAdapter(ArrayList<News> arr) {
        this.newsArr = arr;
    }

    @Override
    public int getCount() {
        return newsArr.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View.inflate(view.getContext(),R.layout.item_layout, null); //ViewHolder?
        TextView caption = view.findViewById(R.id.tV_Caption);
        TextView content = view.findViewById(R.id.tV_Content);
        TextView picturePath = view.findViewById(R.id.img_View);

        News currentNews = newsArr.get(i);

        caption.setText(currentNews.getCaptionString());
        content.setText(currentNews.getContentString());
        picturePath.setText(currentNews.getPicturePathString());
        return view;
    }
}
