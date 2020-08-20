package com.example.newsapp;

import java.io.Serializable;

public class News implements Serializable {
    private String captionString;
    private String contentString;
    private String picturePathString;

    public News(String cap, String con, String pic) {
        this.captionString = cap;
        this.contentString = con;
        this.picturePathString = pic;
    }

    public String getCaptionString() {
        return captionString;
    }

    public String getContentString() {
        return contentString;
    }

    public String getPicturePathString() {
        return picturePathString;
    }
}//end News