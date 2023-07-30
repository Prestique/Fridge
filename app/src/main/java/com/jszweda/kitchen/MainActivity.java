package com.jszweda.kitchen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jszweda.kitchen.databinding.ActivityMainBinding;
import com.jszweda.kitchen.databinding.SelectedItemBinding;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<Food> foodList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        foodList.add(new Food("kasza", LocalDate.now().plusDays(3), 5, 6));
        foodList.add(new Food("Mango", LocalDate.now().plusDays(10), 500, 2));
        foodList.add(new Food("Szynka", LocalDate.now().minusDays(7), 120, 4));
        foodList.add(new Food("Frytki", LocalDate.now().plusDays(180), 2, 2));
        foodList.add(new Food("Burger", LocalDate.now().minusDays(2), 500, 2));
        foodList.add(new Food("Puszka coli", LocalDate.now().plusDays(32), 500, 2));
        foodList.add(new Food("Pizza", LocalDate.now().plusDays(1), 350, 1));
        foodList.add(new Food("Cheetosy", LocalDate.now().plusDays(3), 5, 6));
        foodList.add(new Food("Lasy", LocalDate.now().plusDays(10), 500, 2));
        foodList.add(new Food("Spaghetti z tytki", LocalDate.now().minusDays(7), 120, 4));
        foodList.add(new Food("jabłka", LocalDate.now().plusDays(180), 2, 2));
        foodList.add(new Food("Frytototrtilla", LocalDate.now().minusDays(2), 500, 2));
        foodList.add(new Food("Spaghetti bolognese", LocalDate.now().plusDays(32), 500, 2));
        foodList.add(new Food("Ta kwadratowa", LocalDate.now().plusDays(1), 350, 1));

        binding.addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String quantity = binding.etQuantity.getText().toString().strip();
                String weight = binding.etWeight.getText().toString().strip();
                String foodName = binding.etInputFood.getText().toString().strip();
                LocalDate expDate = LocalDate.now();

                String regexDate = "\\d{1,2}-\\d{1,2}-\\d{4,}";
                boolean matchesDate = binding.tvExpDate.getText().toString().matches(regexDate);
                if (matchesDate) {
                    expDate = LocalDate.parse(binding.tvExpDate.getText().toString(), DateTimeFormatter.ofPattern("d-M-yyyy"));
                } else {
                    binding.tvExpDate.setText("Wybierz datę");
                }

                if (isFieldEmpty(binding.etQuantity,"Wprowadź ilość")&&
                isFieldEmpty(binding.etWeight,"Wprowadź wagę")&&
                isFieldEmpty(binding.etQuantity,"Wybierz ilość") && matchesDate) {
                foodList.add(new Food(foodName,expDate,Integer.parseInt(weight), Integer.parseInt(quantity)));
                Toast.makeText(getApplicationContext(), "Dodano "+ foodName, Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.goFridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), FridgeActivity.class);
                i.putParcelableArrayListExtra("foodList", foodList);
                startActivity(i);
            }
        });

        binding.tvExpDate.setOnClickListener(view -> {
            DatePickerFragment datePickerFragment = new DatePickerFragment();

            datePickerFragment.setterOnDateSelectedListener(new OnDateSelectedListener() {
                @Override
                public void onDateSelected(int year, int month, int day) {
                    // Obsłuż wybraną datę
                    // Możesz zaktualizować TextView lub wykonać inne operacje
                    String selectedDate = day + "-" + (month+1) + "-" + year;
                    binding.tvExpDate.setText(selectedDate);
                }
            });
            datePickerFragment.show(getSupportFragmentManager(), "Wybierz datę:");
        });

        binding.etQuantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus){
                    ((EditText)view).setText("");
                }
            }
        });

        binding.etWeight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus){
                    ((EditText)view).setText("");
                }
            }
        });

        binding.etInputFood.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus){
                    ((EditText)view).setText("");
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
            case R.id.item1 ->
                Toast.makeText(this, "Item1", Toast.LENGTH_SHORT).show();

            case R.id.item2 ->
                Toast.makeText(this, "item2", Toast.LENGTH_SHORT).show();

            default ->
                super.onOptionsItemSelected(item);
        }
        return false;
    }
    private boolean isFieldEmpty(TextView view, String message){
        if (TextUtils.isEmpty(view.getText().toString())){
            view.setError(message);
            return false;
        }
        return true;
    }

}