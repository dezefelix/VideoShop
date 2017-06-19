package com.felixboons.videoshop.Domain;

import java.io.Serializable;

/**
 * Created by Felix on 19-6-2017.
 */

public class Customer implements Serializable {

    private int customerId;

    public Customer(int customerId) {
        this.customerId = customerId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                '}';
    }
}
