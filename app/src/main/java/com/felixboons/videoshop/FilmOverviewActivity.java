package com.felixboons.videoshop;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.felixboons.videoshop.Domain.Film;
import com.felixboons.videoshop.Volley.MyJSONObjectRequest;
import com.felixboons.videoshop.Volley.MyVolleyRequestQueue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FilmOverviewActivity extends AppCompatActivity implements ListView.OnItemClickListener,
        Response.ErrorListener, Response.Listener<JSONObject> {

    private FilmAdapter adapter;
    private ArrayList<Film> films = new ArrayList<>();
    private Film f;

    public static final String TOKENPREFERENCE = "TOKEN";

    private RequestQueue queue;

    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_overview);

        //initialise toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        myToolbar.setTitle("Film Overview");
        setSupportActionBar(myToolbar);

        //initialise views
        ListView filmListview = (ListView) findViewById(R.id.listview);


        films = new ArrayList<>();

        //initialise queue
        queue = MyVolleyRequestQueue.getInstance(this.getApplicationContext()).getRequestQueue();

        //initialise & set adapter
        adapter = new FilmAdapter(this, films);
        filmListview.setAdapter(adapter);

        //set listener
        filmListview.setOnItemClickListener(this);

        showProgressDialog();
        sendGetFilmRequest();
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
        String getFilmsURL = "https://video-shop-server.herokuapp.com/api/v1/films";
        final MyJSONObjectRequest req = new MyJSONObjectRequest(
                Request.Method.GET,
                getFilmsURL,
                null,
                this,
                this,
                this
        );
        queue.add(req);
    }

    //create JSON body
    public JSONObject createBody() throws JSONException {

        //get token from SharedPreference
        SharedPreferences tokenPref = getSharedPreferences(TOKENPREFERENCE, Context.MODE_PRIVATE);
        String token = tokenPref.getString("token", "");

        //create payload
        JSONObject payload = new JSONObject();
        payload.put("Auth", token);
        return payload;
    }

    public void sendGetCopyRequest(int filmID) throws JSONException {
        String getFilmsURL = "https://video-shop-server.herokuapp.com/api/v1/getcopies/" + filmID;
        final MyJSONObjectRequest req = new MyJSONObjectRequest(
                Request.Method.GET,
                getFilmsURL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray copyArray = response.getJSONArray("copies");
                            int amountOfCopies = copyArray.getJSONObject(0).optInt("Amount");
                            f.setAmountOfCopies(amountOfCopies);
                            films.add(f);
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(FilmOverviewActivity.this, "Copies weren't found", Toast.LENGTH_SHORT).show();
                    }
                }
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

                f = new Film(id, title, description, releaseYear, rentalDuration,
                        price, length, replacementCost, rating, specialFeatures);

                sendGetCopyRequest(id);
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
    }
}
