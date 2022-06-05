package com.example.smb116;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Details extends AppCompatActivity {

    // https://api.themoviedb.org/3/movie/{movie_id}?api_key=fc1e00e38c74a357b4c70550778985e7
    private static String JSON_URL_Debut = "https://api.themoviedb.org/3/movie/";
    private static String JSON_URL = "";
    private static String JSON_URL_Suite = "?api_key=fc1e00e38c74a357b4c70550778985e7";
    private String movie_id;

    MovieDetailModelClass movie;
    TextView details_id;
    TextView details_name;
    TextView details_overview;
    ImageView datils_img;

    //List<MovieModelClass> movieList;
    //RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if(extras.getString("movie_id") != null){
            movie_id = extras.getString("movie_id");
        }else{
            movie_id = "338953";
        }
        JSON_URL = JSON_URL_Debut+movie_id+JSON_URL_Suite;
        setContentView(R.layout.movie_details);

        movie = new MovieDetailModelClass();
        details_id = findViewById(R.id.details_id_txt);
        details_name = findViewById(R.id.details_name_txt);
        details_overview = findViewById(R.id.details_overview_txt);
        datils_img = findViewById(R.id.imageView2);

        GetData getData = new GetData();
        getData.execute();
    }

    public class GetData extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... strings) {

            String current = "";

            try{
                URL url;
                HttpURLConnection urlConnection = null;

                try{
                    url = new URL(JSON_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream is = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);

                    int data = isr.read();
                    while(data != -1){
                        current += (char) data;
                        data = isr.read();
                    }
                    return current;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(urlConnection != null){
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s){
            try{
                JSONObject jsonObject = new JSONObject(s);
                MovieDetailModelClass model = new MovieDetailModelClass();
                model.setId(jsonObject.getString("id"));
                model.setName(jsonObject.getString("title"));
                model.setOverview(jsonObject.getString("overview"));
                model.setImg(jsonObject.getString("poster_path"));

                details_id.setText((model.getId()));
                details_name.setText(model.getName());
                details_overview.setText(model.getOverview());
                Glide.with(getApplicationContext()).load("https://image.tmdb.org/t/p/w500"+model.getImg()).into(datils_img);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
