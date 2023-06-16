package com.jszweda.kitchen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txtvMain;
    Button addFood, goFridge;
    EditText etFoodName, etExpDate, etQuantity, etWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtvMain = findViewById(R.id.textViewMain);
        txtvMain.setText("");
        etFoodName = findViewById(R.id.etInputFood);
        etExpDate = findViewById(R.id.etExpDate);
        etQuantity = findViewById(R.id.etQuantity);
        etWeight = findViewById(R.id.etWeight);

        goFridge = findViewById(R.id.goFridge);
        addFood = findViewById(R.id.addFood);
        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (txtvMain.length() > 80){
                    txtvMain.setText("");
                }

                String strFood = etFoodName.getText().toString().strip();

                if (!TextUtils.isEmpty(strFood) && !(strFood.length() == 0)) {
                    txtvMain.append(" "+ strFood);
                } else {
                    etFoodName.setError("Wprowadź jedzenie");
                }
            }
        });

        goFridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), FridgeActivity.class);
                startActivity(i);
            }
        });


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
}