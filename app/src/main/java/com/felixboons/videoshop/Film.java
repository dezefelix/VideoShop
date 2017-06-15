package com.felixboons.videoshop;

/**
 * Created by Felix on 15-6-2017.
 */

public class Film {
    private int filmId, releaseYear;
    private String title, description, rentalDuration, price, length,
            replacementCost, rating, specialFeatures;

    public Film(int filmId, int releaseYear, String title, String description, String rentalDuration, String price, String length, String replacementCost, String rating, String specialFeatures) {
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

    public String getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(String rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(String replacementCost) {
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
