package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    ListView lView;
    Button btnRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "MainActivity created");

        lView = findViewById(R.id.listView);
        lView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int i, long l) {

                Intent intent = new Intent(MainActivity.this, BiggerNews.class);

                TextView tv1 = (TextView) view.findViewById(R.id.tV_card_cap);
                TextView tv2 = (TextView) view.findViewById(R.id.tV_card_con);
                ImageView iV = (ImageView) view.findViewById(R.id.img_card_view);


                intent.putExtra("Caption", tv1.getText().toString());
                intent.putExtra("Content", tv2.getText().toString());


                iV.setDrawingCacheEnabled(true);        //To be able to get the bitmap out of the view.
                Bitmap bmp = iV.getDrawingCache();

                //Source: https://stackoverflow.com/questions/2459524/how-can-i-pass-a-bitmap-object-from-one-activity-to-another
                String fileName = "image";
                try {
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG,100, bytes);
                    FileOutputStream fo = openFileOutput(fileName, Context.MODE_PRIVATE);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                intent.putExtra("Picture", fileName);

                startActivity(intent);
            }
        });

        btnRefresh = findViewById(R.id.btn_refresh);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });

        getData();

    }

    public void getData() {
        new DatabaseActivity(this, lView).execute();
    }//end getData
}