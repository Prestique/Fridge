package com.jszweda.kitchen;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
interface FoodDAO {

 @Insert
 void insert(Food food);
 @Delete
 void delete(Food food);
 @Update
 void update(Food food);
 @Query("SELECT * FROM food_table")
 LiveData<List<Food>> getAll();
 @Query("SELECT * FROM food_table WHERE id ==:id")
 Food getFood(long id);
}
