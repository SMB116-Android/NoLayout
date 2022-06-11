package com.example.smb116;

import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FinderActivity extends AppCompatActivity {

    String URL_JSON = "https://api.themoviedb.org/3/search/movie?api_key=fc1e00e38c74a357b4c70550778985e7&query=";
    String TitleToSearch = "";
    String Full_Url;
    EditText test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finder);
    }

    //searchByTitle
    public void searchByTitle(View view) {
        test = findViewById(R.id.editTextTextPersonName);
        TitleToSearch = test.getText().toString();
        Full_Url = URL_JSON+TitleToSearch;
        Intent i = new Intent(this, FinderResultActivity.class);
        i.putExtra("movie_title",Full_Url);
        Log.d("AUBO", "LAAAAAAAAAAA"+Full_Url);
        startActivity(i);
    }
}
