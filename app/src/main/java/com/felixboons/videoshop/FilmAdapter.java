package com.felixboons.videoshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.felixboons.videoshop.Domain.Film;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Felix on 15-6-2017.
 */

public class FilmAdapter extends ArrayAdapter<Film> {

    public FilmAdapter(Context context, ArrayList<Film> films) {
        super(context, 0, films);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Film film = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_film, parent, false);
        }

        //initialise views
        TextView titleOutput = (TextView) convertView.findViewById(R.id.film_title_textview);
        TextView descriptionOutput = (TextView) convertView.findViewById(R.id.film_descr_textview);
        TextView ratingOutput = (TextView) convertView.findViewById(R.id.film_rating_textview);
        TextView lengthOutput = (TextView) convertView.findViewById(R.id.film_length_textview);
        TextView priceOutput = (TextView) convertView.findViewById(R.id.film_price_textview);
        TextView copiesOutput = (TextView) convertView.findViewById(R.id.film_copies_textview);

        //add value to views
        titleOutput.setText(film.getTitle());
        descriptionOutput.setText(String.format(Locale.getDefault(), "%s.", film.getDescription()));
        ratingOutput.setText(film.getRating());
        lengthOutput.setText(film.getLength() + " min.");
        priceOutput.setText("$" + film.getPrice());
        copiesOutput.setText(film.getAmountOfCopies()+" copies");

        return convertView;
    }
}
