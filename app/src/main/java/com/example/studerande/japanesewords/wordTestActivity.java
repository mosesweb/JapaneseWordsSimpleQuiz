package com.example.studerande.japanesewords;

import android.content.Intent;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        word word = new word();
        word.English = "test";
        word.Japanese = "テスト";
        word.Romaji = "tesuto";

        word word2 = new word();
        word2.English = "(warrior) weapon";
        word2.Japanese = "武器";
        word2.Romaji = "buki";

        word word3 = new word();
        word3.English = "hello";
        word3.Japanese = "こんにちは";
        word3.Romaji = "konnichiwa";

        word word4 = new word();
        word4.English = "what";
        word4.Japanese = "何";
        word4.Romaji = "nani";

        mylist.add(word);
        mylist.add(word2);
        mylist.add(word3);
        mylist.add(word4);
        mylist.add(new word("毎日", "mainichi", "mainichi", "everyday"));
        mylist.add(new word("今日", "kyou", "kyou", "today"));
        mylist.add(new word("パソコン", "pasokon", "pasokon", "comupter"));
        mylist.add(new word("日", "hi", "hi", "day"));

        amountofQuestions = mylist.size();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_test);

        TextView tw = (TextView) findViewById(R.id.question_tw);
        // TextView tw_q = (TextView) findViewById(R.id.q_num);
       // tw_q.setText("Question: #1");

       // Toast.makeText(this, word2.Japanese, Toast.LENGTH_SHORT).show();

        final word current_quest_word = word;

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
            }
          //  Toast.makeText(wordTestActivity.this, res, Toast.LENGTH_SHORT).show();
            Button nextbtn = (Button) findViewById(R.id.next_button);
            nextbtn.setText("NEXT");
            nextbtn.setEnabled(true);

            result_textview.setBackgroundResource(R.color.greenCorrect);
            result_textview.setText(current_word.Japanese + " is correct");

        }
        else
        {
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
        // Score

        TextView howmanycorrect_textview = (TextView) findViewById(R.id.howmanycorrect);
        float procent_result = ((float) amountofCorrect / current_word_num);
        String in_procent =  Math.round(100.0 * procent_result) + "%";
        howmanycorrect_textview.setText("Accuracy: " + in_procent);

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
            tw.setText(whatnumberofquestion + " : Queston: " + current_word.English + " in Japanese is?");
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
        String[] wordStringArr = new String[] {
                mylist.get(0).Japanese,
                mylist.get(1).Japanese,
                mylist.get(2).Japanese,
                mylist.get(3).Japanese,
                mylist.get(4).Japanese,
                mylist.get(5).Japanese,
                mylist.get(6).Japanese,
                mylist.get(7).Japanese
        };

        Log.d("asd", Integer.toString(list_size));

        //List<String> al = new ArrayList<>();
        // use hashmap instead??
        //Set<String> al = new HashSet<>(Arrays.asList(wordStringArr));
        Set<String> al = new HashSet<>(Arrays.asList(wordStringArr));

        // add elements to al, including duplicates
        Set<String> hs = new HashSet<>();
        hs.addAll(al);
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

        /*
        //RandomizeArray(wordStringArr);
        String currobj = myjapaneselist.remove(0); // remove by index!
        Collections.shuffle(myjapaneselist);
        myjapaneselist.add(0, currobj);
*/
        for ( int i = 0;  i < myjapaneselist.size(); i++){
            String tempName = myjapaneselist.get(i);
            if(tempName.equals(current_word.Japanese))
            {
                myjapaneselist.remove(i);
            }
        }

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