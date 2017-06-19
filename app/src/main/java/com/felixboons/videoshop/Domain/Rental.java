package com.felixboons.videoshop.Domain;

import java.util.Date;

/**
 * Created by Felix on 19-6-2017.
 */

public class Rental {
    private int rentalId, customerId, inventoryId, rentalDuration;
    private Date rentalDate, returnDate;
    private String title;

    public Rental(int rentalId, int customerId, int inventoryId, int rentalDuration, Date rentalDate, Date returnDate, String title) {
        this.rentalId = rentalId;
        this.customerId = customerId;
        this.inventoryId = inventoryId;
        this.rentalDuration = rentalDuration;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.title = title;
    }

    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(int rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "rentalId=" + rentalId +
                ", customerId=" + customerId +
                ", inventoryId=" + inventoryId +
                ", rentalDuration=" + rentalDuration +
                ", rentalDate=" + rentalDate +
                ", returnDate=" + returnDate +
                ", title='" + title + '\'' +
                '}';
    }
}