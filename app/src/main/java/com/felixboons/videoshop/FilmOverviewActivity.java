package com.felixboons.videoshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
<<<<<<< HEAD
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
=======
import android.support.v7.widget.Toolbar;
>>>>>>> master

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.felixboons.videoshop.Domain.Film;
import com.felixboons.videoshop.Volley.MyVolleyRequestQueue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class FilmOverviewActivity extends AppCompatActivity implements ListView.OnItemClickListener,
        Response.ErrorListener, Response.Listener<JSONObject> {

    private FilmAdapter adapter;
    private ArrayList<Film> films = new ArrayList<>();

    private RequestQueue queue;

    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_overview);

<<<<<<< HEAD
        //initialise views
        ListView filmListview = (ListView) findViewById(R.id.listview);


        films = new ArrayList<>();
        //testdata----
        films.add(new Film(0, "Deze fielmpje", "Deze goeie fielmpje was erg leuk. Ik zou hem zo nog een keer niet kijken. Dat wilde ik even mededelen.", 2007, 4, 0.99, 107, 21.99, "PG", null));
        films.add(new Film(0, "Deze fielmpje", "Deze goeie fielmpje was erg leuk. Ik zou hem zo nog een keer niet kijken. Dat wilde ik even mededelen.", 2007, 4, 0.99, 107, 21.99, "PG", "No special features."));
        films.add(new Film(0, "Deze andere gekke fielmpje", "Deze goeie fielmpje was erg leuk. Ik zou hem zo nog een keer niet kijken. Dat wilde ik even mededelen. Deze description moet even wat langer zijn. Op deze manier kan ik zien hoe dit er uit komt te zien in de applicatie. Goed, slecht? Eens even kijken...", 2007, 4, 0.99, 107, 21.99, "PG", "No special features."));
        films.add(new Film(0, "Deine fielmpje met die extra lange titel jwz jwt", "Deze goeie fielmpje was erg leuk. Ik zou hem zo nog een keer niet kijken. Dat wilde ik even mededelen.", 2007, 4, 0.99, 107, 21.99, "PG", "Behind the scenes.Extra footage"));
        //----

        //initialise queue
        queue = MyVolleyRequestQueue.getInstance(this.getApplicationContext()).getRequestQueue();

        //initialise & set adapter
        adapter = new FilmAdapter(this, films);
        filmListview.setAdapter(adapter);

        //set listener
        filmListview.setOnItemClickListener(this);

        showProgressDialog();
//        sendGetFilmRequest();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Film film = films.get(position);
        Log.i(this.getClass().getSimpleName(), "Film: " + film);

        //continue to detail screen
        Intent i = new Intent(this, FilmDetailActivity.class);
        i.putExtra("film", film);
        startActivity(i);
    }

    public void sendGetFilmRequest() {
        String getFilmsURL = "";
        final JsonObjectRequest req = new JsonObjectRequest(
                Request.Method.GET,
                getFilmsURL,
                null,
                this,
                this
        );
        queue.add(req);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            films.clear();
            JSONArray filmsArray = response.getJSONArray("films");

            for (int i = 0; i < filmsArray.length(); i++) {
                JSONObject film = filmsArray.getJSONObject(i);
                int id = film.optInt("film_id");
                String title = film.optString("title");
                String description = film.optString("description");
                int releaseYear = film.optInt("release_year");
                int rentalDuration = film.optInt("rental_duration");
                double price = film.optDouble("rental_rate");
                int length = film.optInt("length");
                double replacementCost = film.optDouble("replacement_cost");
                String rating = film.optString("rating");
                String specialFeatures = film.optString("special_features");

                Film f = new Film(id, title, description, releaseYear, rentalDuration,
                        price, length, replacementCost, rating, specialFeatures);

                this.films.add(f);

                adapter.notifyDataSetChanged();
            }

            pd.cancel();
        } catch (JSONException e) {

            e.printStackTrace();
        }
    }

    //show ProgressDialog while retrieving film data
    public void showProgressDialog() {
        pd = new ProgressDialog(this);
        pd.setMessage("Retrieving data...");
        pd.show();
=======
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        myToolbar.setTitle("Film Overview");
        setSupportActionBar(myToolbar);
>>>>>>> master
    }
}
