package com.jszweda.kitchen;

import android.os.Build;

import java.time.LocalDate;
import java.util.Objects;

class Food implements Comparable<Food> {
    protected LocalDate expirationDate;
    protected String foodName;
    protected int pieces = 0;
    protected int quantity = 0;

    private Food(){}
    protected Food(String foodName, LocalDate expirationDate){
        this.foodName = foodName;
        this.expirationDate = expirationDate;
    }
    protected Food(String foodName, LocalDate expirationDate, int pieces){
        this.foodName = foodName;
        this.expirationDate = expirationDate;
        this.pieces = pieces;
    }
    protected Food(String foodName, LocalDate expirationDate, int pieces, int quantity){
        this.foodName = foodName;
        this.expirationDate = expirationDate;
        this.pieces = pieces;
        this.quantity = quantity;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return pieces == food.pieces && quantity == food.quantity && Objects.equals(expirationDate, food.expirationDate) && Objects.equals(foodName, food.foodName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expirationDate, foodName, pieces, quantity);
    }

    @Override
    public int compareTo(Food o) {
        Food other = (Food) o;
        int resultsName = this.getFoodName().compareToIgnoreCase(other.getFoodName());
        if ( resultsName != 0 ){
            return resultsName;
        }
        int resultsWeight = Integer.compare(this.getQuantity(), other.getQuantity());
        if (resultsWeight != 0) {
            return resultsWeight;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return this.getExpirationDate().compareTo(other.getExpirationDate());
        }
        return resultsWeight;
    }
}
