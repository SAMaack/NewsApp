package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


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

                /*Drawable draw = iV.getDrawable();
                Drawable current = draw.getCurrent();
                int[] state = draw.getCurrent().getState();
                Drawable.ConstantState constState = draw.getCurrent().getConstantState();
                intent.putExtra("Picture", (Parcelable) constState);*/

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