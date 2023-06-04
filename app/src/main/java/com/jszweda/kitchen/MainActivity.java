package com.jszweda.kitchen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtvMain;
    Button addFood;
    EditText etFoodName;
    EditText etExpDate;
    EditText etQuantity;
    EditText etWeight;

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

        addFood = findViewById(R.id.fridge);
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



    }
}