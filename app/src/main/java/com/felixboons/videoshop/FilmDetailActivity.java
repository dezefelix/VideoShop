package com.felixboons.videoshop;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.felixboons.videoshop.Domain.Film;

import java.util.Locale;

public class FilmDetailActivity extends AppCompatActivity {

    private Film film;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);

        //get intent values
        film = (Film) getIntent().getSerializableExtra("film");
        Log.i(this.getClass().getSimpleName(), "Film: " + film);

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
        descriptionOutput.setText(film.getDescription());
        rentalDurationOutput.setText(String.format(Locale.getDefault(), "Max rental duration: %d days", film.getRentalDuration()));
        replacementCostOutput.setText(String.format(Locale.getDefault(), "Replacement cost: $%.2f", film.getReplacementCost()));
    }

    //parse special features information
    public void inflateFeatureView(TextView v) {
        //set "include:" view visible
        v.setVisibility(View.VISIBLE);

        //parse features if there is > 1
        String[] features = new String[4];
        if (film.getSpecialFeatures().contains(".")) {
            features = film.getSpecialFeatures().split("\\.");
        } else {
            features[0] = film.getSpecialFeatures();
        }

        //show special feature view(s) based on how many features there are
        for (int i = 0; i < features.length; i++) {
            //initialise view
            TextView featureOuput;
            switch (i) {
                case 0:
                    featureOuput = (TextView) findViewById(R.id.film_special_feature_item_textview1);
                    break;
                case 1:
                    featureOuput = (TextView) findViewById(R.id.film_special_feature_item_textview2);
                    break;
                default:
                    featureOuput = (TextView) findViewById(R.id.film_special_feature_item_textview1);
            }
            featureOuput.setText(features[i]);
            featureOuput.setVisibility(View.VISIBLE);
            Log.i("FEATURES", features[i]);
        }
    }
}
