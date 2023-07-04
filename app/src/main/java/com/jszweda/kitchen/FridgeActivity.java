package com.jszweda.kitchen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jszweda.kitchen.databinding.ActivityFridgeBinding;
import com.jszweda.kitchen.databinding.ActivityMainBinding;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class FridgeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Food[] foodList;
    FoodAdapter foodAdapter;
    ActivityFridgeBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);
        binding = ActivityFridgeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent i = getIntent();
        ArrayList<Food> foodList = i.getParcelableArrayListExtra("foodList");
        Food[] foodArray = new Food[foodList.size()];
        foodList.toArray(foodArray);

        recyclerView = findViewById(R.id.recyclerview_item);
        foodAdapter = new FoodAdapter(foodArray);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(foodAdapter);


//        img.setImageResource(R.drawable.palmy_plaza);

        Glide.with(this)
                .load(R.drawable.palmy_plaza)
                .into(binding.imageView);

    }

    @Override
    protected void onStop() {
        super.onStop();
        Glide.with(this).clear(binding.imageView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}