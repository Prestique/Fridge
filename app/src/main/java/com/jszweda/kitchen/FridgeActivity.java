package com.jszweda.kitchen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

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

        foodAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                showEditPopup();
                Toast.makeText(getApplicationContext(), "hello " + foodList.get(position).getFoodName()  ,Toast.LENGTH_SHORT).show();
            }
        });

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

    private void showEditPopup() {
        LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
        View view = layoutInflater.inflate(R.layout.selected_item, null);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setView(view);
        alertDialog.setPositiveButton("Zapisz", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Zapisz " ,Toast.LENGTH_SHORT).show();

            }
        });
        alertDialog.setNegativeButton("Usuń", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Usuń " ,Toast.LENGTH_SHORT).show();
            }
        });

        alertDialog.show();
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