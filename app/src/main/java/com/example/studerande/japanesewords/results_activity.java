package com.example.studerande.japanesewords;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class results_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_activity);

        String procent_result = getIntent().getStringExtra("RESULT_PROCENT");
        float procent_result_float = getIntent().getFloatExtra("RESULT_PROCENT_FLOAT", 0);
        procent_result_float = Math.round(100.0 * procent_result_float);

        int correct_answers = getIntent().getIntExtra("RESULT_CORRECT", 0);
        int total_answers = getIntent().getIntExtra("RESULT_AMOUNT_OF_Q", 0);

        ImageView encouragement_image = (ImageView) findViewById(R.id.encouragement_img);
        encouragement_image.setVisibility(View.GONE);

        String scoremsg = "There was an attempt..";

        if(procent_result_float > 20)
        {
            scoremsg = "You just need some more practice. Go again perhaps?";
        }
        if(procent_result_float >= 50)
        {
            scoremsg = "Well, 50% or over, that's a milestone!\n" +
                    "However, I recommend to take the test again and learn more!";
            encouragement_image.setVisibility(View.VISIBLE);
        }
        if(procent_result_float > 60)
        {
            scoremsg = "Great job! Not bad!\n" +
                    "Keep it up.";
        }
        if(procent_result_float > 70)
        {
            scoremsg = "Great job!\n" +
                    "You got a pretty good score!";
        }
        if(procent_result_float > 80)
        {
            scoremsg = "Wow! You're really good!\n" +
                    "Very close to 90%.. or even perfect score!";
        }
        if(procent_result_float > 90)
        {
            scoremsg = "Amazing job!\n" +
                    "よく頑張りましたね。お疲れさまでした!";
        }
        if(procent_result_float > 99)
        {
            scoremsg = "Masterful! PERFECT score reached!!\n" +
                    "お疲れさまでした!";
        }

        TextView end_result_textview = (TextView) findViewById(R.id.end_res);
        String myresult = scoremsg + "\n" + correct_answers + " of " + total_answers + " correct (" + procent_result + ")";

        end_result_textview.setText(myresult);
    }
    // takes the user back to the first activity.. not the test activity
    @Override
    public void onBackPressed()
    {
        finish();
        Intent intent = new Intent(results_activity.this, MainActivity.class);
        startActivity(intent);
    }
}
