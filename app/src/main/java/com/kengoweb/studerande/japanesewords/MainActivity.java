package com.kengoweb.studerande.japanesewords;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.study_button);
        btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

                // could send some data to the intent if we want to have category
                // for more options or something
                Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, wordTestActivity.class);
                startActivity(intent);
            }
        });
    }
}
