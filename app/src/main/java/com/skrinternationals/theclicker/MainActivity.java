package com.skrinternationals.theclicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout main_layout;
    TextView textView_clicks_counter;

    int COLOR_INCREMENT_STEP = 150;
    int COLOR_MAX_VALUE = 16777215;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_layout = findViewById(R.id.main_layout);
        textView_clicks_counter = findViewById(R.id.textView_clicks_counter);

        main_layout.setOnClickListener(view -> {
            int counter = Integer.parseInt(textView_clicks_counter.getText().toString());
            textView_clicks_counter.setText(String.valueOf(counter + COLOR_INCREMENT_STEP));
            setLayoutWithIntColorVal(main_layout, COLOR_MAX_VALUE - counter);
        });
    }

    void setLayoutWithIntColorVal(ConstraintLayout layout, int color_value) {

        StringBuilder color_value_string = new StringBuilder(Integer.toString(color_value, 16));
        while (color_value_string.length() < 6) {
            color_value_string.insert(0, '0');
        }
//        Log.d("TAG!!!: ", color_value_string.toString());
        layout.setBackgroundColor(Color.parseColor("#"+color_value_string));
    }
}
