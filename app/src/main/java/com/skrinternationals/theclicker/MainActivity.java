package com.skrinternationals.theclicker;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout main_layout;
    TextView textview_clicks_counter;
    Counter counter;
    Button button_temp1;
    Button button_temp2;


    int to_colour;
    int curr_colour;

    final int COLOUR_MIN_VALUE = 0;
    final int COLOUR_MAX_VALUE = 255;
    int COLOUR_INCREMENT_SIZE_RED;
    int COLOUR_INCREMENT_SIZE_GREEN;
    int COLOUR_INCREMENT_SIZE_BLUE;
    int COLOUR_INCREMENT_STEPS;
    int LEVEL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        Log.d("!!!Logs:","-------------------------------------------------");

        main_layout.setOnClickListener(view -> {
            increaseCounter();
            if(counter.getValue() % COLOUR_INCREMENT_STEPS == 0) {
                setColorIncrementSize();
            }
            updateBGColor();

            textview_clicks_counter.setTextColor(to_colour);
        });
    }

    private void setColorIncrementSize() {
        Random random = new Random();
        int red = random.nextInt(COLOUR_MAX_VALUE - COLOUR_MIN_VALUE + 1) + COLOUR_MIN_VALUE;
        int green = random.nextInt(COLOUR_MAX_VALUE - COLOUR_MIN_VALUE + 1) + COLOUR_MIN_VALUE;
        int blue = random.nextInt(COLOUR_MAX_VALUE - COLOUR_MIN_VALUE + 1) + COLOUR_MIN_VALUE;
        to_colour = Color.rgb(red, green, blue);

        COLOUR_INCREMENT_SIZE_RED = (Color.red(to_colour) - Color.red(curr_colour)) / COLOUR_INCREMENT_STEPS;
        COLOUR_INCREMENT_SIZE_GREEN = (Color.green(to_colour) - Color.green(curr_colour)) / COLOUR_INCREMENT_STEPS;
        COLOUR_INCREMENT_SIZE_BLUE = (Color.blue(to_colour) - Color.blue(curr_colour)) / COLOUR_INCREMENT_STEPS;
    }

    private void increaseCounter() {
        counter.increment();
        textview_clicks_counter.setText(String.valueOf(counter.getValue()));
    }

    private void updateBGColor() {
//        curr_colour += COLOUR_INCREMENT_SIZE;
        int red = Color.red(curr_colour) + COLOUR_INCREMENT_SIZE_RED;
        int green = Color.green(curr_colour) + COLOUR_INCREMENT_SIZE_GREEN;
        int blue = Color.blue(curr_colour) + COLOUR_INCREMENT_SIZE_BLUE;

        curr_colour = Color.rgb(red, green, blue);
//        main_layout.setBackgroundColor(curr_colour);
        button_temp1.setBackgroundColor(curr_colour);
        button_temp2.setBackgroundColor(to_colour);
    }


    void initialize() {
        main_layout = findViewById(R.id.main_layout);
        textview_clicks_counter = findViewById(R.id.textView_clicks_counter);
        button_temp1 = findViewById(R.id.button_temp1);
        button_temp2 = findViewById(R.id.button_temp2);
        textview_clicks_counter = findViewById(R.id.textView_clicks_counter);

        counter = new Counter();
        to_colour = Color.rgb(255, 255, 255);
        curr_colour = Color.rgb(255, 255, 255);

        LEVEL = 0;
        COLOUR_INCREMENT_STEPS = 10;
    }
}
