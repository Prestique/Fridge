package com.jszweda.kitchen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jszweda.kitchen.databinding.ActivityFridgeBinding;
import com.jszweda.kitchen.databinding.SelectedItemBinding;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FridgeActivity extends AppCompatActivity {
    //Data Source
    private FoodDatabase foodDatabase;
    private ArrayList<Food> foodList = new ArrayList<>();
    //Adapter
    private FoodAdapter foodAdapter;
    //Binding
    private ActivityFridgeBinding fridgeBinding;
    MyViewModel viewModel;

    private RecyclerView recyclerView;
    private boolean isDateReversedOrder = false;
    private boolean isNameReversedOrder = true;
    private SelectedItemBinding bindingEditPopup;


    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fridge);
//        setContentView(fridgeBinding.getRoot());
        getSupportActionBar().hide();
        fridgeBinding = DataBindingUtil.setContentView(this, R.layout.activity_fridge);
        fridgeBinding.setLifecycleOwner(this);
        //RecyclerView
        recyclerView = fridgeBinding.recyclerviewItem;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

/*
        Intent i = getIntent();
        foodList = i.getParcelableArrayListExtra("foodList");
        foodList.sort(Comparator.comparingLong(Food::getDaysLeft));
        recyclerView = binding.recyclerviewItem;
        foodAdapter = new FoodAdapter(foodList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(foodAdapter);
 */
        //Database
        foodDatabase = FoodDatabase.getInstance(this);
        //View Model
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        //Insert food for test
//        Food f1 = new Food("Kawa", LocalDate.now().plusDays(5),500, 2);
//        viewModel.addFood(f1);

        //Loading the data from ROOM DB
        viewModel.getAllFoods().observe(this,
                new Observer<List<Food>>() {
                    @Override
                    public void onChanged(List<Food> foods) {
                        foodList.clear();

                        for (Food f: foods) {
                            foodList.add(f);
                        }
                        foodAdapter.notifyDataSetChanged();
                    }
                });

        //Adapter
        foodAdapter = new FoodAdapter(foodList);

        //Linking recyclerView with the Adapter
        recyclerView.setAdapter(foodAdapter);


        foodAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                showEditItemPopup(position);
            }
        });

        Glide.with(this)
                .load(R.drawable.palmy_plaza)
                .into(fridgeBinding.imageView);

        fridgeBinding.sortExpDate.setOnClickListener(view -> {
            if (isDateReversedOrder) {
                foodList.sort(Comparator.comparingLong(Food::getDaysLeft));
                isDateReversedOrder = false;
            } else {
                foodList.sort(Comparator.comparingLong(Food::getDaysLeft).reversed());
                isDateReversedOrder = true;
            }
            foodAdapter.notifyDataSetChanged();
        });

        fridgeBinding.sortName.setOnClickListener(view -> {
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

        Food selectedFood = foodList.get(index);

        bindingEditPopup.etQuantity.setText(""+selectedFood.getQuantity());
        bindingEditPopup.etWeight.setText(""+selectedFood.getWeight());
        bindingEditPopup.etInputFood.setText(selectedFood.getFoodName());
        bindingEditPopup.tvExpDate.setText(selectedFood.getDateAsString());

        DatePickerFragment datePickerFragment = new DatePickerFragment();

        bindingEditPopup.tvExpDate.setOnClickListener(view -> {
            HelperClass.setDateOnTextView(datePickerFragment, (TextView) view, getSupportFragmentManager());
        });


        alertDialog.setPositiveButton("Zapisz", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                HelperClass.editFood(bindingEditPopup.etQuantity, bindingEditPopup.etWeight, bindingEditPopup.etInputFood,
                        bindingEditPopup.tvExpDate, selectedFood, viewModel);
                foodAdapter.notifyItemChanged(index);
            }
        });

        alertDialog.setNegativeButton("Usuń", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                viewModel.deleteFood(foodList.get(index));
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