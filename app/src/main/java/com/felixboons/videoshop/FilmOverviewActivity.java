package com.felixboons.videoshop;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.RequestQueue;

import java.util.ArrayList;


public class FilmOverviewActivity extends AppCompatActivity {

    private FilmAdapter adapter;
    private RequestQueue queue;

    private ArrayList<Film> films = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_overview);

        //initialise views
        ListView filmOverview = (ListView) findViewById(R.id.listview);


        ArrayList<Film> films = new ArrayList<>();
        //testdata----
        films.add(new Film(0, 2007, "Deze fielmpje", "Deze goeie fielmpje was erg leuk. Ik zou hem zo nog een keer niet kijken. Dat wilde ik even mededelen.", 4, 0.99, 107, 21.99, "PG", "No special features."));
        films.add(new Film(0, 2007, "Deze andere gekke fielmpje", "Deze goeie fielmpje was erg leuk. Ik zou hem zo nog een keer niet kijken. Dat wilde ik even mededelen. Deze description moet even wat langer zijn. Op deze manier kan ik zien hoe dit er uit komt te zien in de applicatie. Goed, slecht? Eens even kijken...", 4, 0.99, 107, 21.99, "PG", "No special features."));
        films.add(new Film(0, 2007, "Deine fielmpje met die extra lange titel jwz jwt", "Deze goeie fielmpje was erg leuk. Ik zou hem zo nog een keer niet kijken. Dat wilde ik even mededelen.", 4, 0.99, 107, 21.99, "PG", "No special features."));
        //----

        //initialise & set adapter
        adapter = new FilmAdapter(this, films);
        filmOverview.setAdapter(adapter);

    }

    public void continueToDetail() {

    }
}
