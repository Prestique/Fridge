package com.jszweda.fridge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textMain;
    Button buttonFridge;

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
                textMain.append(" Clicked");
            }
        });

    }
}