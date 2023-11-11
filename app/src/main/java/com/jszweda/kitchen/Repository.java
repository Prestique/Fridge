package com.jszweda.kitchen;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;

class Repository {
   private final FoodDAO foodDAO;
   public ExecutorService executor;
   public Handler handler;

   public Repository(Application application) {
      FoodDatabase foodDatabase = FoodDatabase.getInstance(application);
      this.foodDAO = foodDatabase.getFoodDAO();
      executor = Executors.newSingleThreadExecutor();
      handler = new Handler(Looper.getMainLooper());
   }

   public void addFood(Food food) {
      executor.execute(new Runnable() {
         @Override
         public void run() {
            foodDAO.insert(food);
         }
      });
   }

   public void deleteFood(Food food){
      executor.execute(new Runnable() {
         @Override
         public void run() {
            foodDAO.delete(food);
         }
      });
   }

   public void updateFood(Food food){
      executor.execute(new Runnable() {
         @Override
         public void run() {
            foodDAO.update(food);
         }
      });
   }

   public LiveData<List<Food>> getAllFoods(){
      return foodDAO.getAll();
   }

   public Food getFood(long id){
      return foodDAO.getFood(id);
   }



}
