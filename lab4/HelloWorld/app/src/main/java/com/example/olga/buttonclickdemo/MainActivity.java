package com.example.olga.buttonclickdemo;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.graphics.Color;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Integer num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeColor(View view) {
        textView = (TextView) (findViewById(R.id.textViewHello));

        if (num == 0) {
            textView.setTextColor(Color.parseColor("#FF0000"));
            textView.setText("This is RED");
            num = 1;

        } else {
            textView.setTextColor(Color.parseColor("#0000FF"));
            textView.setText("This is BLUE");
            num = 0;
        }

    }
    public void gotoSecondActivity(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);


    }
}
