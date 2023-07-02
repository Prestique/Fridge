package com.jszweda.kitchen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.time.LocalDate;

public class FridgeActivity extends AppCompatActivity {

    ImageView img;
    RecyclerView recyclerView;
    Food[] foodList;
    FoodAdapter foodAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);

        img = findViewById(R.id.imageView);
        recyclerView = findViewById(R.id.recyclerview_item);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            foodList = new Food[]{
                    new Food("Mięso wołowe", LocalDate.of(2023, 12,12), 2, 500),
                    new Food("Woda źródlana", LocalDate.of(2024, 11,1), 5, 5000),
                    new Food("Frytki", LocalDate.of(2024, 11,1), 2, 2),
                    new Food("Sok", LocalDate.of(2026, 11,1), 6, 1),
                    new Food("Sok", LocalDate.of(2026, 11,1), 6, 1),
                    new Food("Sok", LocalDate.of(2026, 11,1), 6, 5000),
                    new Food("Mięso wołowe", LocalDate.of(2026, 11,1), 6, 1),
                    new Food("Sok", LocalDate.of(2026, 11,1), 6, 1),
                    new Food("Sok", LocalDate.of(2026, 11,1), 6, 1),
            };
        }
        foodAdapter = new FoodAdapter(foodList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(foodAdapter);


//        img.setImageResource(R.drawable.palmy_plaza);

        Glide.with(this)
                .load(R.drawable.palmy_plaza)
                .into(img);

    }

    @Override
    protected void onStop() {
        super.onStop();
        Glide.with(this).clear(img);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}