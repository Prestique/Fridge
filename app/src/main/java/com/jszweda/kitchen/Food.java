package com.jszweda.kitchen;


import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "food_table")
public class Food implements Comparable<Food>, Parcelable {
    @ColumnInfo(name = "expiration_date")
    protected LocalDate expirationDate;
    @ColumnInfo(name = "food_name")
    protected String foodName;
    @ColumnInfo(name = "weight")
    protected int weight = 0;
    @ColumnInfo(name = "quantity")
    protected int quantity = 0;
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private long id;

    @Ignore
    private Food(){}
    @Ignore
    protected Food(String foodName, LocalDate expirationDate){
        this.foodName = foodName;
        this.expirationDate = expirationDate;
    }
    @Ignore
    protected Food(String foodName, LocalDate expirationDate, int weight){
        this.foodName = foodName;
        this.expirationDate = expirationDate;
        this.weight = weight;
    }
    protected Food(String foodName, LocalDate expirationDate, int weight, int quantity){
        this.foodName = foodName.substring(0,1).toUpperCase().concat(foodName.substring(1).toLowerCase());
        this.expirationDate = expirationDate;
        this.weight = weight;
        this.quantity = quantity;
    }

    public long getDaysLeft(){
        LocalDate today = LocalDate.now();
        long diff = ChronoUnit.DAYS.between(today, expirationDate);
        return diff;
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
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
        return weight == food.weight && quantity == food.quantity && Objects.equals(expirationDate, food.expirationDate) && Objects.equals(foodName, food.foodName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expirationDate, foodName, weight, quantity);
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

        return this.getExpirationDate().compareTo(other.getExpirationDate());
    }

    protected Food(Parcel in) {
        expirationDate = LocalDate.parse(in.readString());
        foodName = in.readString();
        weight = in.readInt();
        quantity = in.readInt();
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int flags) {
        parcel.writeString(expirationDate.toString());
        parcel.writeString(foodName);
        parcel.writeInt(weight);
        parcel.writeInt(quantity);
    }

    public String getDateAsString(){
        if (expirationDate == null) {
            return "date is null";
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d-M-y");
        String date = dtf.format(expirationDate);
        return date;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("Nazwa: %s, Ważność: %s, waga: %d, ilość: %d",foodName, expirationDate.toString(), weight, quantity);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
