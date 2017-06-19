package com.felixboons.videoshop;

<<<<<<< HEAD
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
=======
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
>>>>>>> origin/development
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.felixboons.videoshop.Domain.Customer;
import com.felixboons.videoshop.Domain.Film;
import com.felixboons.videoshop.Volley.MyJSONObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class FilmDetailActivity extends AppCompatActivity implements View.OnClickListener, Response.ErrorListener, Response.Listener<JSONObject> {

    private Film film;
    private Customer customer;
    public static final String TOKENPREFERENCE = "TOKEN";
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);

        //get intent values
        film = (Film) getIntent().getSerializableExtra("film");
        customer = (Customer) getIntent().getSerializableExtra("customer");
        fillViews();
    }

    public void fillViews() {
        //initialise views
        TextView titleOutput = (TextView) findViewById(R.id.film_title_textview);
        TextView ratingOutput = (TextView) findViewById(R.id.film_rating_textview);
        TextView releaseYearOutput = (TextView) findViewById(R.id.film_release_year_textview);
        TextView lengthOutput = (TextView) findViewById(R.id.film_length_textview);
        TextView priceOutput = (TextView) findViewById(R.id.film_price_textview);
        TextView specialFeaturesOutput = (TextView) findViewById(R.id.film_special_features_textview);
        TextView descriptionOutput = (TextView) findViewById(R.id.film_descr_textview);
        TextView rentalDurationOutput = (TextView) findViewById(R.id.film_rental_duration_textview);
        TextView replacementCostOutput = (TextView) findViewById(R.id.film_rental_cost_textview);

        //fill views
        titleOutput.setText(film.getTitle());
        ratingOutput.setText(film.getRating());
        releaseYearOutput.setText(Integer.toString(film.getReleaseYear()));
        lengthOutput.setText(String.format(Locale.getDefault(), "%d min.", film.getLength()));
        priceOutput.setText(String.format(Locale.getDefault(), "$%.2f", film.getPrice()));
        if (film.getSpecialFeatures() != null) {
            inflateFeatureView(specialFeaturesOutput);
        }
        descriptionOutput.setText(String.format(Locale.getDefault(), "%s.", film.getDescription()));
        rentalDurationOutput.setText(String.format(Locale.getDefault(), "Max rental duration: %d days", film.getRentalDuration()));
        replacementCostOutput.setText(String.format(Locale.getDefault(), "Replacement cost: $%.2f", film.getReplacementCost()));


    }

    //parse special features information
    public void inflateFeatureView(TextView v) {
        //set "include:" view visible
        v.setVisibility(View.VISIBLE);

        //parse features if there is > 1
        String[] features = new String[4];
        if (film.getSpecialFeatures().contains(",")) {
            features = film.getSpecialFeatures().split("\\,");
        } else {
            features[0] = film.getSpecialFeatures();
        }

        //show special feature view(s) based on how many features there are
        for (int i = 0; i < features.length; i++) {
            //initialise view
            TextView featureOutput;
            switch (i) {
                case 0:
                    featureOutput = (TextView) findViewById(R.id.film_special_feature_item_textview1);
                    break;
                case 1:
                    featureOutput = (TextView) findViewById(R.id.film_special_feature_item_textview2);
                    break;
                default:
                    featureOutput = (TextView) findViewById(R.id.film_special_feature_item_textview1);
            }
<<<<<<< HEAD
            featureOuput.setText(String.format(Locale.getDefault(), "* %s", features[i]));

            featureOuput.setVisibility(View.VISIBLE);
=======
            featureOutput.setText(String.format(Locale.getDefault(), "* %s", features[i]));
            featureOutput.setVisibility(View.VISIBLE);
>>>>>>> origin/development
        }
    }

    public void sendRentFilmRequest() throws JSONException {
        String rentFilmURL = "https://video-shop-server.herokuapp.com/api/v1/rentals/" + customer.getCustomerId() + "/" + film.getInventoryID();
        final MyJSONObjectRequest req = new MyJSONObjectRequest(
                Request.Method.POST,
                rentFilmURL,
                createBody(),
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rent_film_button:
                try {
                    sendRentFilmRequest();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {

    }
}
