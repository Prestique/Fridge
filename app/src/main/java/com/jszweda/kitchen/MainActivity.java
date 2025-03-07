package com.jszweda.kitchen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jszweda.kitchen.databinding.ActivityMainBinding;


import java.time.LocalDate;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<Food> foodList = new ArrayList<>();
    MyViewModel myViewModel;
    FoodDatabase foodDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        foodDatabase = FoodDatabase.getInstance(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        foodList.add(new Food("kasza", LocalDate.now().plusDays(3), 5, 6));
        binding.addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HelperClass.saveFood(binding.etQuantity, binding.etWeight, binding.etInputFood, binding.tvExpDate,
                        getApplicationContext(), myViewModel);

            }
        });

        binding.goFridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), FridgeActivity.class);
//                i.putParcelableArrayListExtra("foodList", foodList);
                startActivity(i);
            }
        });

        binding.tvExpDate.setOnClickListener(view -> {
            DatePickerFragment datePickerFragment = new DatePickerFragment();
            HelperClass.setDateOnTextView(datePickerFragment, (TextView) view, getSupportFragmentManager());
        });

        binding.etQuantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    ((EditText) view).setText("");
                }
            }
        });

        binding.etWeight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    ((EditText) view).setText("");
                }
            }
        });

        binding.etInputFood.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    ((EditText) view).setText("");
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
                }
            }
        });

//        binding.etInputFood.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_DONE ||
//                        (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
//                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//                    return true;
//                }
//                return false;
//            }
//        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @SuppressLint({"NonConstantResourceId"})
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.item1: {
                String number = "505505505";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + number));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "No app can handle this action", Toast.LENGTH_SHORT).show();
                }
//                try {
//                    startActivity(intent);
//                } catch (ActivityNotFoundException e) {
//                    Toast.makeText(this, "No app can handle this action", Toast.LENGTH_SHORT).show();
//                }
                return true;
            }

            case R.id.item2:
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, "pizza");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "No app can handle this action", Toast.LENGTH_SHORT).show();
                }

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}