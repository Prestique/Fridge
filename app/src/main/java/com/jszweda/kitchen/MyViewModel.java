package com.jszweda.kitchen;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MyViewModel extends AndroidViewModel {

   private Repository repository;
   LiveData<List<Food>> allFoods;

   public MyViewModel(@NonNull Application application) {
      super(application);
      this.repository = new Repository(application);
//      allFoods = repository.getAllFoods();
   }

   public LiveData<List<Food>> getAllFoods(){
      allFoods = repository.getAllFoods();
      return allFoods;
//      return repository.getAllFoods();
   }

   public void addFood(Food food){
      repository.addFood(food);
   }
   public void deleteFood(Food food){
      repository.deleteFood(food);
   }
   public void updateFood(Food food){
      repository.updateFood(food);
   }
   public Food getFood(long id){
      return repository.getFood(id);
   }


}
