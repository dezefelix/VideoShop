package com.felixboons.videoshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

public class HomescreenActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout rentFilmButton;
    private LinearLayout returnFilmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        myToolbar.setTitle("Home");
        setSupportActionBar(myToolbar);

        rentFilmButton = (LinearLayout) findViewById(R.id.rent_film_button);
        rentFilmButton.setOnClickListener(this);

        returnFilmButton = (LinearLayout) findViewById(R.id.return_film_button);
        returnFilmButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rent_film_button:
                Intent rentFilmIntent = new Intent(this, FilmOverviewActivity.class);
                startActivity(rentFilmIntent);
                break;
            case R.id.return_film_button:
                Intent returnFilmIntent = new Intent(this, ReturnFilmActivity.class);
                startActivity(returnFilmIntent);
        }
    }
}
