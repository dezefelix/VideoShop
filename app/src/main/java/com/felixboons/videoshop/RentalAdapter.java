package com.felixboons.videoshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.felixboons.videoshop.Domain.Rental;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Felix on 15-6-2017.
 */

public class RentalAdapter extends ArrayAdapter<Rental> {

    private String TAG = this.getClass().getSimpleName();

    public RentalAdapter(Context context, ArrayList<Rental> rentals) {
        super(context, 0, rentals);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Rental rental = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_rental, parent, false);
        }

        //initialise views
        TextView titleOutput = (TextView) convertView.findViewById(R.id.film_title_textview);
        TextView rentalDateOutput = (TextView) convertView.findViewById(R.id.rental_date_textview);
        TextView rentalDurationLeftOutput = (TextView) convertView.findViewById(R.id.rental_duration_left_textview);

        //add value to views
        titleOutput.setText(rental.getTitle());
        rentalDateOutput.setText(String.format(Locale.getDefault(), "Film rented at: %s", formatDate(rental.getRentalDate())));
        rentalDurationLeftOutput.setText(String.format(Locale.getDefault(), "Days left to return this film: %d", calcRentalDaysLeft(rental.getRentalDuration(), rental.getRentalDate())));
        return convertView;
    }

    //calculate the days that are left to return the film.
    //rental date - current time
    public int calcRentalDaysLeft(int totalDays, Date date) {
        //create current date
        Date currentDate = new Date();
        //calc difference between rental date and current date (in milliseconds)
        long diff = Math.abs(currentDate.getTime() - date.getTime());
        //convert milliseconds to days
        int days = (int) (diff / 1000 / 60 / 60 / 24);
        //return days that are remaining to return this film
        return totalDays - days;
    }

    public String formatDate(Date date) {
        //format the date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }
}
