package com.jszweda.kitchen;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.time.LocalDate;
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
      //used for background database operation
      executor = Executors.newSingleThreadExecutor();
      //for updating UI
      handler = new Handler(Looper.getMainLooper());
   }

   public void addFood(Food food) {
      executor.execute(new Runnable() {
         @Override
         public void run() {
            foodDAO.insert(food);
            Log.e("FOOD", "Food added to the database");
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
//            Food existingFood = foodDAO.getFood(foodId); // Pobierz obiekt z bazy danych
//            existingFood.setFoodName(newName);
//            existingFood.setExpirationDate(newExpDate);
//            existingFood.setWeight(newWeight);
//            existingFood.setQuantity(newQuantity);
            foodDAO.update(food);
            Log.d("Test","Update food");
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
