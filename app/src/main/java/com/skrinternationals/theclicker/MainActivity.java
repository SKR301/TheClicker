package com.skrinternationals.theclicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout main_layout;
    TextView textView_clicks_counter;

    int COLOR_INCREMENT_STEP = 1;
    int COLOR_MAX_VALUE = 16777215;
    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_layout = findViewById(R.id.main_layout);
        textView_clicks_counter = findViewById(R.id.textView_clicks_counter);
        random = new Random();

        main_layout.setOnClickListener(view -> {
            int counter = Integer.parseInt(textView_clicks_counter.getText().toString());
            textView_clicks_counter.setText(String.valueOf(counter + COLOR_INCREMENT_STEP));
            int color_value = random.nextInt(COLOR_MAX_VALUE);
            setLayoutColorWithIntVal(main_layout, color_value);
            setTextColorWithIntVal(textView_clicks_counter, ((COLOR_MAX_VALUE - color_value) % COLOR_MAX_VALUE + COLOR_MAX_VALUE) % COLOR_MAX_VALUE);
        });

    }

    void setLayoutColorWithIntVal(ConstraintLayout layout, int color_value) {
        StringBuilder color_value_string = new StringBuilder(Integer.toString(color_value, 16));
        while (color_value_string.length() < 6) {
            color_value_string.insert(0, '0');
        }
        Log.d("TAG!!!: ", "layout_color: "+color_value);
        layout.setBackgroundColor(Color.parseColor("#"+color_value_string));
    }

    void setTextColorWithIntVal(TextView text_view, int color_value) {
        StringBuilder color_value_string = new StringBuilder(Integer.toString(color_value, 16));
        while (color_value_string.length() < 6) {
            color_value_string.insert(0, '0');
        }
        Log.d("TAG!!!: ", "text_color: "+color_value);
        text_view.setTextColor(Color.parseColor("#"+color_value_string));
    }
}
