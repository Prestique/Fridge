package com.jszweda.kitchen;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textMain;
    Button buttonFridge;
    EditText etTextFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textMain = findViewById(R.id.textViewMain);
        textMain.setText("");

        buttonFridge = findViewById(R.id.fridge);
        buttonFridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (textMain.length() > 80){
                    textMain.setText("");
                }

                etTextFood = findViewById(R.id.etInputFood);
                String strFood = etTextFood.getText().toString();
                if (!TextUtils.isEmpty(strFood) && !(strFood.strip().length() == 0)) {
                    textMain.append(" "+ strFood.strip());
                } else {
                    etTextFood.setError("Wprowadź jedzenie");
                }
            }
        });



    }
}