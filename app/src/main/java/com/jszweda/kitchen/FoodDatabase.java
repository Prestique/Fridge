package com.jszweda.kitchen;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Food.class}, version = 1)
@TypeConverters(ConverterLocalDate.class)
public abstract class FoodDatabase extends RoomDatabase {
   public abstract FoodDAO getFoodDAO();
   private static FoodDatabase dbInstance;

   public static synchronized FoodDatabase getInstance(Context context){
      if (dbInstance == null) {
         dbInstance = Room.databaseBuilder(
                 context.getApplicationContext(),
                 FoodDatabase.class,
                 "food_db"
         ).fallbackToDestructiveMigration().build();
      }
      return dbInstance;
   }

}
