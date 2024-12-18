package com.skrinternationals.theclicker;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout main_layout;
    TextView textview_clicks_counter;
    TextView textview_level_value;
    TextView textview_level_label;
    TextView textView_quickest_label;
    TextView textView_quickest_timing;
    TextView textView_quickest_ms;
    TextView textView_average_label;
    TextView textView_average_timing;
    TextView textView_average_ms;
    TextView textView_slowest_label;
    TextView textView_slowest_timing;
    TextView textView_slowest_ms;
    Counter counter;

    int curr_colour;
    int to_colour;

    long prev_click = -1;
    long next_click = -1;
    long timing = 0;
    long sum_timing = 0;

    final int COLOUR_MIN_VALUE = 0;
    final int COLOUR_MAX_VALUE = 255;
    int COLOUR_INCREMENT_SIZE_RED;
    int COLOUR_INCREMENT_SIZE_GREEN;
    int COLOUR_INCREMENT_SIZE_BLUE;
    int LEVEL;
    final double ALPHA_VALUE = 10.0;                //10, 15, 20
    final double DIFFICULTY_LEVEL_VALUE = 1.61;     //GOLDEN_RATIO, 2, EULER_CONSTANT, PI etc...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        main_layout.setOnClickListener(view -> {
            long current_time = System.currentTimeMillis();

            if (prev_click < 0) {
                prev_click = current_time;
            } else {
                next_click = current_time;

                timing = (next_click - prev_click);
                sum_timing += timing;

                if (textView_quickest_timing.getText().toString().equals(getString(R.string.textview_time_default))) {
                    textView_quickest_timing.setText(String.valueOf(timing));
                } else {
                    if (Integer.parseInt(textView_quickest_timing.getText().toString()) > timing) {
                        textView_quickest_timing.setText(String.valueOf(timing));
                    }
                }

                if (textView_slowest_timing.getText().toString().equals(getString(R.string.textview_time_default))) {
                    textView_slowest_timing.setText(String.valueOf(timing));
                } else {
                    if (Integer.parseInt(textView_slowest_timing.getText().toString()) < timing) {
                        textView_slowest_timing.setText(String.valueOf(timing));
                    }
                }

                textView_average_timing.setText(String.valueOf(sum_timing / counter.getValue()));

                prev_click = next_click;
            }

            if(counter.isGoalReach()) {
                updateNextValues();
                setColorIncrementSize();
            }
            increaseCounter();
            updateBGColor();
        });
    }

    void initialize() {
        main_layout = findViewById(R.id.main_layout);
        textview_clicks_counter = findViewById(R.id.textView_clicks_counter);
        textview_level_value = findViewById(R.id.textview_level_value);
        textview_level_label = findViewById(R.id.textview_level_label);
        textView_quickest_label = findViewById(R.id.textView_quickest_label);
        textView_quickest_timing = findViewById(R.id.textView_quickest_timing);
        textView_quickest_ms = findViewById(R.id.textView_quickest_ms);
        textView_average_label = findViewById(R.id.textView_average_label);
        textView_average_timing = findViewById(R.id.textView_average_timing);
        textView_average_ms = findViewById(R.id.textView_average_ms);
        textView_slowest_label = findViewById(R.id.textView_slowest_label);
        textView_slowest_timing = findViewById(R.id.textView_slowest_timing);
        textView_slowest_ms = findViewById(R.id.textView_slowest_ms);

        counter = new Counter();
        to_colour = Color.rgb(255, 255, 255);
        curr_colour = Color.rgb(255, 255, 255);
    }

    private void updateNextValues() {
        LEVEL++;
        int next_val = (int) Math.ceil((Math.pow(LEVEL, DIFFICULTY_LEVEL_VALUE)) * ALPHA_VALUE);
        int next_val_to_nearest_tens = (next_val / 10) * 10;
        counter.setGoal(next_val_to_nearest_tens);
        textview_level_value.setText(String.valueOf(LEVEL));
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

        textview_level_label.setTextColor(getContrastingColor(curr_colour));
        textview_level_value.setTextColor(getContrastingColor(curr_colour));
        textview_clicks_counter.setTextColor(getContrastingColor(curr_colour));

        textView_quickest_label.setTextColor(getContrastingColor(curr_colour));
        textView_quickest_timing.setTextColor(getContrastingColor(curr_colour));
        textView_quickest_ms.setTextColor(getContrastingColor(curr_colour));
        textView_average_label.setTextColor(getContrastingColor(curr_colour));
        textView_average_timing.setTextColor(getContrastingColor(curr_colour));
        textView_average_ms.setTextColor(getContrastingColor(curr_colour));
        textView_slowest_label.setTextColor(getContrastingColor(curr_colour));
        textView_slowest_timing.setTextColor(getContrastingColor(curr_colour));
        textView_slowest_ms.setTextColor(getContrastingColor(curr_colour));
    }

    private int getContrastingColor(int color) {
        double luminance = (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color));
        return (luminance > 186) ? Color.BLACK : Color.WHITE;
    }
}
