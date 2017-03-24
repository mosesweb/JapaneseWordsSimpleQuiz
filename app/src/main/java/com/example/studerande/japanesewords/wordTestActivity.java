package com.example.studerande.japanesewords;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Set;

import static android.R.id.input;
import static android.R.id.list;
import static android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION;

public class wordTestActivity extends AppCompatActivity {
    word current_word = new word();
    int current_word_num = 0;
    List<word> mylist = new ArrayList<word>();
    public int amountofQuestions = 0;
    public int amountofMistakes = 0;
    public int amountofCorrect = 0;
    public boolean currCorrect = false;
    public boolean currMistake = false;
    public int onClicksCount = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        wordTestActivity.this.setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mylist.add(new word("山紫水明", "さんしすいめい", "sanshisuimei", "Scenic Beauty",
                "Mountains being illuminated by the sun in hazy violet color, floods and lakes are pure and clear.\n" +
                "山, やま, yama - Mountains\n" +
                "紫, むらさき (murasaki) Violet\n" +
                "水, みず, (mizu) Water 明, あか・るい, (akarui) Bright\n" +
                "Together they form this word of a beautiful scene."));

        mylist.add(new word("複雑多岐", "ふくざつたき", "fukuzatsutaki", "Complex and Wide-Ranging", ""));
        mylist.add(new word("四当五落", "よんとうごらく", "yontōgoraku", "Sleep four hours and pass, sleep five hours and fail", ""));
        mylist.add(new word("乾坤一擲", "けんこんいってき", "kenkonitteki", "Play all for nothing", ""));
        mylist.add(new word("夏炉冬扇", "かろとうせん", "karotousen", "Untimely and useless thing", "When it is summer the fireplace becomes useless, when it is winter the side fan becomes useless. Such is the meaning of this word, because the things is not useful for it's season."));
        mylist.add(new word("気韻生動", "きいんせいどう", "kiinseidou", "Made with elegance and vitality", "Painting and calligraphic works and such have elegance and character. The work show a dynamic and uplifting feeling made vividly."));

        amountofQuestions = mylist.size();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_test);

        TextView tw = (TextView) findViewById(R.id.question_tw);
        // TextView tw_q = (TextView) findViewById(R.id.q_num);
       // tw_q.setText("Question: #1");

       // Toast.makeText(this, word2.Japanese, Toast.LENGTH_SHORT).show();

//        final word current_quest_word = word;

        // Q 1
        getQuestion(current_word_num);
    }
    public void onClick(View v)
    {
        onClicksCount++;
        Button b = (Button)v;
        Button currButton = ((Button) v);

        String the_btn_answer = b.getText().toString();
        String res = "";
        TextView result_textview = (TextView) findViewById(R.id.result_text);

        currButton.setBackgroundResource(R.color.greenCorrect);
        String buttonText = ((Button) v).getText().toString();
        //Toast.makeText(this, buttonText, Toast.LENGTH_LONG).show();


        if(checkAnswer(the_btn_answer))
        {
            res = "correct!";

            // user clicked correct answer on first try!
            if(onClicksCount == 1) {
                currCorrect = true;

                // exception for first question
                    int curr_word_val = current_word_num + 1;
                    int correct_amountofcorrect = amountofCorrect + 1;
                    TextView howmanycorrect_textview = (TextView) findViewById(R.id.howmanycorrect);
                    float procent_result = ((float) correct_amountofcorrect /  curr_word_val);
                    String in_procent = Math.round(100.0 * procent_result) + "%";
                    howmanycorrect_textview.setText("Accuracy: " + in_procent);
            }
          //  Toast.makeText(wordTestActivity.this, res, Toast.LENGTH_SHORT).show();
            Button nextbtn = (Button) findViewById(R.id.next_button);
            nextbtn.setText("NEXT");
            nextbtn.setEnabled(true);

            result_textview.setBackgroundResource(R.color.greenCorrect);
            result_textview.setText(current_word.Japanese + " is correct");

            TextView description_textview = (TextView) findViewById(R.id.description_textview);
            // show some info about the word
            for ( int i = 0;  i < mylist.size(); i++){
                word tempWord = mylist.get(i);
                if(tempWord.equals(current_word))
                {
                    String info_string = tempWord.Japanese + " - " + tempWord.English + "\n";
                    info_string += "Kana Reading: " + tempWord.Reading + "\n";
                    info_string += "Romaji Reading: " + tempWord.Romaji + "\n";
                    if(tempWord.Description != "")
                    {
                        info_string += "\n" + tempWord.Description;
                    }
                    description_textview.setText(info_string);
                    description_textview.setMovementMethod(new ScrollingMovementMethod());


                }
            }
        }
        else
        {
            // wrong on first try
            if(onClicksCount == 1) {
                currCorrect = false;

                // exception for first question
                int curr_word_val = current_word_num + 1;
                int correct_amountofcorrect = amountofCorrect;
                TextView howmanycorrect_textview = (TextView) findViewById(R.id.howmanycorrect);
                float procent_result = ((float) correct_amountofcorrect /  curr_word_val);
                String in_procent = Math.round(100.0 * procent_result) + "%";
                howmanycorrect_textview.setText("Accuracy: " + in_procent);
            }

            res = "wrong!";
           // Toast.makeText(wordTestActivity.this, res, Toast.LENGTH_SHORT).show();
            result_textview.setBackgroundResource(R.color.redWrong);
            result_textview.setText(currButton.getText().toString() + " is wrong");
            currButton.setBackgroundResource(R.color.redWrong);
        }
    }
    public void nextQuestion(View v)
    {
        current_word_num++;
        if(currCorrect == true)
        {
            amountofCorrect++;
        }
        currCorrect = false;
        onClicksCount = 0;
        getQuestion(current_word_num);
        setRandomOptions();
        Button nextbtn = (Button) findViewById(R.id.next_button);
        nextbtn.setText("ANSWER");
        nextbtn.setEnabled(false);
        resetAllButtons();
    }
    public void resetAllButtons()
    {
        Button btn1 = (Button) findViewById(R.id.ansr_btn1);
        Button btn2 = (Button) findViewById(R.id.ansr_btn2);
        Button btn3 = (Button) findViewById(R.id.ansr_btn3);
        Button btn4 = (Button) findViewById(R.id.ansr_btn4);
        btn1.setBackgroundResource(R.color.whiteDef);
        btn2.setBackgroundResource(R.color.whiteDef);
        btn3.setBackgroundResource(R.color.whiteDef);
        btn4.setBackgroundResource(R.color.whiteDef);

        TextView result_textview = (TextView) findViewById(R.id.result_text);
        result_textview.setText("Awaiting your answer...");
        result_textview.setBackgroundResource(R.color.whiteDef);

        TextView description_textview = (TextView) findViewById(R.id.description_textview);
        description_textview.setText("");
    }


    public void getQuestion(int q_num)
    {
        TextView tw = (TextView) findViewById(R.id.question_tw);
        if(q_num != mylist.size()) {
            int whatnumberofquestion = 0;
            whatnumberofquestion = q_num + 1;
           // TextView tw_q = (TextView) findViewById(R.id.q_num);
            // tw_q.setText("Question: #" + whatnumberofquestion);

            current_word = (word) mylist.get(q_num);
            setRandomOptions();
            String Q_number = Integer.toString(q_num);
            //tw_q.setText("asadasd");
            //tw.setText(Integer.toString(mylist.size()) + " yes");
            tw.setText("Question #" + whatnumberofquestion + ": " + current_word.English);
        }
        else
        {
            tw.setText("ALL QUESTIONS ARE DONE!");

            // calucate correct procent
            float procent_result = ((float) amountofCorrect / amountofQuestions);
            String in_procent =  Math.round(100.0 * procent_result) + "%";

            Intent intent = new Intent(getBaseContext(), results_activity.class);
            intent.putExtra("RESULT_PROCENT", in_procent);
            intent.putExtra("RESULT_PROCENT_FLOAT", procent_result);
            intent.putExtra("RESULT_CORRECT", amountofCorrect);
            intent.putExtra("RESULT_AMOUNT_OF_Q", amountofQuestions);

            // start the result screen activity
            intent.addFlags(FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }
    }
    public Boolean checkAnswer(String jap_jap)
    {
        if(current_word.Japanese == jap_jap)
        {
            return true;
        }
        return false;
    }
    public void setRandomOptions()
    {
        Button answer_button1 = (Button) findViewById(R.id.ansr_btn1);
        Button answer_button2 = (Button) findViewById(R.id.ansr_btn2);
        Button answer_button3 = (Button) findViewById(R.id.ansr_btn3);
        Button answer_button4 = (Button) findViewById(R.id.ansr_btn4);

        Integer list_size = mylist.size();

        // put the values from each object into a string array
        String[] wordStringArr = new String[amountofQuestions];

        // what we want to do in the future..


        String option_type = "Japanese";

        for(int x = 0; x < wordStringArr.length; x++)
        {
            wordStringArr[x] = mylist.get(x).Japanese;
        }

        Log.d("asd", Integer.toString(list_size));

        //List<String> al = new ArrayList<>();
        // use hashmap instead??
        //Set<String> al = new HashSet<>(Arrays.asList(wordStringArr));
        Set<String> al = new HashSet<>(Arrays.asList(wordStringArr));

        // add elements to al, including duplicates
        Set<String> hs = new HashSet<>();
        hs.addAll(al);
        // take away duplicates!
        al.clear();
        al.addAll(hs);

        // randomize romaji text
        //RandomizeArray(wordStringArr);
        // create an iterator
        Iterator iterator = hs.iterator();

        // new arrayList
        List<String> myjapaneselist = new ArrayList<String>();

        // check values
        while (iterator.hasNext()){
            //Log.d("Interactor: ", "Value: "+iterator.next() + " ");
            myjapaneselist.add(iterator.next().toString());
        }

        //wordStringArr = hs.toArray(new String[hs.size()]);
        for (int i = 0; i < myjapaneselist.size(); i++){
            Log.i("myjapaneselist name: ", myjapaneselist.get(i));
        }

        // remove the word in question from the myjapaneselist array
        for ( int i = 0;  i < myjapaneselist.size(); i++){
            String tempName = myjapaneselist.get(i);
            if(tempName.equals(current_word.Japanese))
            {
                myjapaneselist.remove(i);
            }
        }

        // shuffle myjapaneselist
        Collections.shuffle(myjapaneselist);

        List<String> q_list = new ArrayList<String>();
        q_list.add(current_word.Japanese);
        q_list.add(myjapaneselist.get(0));
        q_list.add(myjapaneselist.get(1));
        q_list.add(myjapaneselist.get(2));

        // shuffle answers
        Collections.shuffle(q_list);

        // set buttons
        answer_button1.setText(q_list.get(0));
        answer_button2.setText(q_list.get(1));
        answer_button3.setText(q_list.get(2));
        answer_button4.setText(q_list.get(3));

        Log.d("this is my array", "arr: " + Arrays.toString(wordStringArr));
    }
    public void updateScore()
    {
        // Score
        TextView howmanycorrect_textview = (TextView) findViewById(R.id.howmanycorrect);
        float procent_result = ((float) amountofCorrect / current_word_num);
        String in_procent =  Math.round(100.0 * procent_result) + "%";
        howmanycorrect_textview.setText("Accuracy: " + in_procent);
    }

    public void setTextJapanese(Button btn, String txt)
    {
        btn.setText(txt);
    }

    public static String[] RandomizeArray(String[] array){
        Random rgen = new Random();  // Random number generator

        for (int i=0; i<array.length; i++) {
            int randomPosition = rgen.nextInt(array.length);
            String temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }

        return array;
    }

}