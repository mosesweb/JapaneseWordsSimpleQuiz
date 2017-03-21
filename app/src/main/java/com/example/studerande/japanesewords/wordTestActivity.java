package com.example.studerande.japanesewords;

import android.content.Intent;
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

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_test);

        TextView tw = (TextView) findViewById(R.id.question_tw);
        TextView tw_q = (TextView) findViewById(R.id.q_num);
        tw_q.setText("Question: #1");

        Toast.makeText(this, word2.Japanese, Toast.LENGTH_SHORT).show();

        final word current_quest_word = word;

        // Q 1
        getQuestion(current_word_num);
    }
    public void onClick(View v)
    {
        Button b = (Button)v;
        String the_btn_answer = b.getText().toString();
        String res = "";
        if(checkAnswer(the_btn_answer))
        {
            res = "correct!";
            Toast.makeText(wordTestActivity.this, res, Toast.LENGTH_SHORT).show();
            current_word_num++;
            Button nextbtn = (Button) findViewById(R.id.next_button);
            nextbtn.setText("NEXT");
            nextbtn.setEnabled(true);

        }
        else
        {
            res = "wrong!";
            Toast.makeText(wordTestActivity.this, res, Toast.LENGTH_SHORT).show();
        }
    }

    public void nextQuestion(View v)
    {
        getQuestion(current_word_num);
        setRandomOptions();
        Button nextbtn = (Button) findViewById(R.id.next_button);
        nextbtn.setText("ANSWER");
        nextbtn.setEnabled(false);
    }

    public void getQuestion(int q_num)
    {
        int whatnumberofquestion = q_num + 1 ;
        TextView tw_q = (TextView) findViewById(R.id.q_num);
        tw_q.setText("Question: #" + whatnumberofquestion);


        TextView tw = (TextView) findViewById(R.id.question_tw);

        current_word = (word) mylist.get(q_num);
        setRandomOptions();
        String Q_number = Integer.toString(q_num);
        //tw_q.setText("asadasd");
        //tw.setText(Integer.toString(mylist.size()) + " yes");
        tw.setText(current_word_num +  " : Queston: " + current_word.English + " in Japanese is?");
    }
    public Boolean checkAnswer(String jap_romaji)
    {
        if(current_word.Romaji == jap_romaji)
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

        int[] holdnums = new int[] { 1, 2, 3, 4};

        word randomw = mylist.get(0);
        word randomw2 = mylist.get(1);
        word randomw3 = mylist.get(2);
        word randomw4 = mylist.get(3);



        int first_w = 0;
        // first word check

        // put the values from each object into a string array
        String[] wordStringArr = new String[]
                {       current_word.Romaji,
                        randomw.Romaji,
                        randomw2. Romaji,
                        randomw3.Romaji,
                        randomw4.Romaji
                };

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

        // check values
        int i = 0;
        while (iterator.hasNext()){
            Log.d("Interactor: ", "Value: "+iterator.next() + " ");
        }

        wordStringArr = hs.toArray(new String[hs.size()]);

        RandomizeArray(wordStringArr);

        // set buttons
        answer_button1.setText(wordStringArr[0]);
        answer_button2.setText(wordStringArr[1]);
        answer_button3.setText(wordStringArr[2]);
        answer_button4.setText(wordStringArr[3]);

        Log.d("this is my array", "arr: " + Arrays.toString(wordStringArr));
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
