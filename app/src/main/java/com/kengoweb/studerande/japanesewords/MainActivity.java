package com.kengoweb.studerande.japanesewords;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import static android.R.attr.checked;

public class MainActivity extends AppCompatActivity {

    public static final String PREF_QUIZ_TYPE_SETTING = "japquiz_pref_file";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = getSharedPreferences(PREF_QUIZ_TYPE_SETTING, MODE_PRIVATE);
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        Button btn = (Button) findViewById(R.id.study_button);

        // get value of selected mode
        // http://stackoverflow.com/questions/23024831/android-shared-preferences-example
        String restoredText = sharedPref.getString("quiz_mode", null);
        if (restoredText != null) {
            Toast.makeText(this, "You have checked" + restoredText, Toast.LENGTH_SHORT).show();
        }

        btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

                // could send some data to the intent if we want to have category
                // for more options or something
                Intent intent = new Intent(MainActivity.this, wordTestActivity.class);
                startActivity(intent);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                //radioGroup.check(checkedId);
                RadioButton rb = (RadioButton) findViewById(checkedId);
                String radioText = rb.getText().toString();
                Context context = getApplicationContext();
                CharSequence text = "you checked" + radioText;
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                SharedPreferences.Editor editor = getSharedPreferences(PREF_QUIZ_TYPE_SETTING, MODE_PRIVATE).edit();
                editor.putString("quiz_mode", radioText);
                editor.putInt("quiz_mode_num", checkedId);
                editor.commit();
            }
        });



    }

    // Check user settings..



}
