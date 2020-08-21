package com.example.newsapp;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class DatabaseActivity extends AsyncTask<String, Void, String> {

    private static final String TAG = "DatabaseActivity";

    private ListView lView;  //Leaks context???
    Context context;
    MyAdapter adapter;

    public DatabaseActivity(Context con, ListView lv) {
        this.lView = lv;
        this.context = con;

    }

    /////////////////////////////////////////////////

    public static final String POST_PARAM_KEYVALUE_SEPARATOR = "=";
    public static final String POST_PARAM_SEPARATOR = "&";
    public static String DESTINATION_METHOD = "allEntrys";

    private URLConnection conn;

    private void openConnection() throws IOException {
        StringBuffer urlDataBuffer = new StringBuffer();

        /*urlDataBuffer.append(URLEncoder.encode("method", "UTF-8"));
        urlDataBuffer.append(POST_PARAM_KEYVALUE_SEPARATOR);
        urlDataBuffer.append(URLEncoder.encode(DESTINATION_METHOD, "UTF-8"));*/


        URL url = new URL("http://10.33.11.5/PHPServerEinkaufsliste/News.php");

        conn = url.openConnection();
        conn.setDoOutput(true);

        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

        wr.write(urlDataBuffer.toString());
        wr.flush();
    }

    /////////////////////////////////////////////////

    @Override
    protected String doInBackground(String... params) {

        try {
            openConnection();
            return readResult();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String readResult() throws IOException {
        String result = null;

        //Lesen der Rückgabewerte vom Server
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = null;

        //Solange Daten bereitstehen werden diese gelesen.
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    @Override
    protected void onPostExecute(String result){
        //JSON WEITERGEBEN ODER HIER VERWERTEN?

        if(!isBlank(result)) {
            ArrayList<News> newsArr = new ArrayList<News>(); //Contains the classes

            try {
                //JSON TO NEWS
                JSONArray jsonArr = new JSONArray(result);

                for (int i = jsonArr.length(); i > 0; i--)
                {
                    JSONObject articleAsJson = jsonArr.getJSONObject(i-1);

                    // Pulls items from the array, generates new News objects with those items and then pushes them into an array.
                    newsArr.add(new News(articleAsJson.getString("Caption"),
                            articleAsJson.getString("Content"),
                            articleAsJson.getString("PictureAddress")));
                }

                this.adapter = new MyAdapter(context, R.layout.item_layout, newsArr);
                lView.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }//END if(!isBlank())

    }//END onPostExecute

    private boolean isBlank(String value){

        return value == null || value.trim().isEmpty();
    }

}//end DatabaseActivity
