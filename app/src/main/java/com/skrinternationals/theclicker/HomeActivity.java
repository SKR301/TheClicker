package com.skrinternationals.theclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    Button button_go;
    TextView textView_10_timing;
    TextView textView_100_timing;
    TextView textView_1000_timing;
    TextView textView_10000_timing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initialize();
        setScorecard();

        button_go.setOnClickListener(v-> {
            Intent main_intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(main_intent);
        });
    }

    void initialize() {
        button_go = findViewById(R.id.button_go);
        textView_10_timing = findViewById(R.id.textView_10_timing);
        textView_100_timing = findViewById(R.id.textView_100_timing);
        textView_1000_timing = findViewById(R.id.textView_1000_timing);
        textView_10000_timing = findViewById(R.id.textView_10000_timing);
    }

    void setScorecard() {
        textView_10_timing.setText("0");
        textView_100_timing.setText("0");
        textView_1000_timing.setText("0");
        textView_10000_timing.setText("0");
    }
}
