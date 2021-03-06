package com.felixboons.videoshop.Domain;

import java.io.Serializable;

/**
 * Created by Felix on 15-6-2017.
 */

public class Film implements Serializable {
    private int filmId;
    private int releaseYear;
    private int rentalDuration;
    private int length;
    private int amountOfCopies;
    private int inventoryID;
    private String title, description, rating, specialFeatures;
    private double price, replacementCost;

    public Film(int filmId, String title, String description, int releaseYear, int rentalDuration, int amountOfCopies, double price, int length, double replacementCost, String rating, String specialFeatures) {
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
        this.amountOfCopies = amountOfCopies;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public int getInventoryID() {
        return inventoryID;
    }

    public void setInventoryID(int inventoryID) {
        this.inventoryID = inventoryID;
    }

    public void setAmountOfCopies(int amountOfCopies) {
        this.amountOfCopies = amountOfCopies;
    }

    public int getAmountOfCopies() {
        return amountOfCopies;
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
                ", rentalDuration=" + rentalDuration +
                ", length=" + length +
                ", amountOfCopies=" + amountOfCopies +
                ", inventoryID=" + inventoryID +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", rating='" + rating + '\'' +
                ", specialFeatures='" + specialFeatures + '\'' +
                ", price=" + price +
                ", replacementCost=" + replacementCost +
                '}';
    }
}
