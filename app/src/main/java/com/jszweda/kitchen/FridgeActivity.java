package com.jszweda.kitchen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.jszweda.kitchen.databinding.ActivityFridgeBinding;

import java.util.ArrayList;
import java.util.Comparator;

public class FridgeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Food> foodList;
    FoodAdapter foodAdapter;
    ActivityFridgeBinding binding;
    boolean isDateReversedOrder = false;
    boolean isNameReversedOrder = true;


    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);
        binding = ActivityFridgeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent i = getIntent();
        foodList = i.getParcelableArrayListExtra("foodList");
        foodList.sort(Comparator.comparingLong(Food::getDaysLeft));

        recyclerView = binding.recyclerviewItem;
        foodAdapter = new FoodAdapter(foodList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(foodAdapter);

        Glide.with(this)
                .load(R.drawable.palmy_plaza)
                .into(binding.imageView);

        binding.sortExpDate.setOnClickListener(view -> {
            if (isDateReversedOrder) {
                foodList.sort(Comparator.comparingLong(Food::getDaysLeft));
                isDateReversedOrder = false;
            } else {
                foodList.sort(Comparator.comparingLong(Food::getDaysLeft).reversed());
                isDateReversedOrder = true;
            }
            foodAdapter.notifyDataSetChanged();
        });

        binding.sortName.setOnClickListener(view -> {
            if (isNameReversedOrder) {
                foodList.sort(Comparator.comparing(Food::getFoodName));
                isNameReversedOrder = false;
            } else {
                foodList.sort(Comparator.comparing(Food::getFoodName).reversed());
                isNameReversedOrder = true;
            }
            foodAdapter.notifyDataSetChanged();
        });

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