package com.example.newsapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;


import java.io.InputStream;
//Source https://medium.com/@crossphd/android-image-loading-from-a-string-url-6c8290b82c5e
public class DownloadImage extends AsyncTask<String, Void, Bitmap> {



    ImageView imgView;

    public DownloadImage(ImageView imgView) {
        this.imgView = imgView;
    }

    protected Bitmap doInBackground(String... urls) {
        String url = "http://10.33.11.5" + urls[0];
        Bitmap img = null;

        try {
            InputStream in = new java.net.URL(url).openStream();
            img = BitmapFactory.decodeStream(in);
        }catch (Exception e)
        {
            Log.e("Error:", e.getMessage());
            e.printStackTrace();
        }
        return img;
    }

    protected void onPostExecute(Bitmap result) {
        imgView.setImageBitmap(result);

    }
}
