package com.jszweda.kitchen;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

class Food implements Comparable<Food> {
    protected LocalDate expirationDate;
    protected String foodName;
    protected int quantity;
    protected int weight;

    private Food(){}
    protected Food(String foodName, LocalDate expirationDate){
        this.foodName = foodName;
        this.expirationDate = expirationDate;
    }
    protected Food(String foodName, LocalDate expirationDate, int quantity){
        this.foodName = foodName;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
    }
    protected Food(String foodName, LocalDate expirationDate, int quantity, int weight){
        this.foodName = foodName;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
        this.weight = weight;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return quantity == food.quantity && weight == food.weight && Objects.equals(expirationDate, food.expirationDate) && Objects.equals(foodName, food.foodName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expirationDate, foodName, quantity, weight);
    }

    @Override
    public int compareTo(Food o) {
        return 0;
    }
}
