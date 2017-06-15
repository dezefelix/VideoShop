package com.felixboons.videoshop;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Felix on 15-6-2017.
 */

public class FilmAdapter {

    public FilmAdapter(Context context, ArrayList<Film> films) {
        super(context, 0, films);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Film film = getItem(position);

        long dateInMillis = film.getDate().getTime() + new Double(2.16e+7).longValue();
        Date date = new Date(dateInMillis);

        //create an order item
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.order_overview_list_item, parent, false);
        }

        //initialise views
        TextView orderNumberOutput = (TextView) convertView.findViewById(R.id.order_number_textview);
        TextView orderLocationOutput = (TextView) convertView.findViewById(R.id.order_location_textview);
        TextView orderDateOutput = (TextView) convertView.findViewById(R.id.order_date_textview);
        checkbox = (CheckBox) convertView.findViewById(R.id.status_checkbox);
        xCheckbox = (ImageView) convertView.findViewById(R.id.status_imageview);

        //add value to views

        return convertView;
    }

    public String formatDate(Date date) {
        //format the date
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm - dd/MM/yyyy");
        return sdf.format(date);
    }

}
