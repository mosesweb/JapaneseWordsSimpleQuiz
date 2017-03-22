package com.example.studerande.japanesewords;

/**
 * Created by Studerande on 2017-03-20.
 */

public class word {
    public String Japanese = "";
    public String Reading = "";
    public String Romaji = "";
    public String English = "";

    public word()
    {

    }

    public word(String japanese, String reading, String romaji, String english)
    {
        this.Japanese = japanese;
        this.Reading = reading;
        this.Romaji = romaji;
        this.English = english;
    }
}
