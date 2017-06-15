package com.felixboons.videoshop;

import java.io.Serializable;

/**
 * Created by Felix on 15-6-2017.
 */

public class Film implements Serializable {
    private int filmId, releaseYear, rentalDuration, length;
    private String title, description, rating, specialFeatures;
    private double price, replacementCost;

    public Film(int filmId, String title, String description, int releaseYear, int rentalDuration, double price, int length, double replacementCost, String rating, String specialFeatures) {
        this.filmId = filmId;
        this.releaseYear = releaseYear;
        this.title = title;
        this.description = description;
        this.rentalDuration = rentalDuration;
        this.price = price;
        this.length = length;
        this.replacementCost = replacementCost;
        this.rating = rating;
        this.specialFeatures = specialFeatures;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(int rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public double getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(double replacementCost) {
        this.replacementCost = replacementCost;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(String specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    @Override
    public String toString() {
        return "Film{" +
                "filmId=" + filmId +
                ", releaseYear=" + releaseYear +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", rentalDuration='" + rentalDuration + '\'' +
                ", price='" + price + '\'' +
                ", length='" + length + '\'' +
                ", replacementCost='" + replacementCost + '\'' +
                ", rating='" + rating + '\'' +
                ", specialFeatures='" + specialFeatures + '\'' +
                '}';
    }
}
