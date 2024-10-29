package com.skrinternationals.theclicker;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout main_layout;
    TextView textview_clicks_counter;
    Counter counter;

    int curr_colour;
    int to_colour;

    final int COLOUR_MIN_VALUE = 0;
    final int COLOUR_MAX_VALUE = 255;
    int COLOUR_INCREMENT_SIZE_RED;
    int COLOUR_INCREMENT_SIZE_GREEN;
    int COLOUR_INCREMENT_SIZE_BLUE;
    int LEVEL;
    final double ALPHA_VALUE = 10.0;
    final double DIFFICULTY_LEVEL_VALUE = 2;    //GOLDEN_RATIO, 2, EULER_CONSTANT, PI etc...


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("!!!LOGS: ", "------------------------------");
        initialize();

//        TODO: some research on optimal value
        for(int a=0;a<10;a++) {
            int next_val_to_nearest_tensG = ((int) Math.ceil((Math.pow(a, 1.61)) * ALPHA_VALUE) / 10) * 10;
            int next_val_to_nearest_tens2 = ((int) Math.ceil((Math.pow(a, 2)) * ALPHA_VALUE) / 10) * 10; // PREFERRED
            int next_val_to_nearest_tens2p5 = ((int) Math.ceil((Math.pow(a, 2.5)) * ALPHA_VALUE) / 10) * 10;
//            int next_val_to_nearest_tens50 = ((int) Math.ceil((Math.pow(a, DIFFICULTY_LEVEL_VALUE)) * ALPHA_VALUE) / 10) * 10;
            Log.d("!!!LOGS: ", "a: "+a+"\tnextG: "+next_val_to_nearest_tensG+"\tnext2: "+next_val_to_nearest_tens2+"\tnext2p5: "+next_val_to_nearest_tens2p5);
        }

//        main_layout.setOnClickListener(view -> {
//            if(counter.isGoalReach()) {
//                updateNextValues();
//                setColorIncrementSize();
//            }
//            increaseCounter();
//            updateBGColor();
//            Log.d("!!!LOGS: ", "LEVEL: "+LEVEL+"\tgoal: "+counter.getGoal()+"\tcurr_col: "+curr_colour+"\tto_col: "+to_colour);
//        });
    }

    void initialize() {
        main_layout = findViewById(R.id.main_layout);
        textview_clicks_counter = findViewById(R.id.textView_clicks_counter);
        textview_clicks_counter = findViewById(R.id.textView_clicks_counter);

        counter = new Counter();
        to_colour = Color.rgb(255, 255, 255);
        curr_colour = Color.rgb(255, 255, 255);
    }

    private void updateNextValues() {
        LEVEL++;
        int next_val = (int) Math.ceil((Math.pow(LEVEL, DIFFICULTY_LEVEL_VALUE)) * ALPHA_VALUE);
        int next_val_to_nearest_tens = (next_val / 10) * 10;
        counter.setGoal(next_val_to_nearest_tens);
        Log.d("!!!Logs:", "Counter: [from to] "+counter.getValue()+" "+counter.getGoal());
    }

    private void increaseCounter() {
        counter.increment();
        textview_clicks_counter.setText(String.valueOf(counter.getValue()));
    }

    private void setColorIncrementSize() {
        Random random = new Random();
        int red = random.nextInt(COLOUR_MAX_VALUE - COLOUR_MIN_VALUE + 1) + COLOUR_MIN_VALUE;
        int green = random.nextInt(COLOUR_MAX_VALUE - COLOUR_MIN_VALUE + 1) + COLOUR_MIN_VALUE;
        int blue = random.nextInt(COLOUR_MAX_VALUE - COLOUR_MIN_VALUE + 1) + COLOUR_MIN_VALUE;
        to_colour = Color.rgb(red, green, blue);

        COLOUR_INCREMENT_SIZE_RED = (Color.red(to_colour) - Color.red(curr_colour)) / counter.getDifference();
        COLOUR_INCREMENT_SIZE_GREEN = (Color.green(to_colour) - Color.green(curr_colour)) / counter.getDifference();
        COLOUR_INCREMENT_SIZE_BLUE = (Color.blue(to_colour) - Color.blue(curr_colour)) / counter.getDifference();
    }

    private void updateBGColor() {
        int red = Color.red(curr_colour) + COLOUR_INCREMENT_SIZE_RED;
        int green = Color.green(curr_colour) + COLOUR_INCREMENT_SIZE_GREEN;
        int blue = Color.blue(curr_colour) + COLOUR_INCREMENT_SIZE_BLUE;

        curr_colour = Color.rgb(red, green, blue);
        main_layout.setBackgroundColor(curr_colour);
    }
}
