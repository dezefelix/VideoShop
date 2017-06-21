package com.felixboons.videoshop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomescreenActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout rentFilmButton;
    private LinearLayout returnFilmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        //initialise toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        myToolbar.setTitle("Home");
        setSupportActionBar(myToolbar);

        //initialise views
        rentFilmButton = (LinearLayout) findViewById(R.id.rent_film_button);
        returnFilmButton = (LinearLayout) findViewById(R.id.return_film_button);
        TextView logOutButton = (TextView) findViewById(R.id.logout_button);

        //add listeners
        rentFilmButton.setOnClickListener(this);
        returnFilmButton.setOnClickListener(this);
        logOutButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rent_film_button:
                Intent rentFilmIntent = new Intent(this, FilmOverviewActivity.class);
                rentFilmIntent.putExtra("customer", getIntent().getSerializableExtra("customer"));
                startActivity(rentFilmIntent);
                break;

            case R.id.return_film_button:
                Intent returnFilmIntent = new Intent(this, ReturnFilmActivity.class);
                returnFilmIntent.putExtra("customer", getIntent().getSerializableExtra("customer"));
                startActivity(returnFilmIntent);
                break;

            case R.id.logout_button:
                //clear token
                SharedPreferences tokenPref = getSharedPreferences(LoginActivity.TOKENPREFERENCE, Context.MODE_PRIVATE);
                SharedPreferences.Editor tokenPrefEditor = tokenPref.edit();
                tokenPrefEditor.remove("token");
                tokenPrefEditor.commit();

                //return to log in screen
                Intent i = new Intent(this, LoginActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }
}
