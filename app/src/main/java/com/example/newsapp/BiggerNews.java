package com.example.newsapp;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;

public class BiggerNews extends AppCompatActivity {
    Button btn_close;
    TextView tv_cap_big;
    TextView tv_con_big;
    ImageView iv_pp_Big;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bigger_news);

        //Parameters
        Intent in = this.getIntent();

        //Caption
        this.tv_cap_big = findViewById(R.id.tV_cap_bignews);
        tv_cap_big.setText(in.getStringExtra("Caption"));

        //Content
        this.tv_con_big = findViewById(R.id.tV_con_bignews);
        tv_con_big.setText(in.getStringExtra("Content"));


        this.iv_pp_Big = findViewById(R.id.img_view_bignews);
        try {
            Bitmap bmp = BitmapFactory.decodeStream(openFileInput(in.getStringExtra("Picture"))); //Grabs compressed resource by stored name inside "picture" and decodes it.
            iv_pp_Big.setImageBitmap(bmp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



        btn_close = findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BiggerNews.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
