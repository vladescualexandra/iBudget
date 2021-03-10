package ro.ase.csie.degree.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Balance implements Serializable {

    private String id;
    private String name;
    private String user;
    private double available_amount;

    public Balance() {
    }

    public Balance(String id, String name, String user, double available_amount) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.available_amount = available_amount;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAvailable_amount() {
        return available_amount;
    }

    public void setAvailable_amount(double available_amount) {
        this.available_amount = available_amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NonNull
    @Override
    public String toString() {
        return this.name + " - " + this.available_amount;
    }

    public boolean operation(TransactionType type, double amount) {
        if (type.equals(TransactionType.EXPENSE)) {
            return withdraw(amount);
        } else {
            return deposit(amount);
        }
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        } else {
            this.available_amount += amount;
            return true;
        }
    }

    public boolean withdraw(double amount) {
        if (amount <= 0 || amount >= this.available_amount) {
            return false;
        } else {
            this.available_amount -= amount;
            return true;
        }
    }
}
