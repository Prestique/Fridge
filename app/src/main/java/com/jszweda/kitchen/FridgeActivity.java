package com.jszweda.kitchen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jszweda.kitchen.databinding.ActivityFridgeBinding;
import com.jszweda.kitchen.databinding.SelectedItemBinding;

import java.util.ArrayList;
import java.util.Comparator;

public class FridgeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Food> foodList;
    private FoodAdapter foodAdapter;
    private ActivityFridgeBinding binding;
    private boolean isDateReversedOrder = false;
    private boolean isNameReversedOrder = true;
    private SelectedItemBinding bindingEditPopup;


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
                showEditItemPopup(position);
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

    private void showEditItemPopup(int index) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        bindingEditPopup = SelectedItemBinding.inflate(getLayoutInflater());
        alertDialog.setView(bindingEditPopup.getRoot());

        bindingEditPopup.etQuantity.setText(""+foodList.get(index).getQuantity());
        bindingEditPopup.etWeight.setText(""+foodList.get(index).getWeight());
        bindingEditPopup.etInputFood.setText(foodList.get(index).getFoodName());
        bindingEditPopup.tvExpDate.setText(foodList.get(index).getDateAsString());

        DatePickerFragment datePickerFragment = new DatePickerFragment();

        bindingEditPopup.tvExpDate.setOnClickListener(view -> {
            HelperClass.setDateOnTextView(datePickerFragment, (TextView) view, getSupportFragmentManager());
        });


        alertDialog.setPositiveButton("Zapisz", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                HelperClass.editFood(bindingEditPopup.etQuantity, bindingEditPopup.etWeight, bindingEditPopup.etInputFood,
                        bindingEditPopup.tvExpDate, getApplicationContext(), foodList, index);
                foodAdapter.notifyItemChanged(index);
            }
        });

        alertDialog.setNegativeButton("Usuń", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                foodList.remove(index);
                foodAdapter.notifyItemRemoved(index);
                if (index < foodList.size()) {
                    foodAdapter.notifyItemRangeChanged(index, foodList.size() - index);
                }
            }
        });

        alertDialog.show();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Glide.with(this).clear(binding.imageView);
    }
}