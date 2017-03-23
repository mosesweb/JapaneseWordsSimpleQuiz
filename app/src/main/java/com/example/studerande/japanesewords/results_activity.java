package com.example.studerande.japanesewords;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class results_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_activity);

        String end_res_par = getIntent().getStringExtra("RESULT_IS");


        TextView end_result_textview = (TextView) findViewById(R.id.end_res);
        end_result_textview.setText(end_res_par);
    }
}
