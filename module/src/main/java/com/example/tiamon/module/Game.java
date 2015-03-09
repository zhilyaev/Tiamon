package com.example.tiamon.module;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Date;
import java.util.Random;

import static java.lang.Thread.sleep;


public class Game extends Index{
    /*  Найстройки: Статусы [INT] ∈ [0;100]  */
    protected  static final String _STATUS_HANGRY = "HANGRY";
    protected  static final String _STATUS_SLEEP = "SLEEP";
    protected  static final String _STATUS_PLAY = "PLAY";
    /*  Найстройки: Вещи [INT] ∈ [0;∞)  */
    protected  static final String _SHOP_BALL = "BALL";
    protected  static final String _SHOP_BED = "BED";
    protected  static final String _SHOP_MILK = "MILK";
    protected  static final String _SHOP_FISH = "FISH";

    TextView TV_age, TV_name;
    Intent intent_shop;
    static Thread T,events;
    static Random random;
    static Date date;
    final int U = 1000*3; // Скорость убывания
    final int FIRST_TIME = 1000*60*60*12; // Через 12 часов первый заход

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        /* Установить найстройки в layout */
        TV_name = (TextView) findViewById(R.id.label_name);
        TV_age = (TextView) findViewById(R.id.label_age);
        TV_name.setText(PET.getString(_NAME, ""));
        TV_age.setText(PET.getString(_AGE, ""));
        random = new Random(1);
        date = new Date();

    }

    protected void live(){
        // Побочный поток
        T = new Thread(new Runnable()
        {
            public void run() {
                while(true){
                    try {sleep(PET.getLong(_NEXTTIME,FIRST_TIME));}
                    catch (InterruptedException e) {e.printStackTrace();}

                    /* Изменить СТАТУСЫ */
                    E = PET.edit();
                    E.putInt(_STATUS_SLEEP, PET.getInt(_STATUS_SLEEP,new Random(5*2).nextInt(100))-random.nextInt(5));
                    E.putInt(_STATUS_HANGRY, PET.getInt(_STATUS_HANGRY,new Random(4*2).nextInt(100))-random.nextInt(4));
                    E.putInt(_STATUS_PLAY, PET.getInt(_STATUS_PLAY,new Random(3*2).nextInt(100))-random.nextInt(3));
                    E.apply();

                    // Умри
                    if (isDie()) break;

                    /* Следущий заход */
                    E = PET.edit();
                    E.putLong(_NEXTTIME, date.getTime()+PET.getLong(_TIME,FIRST_TIME)-U);
                    E.putLong(_TIME, PET.getLong(_TIME,FIRST_TIME)-U);
                    E.apply();

                }// Цикл

            }
        });

        events = new Thread(new Runnable()
        {
            public void run() {
                while(true){
                    try {sleep(U*2);}
                    catch (InterruptedException e) {e.printStackTrace();}
                    if (random.nextInt(777)==777){
                        // ВЫ выиграли в лотерею
                    }

                    // Умри
                    if (isDie()) break;
                }// Цикл

            }
        });

       /* Я сказала, СТАРТУЕМ! */
       T.start();
       events.start();
    }

    @Override
    protected void onStart() {
        super.onStart();

        while(date.getTime() > PET.getLong(_NEXTTIME,0)){
            E = PET.edit();
            E.putInt(_STATUS_SLEEP, PET.getInt(_STATUS_SLEEP,new Random(5*2).nextInt(100))-random.nextInt(5));
            E.putInt(_STATUS_HANGRY, PET.getInt(_STATUS_HANGRY,new Random(4*2).nextInt(100))-random.nextInt(4));
            E.putInt(_STATUS_PLAY, PET.getInt(_STATUS_PLAY,new Random(3*2).nextInt(100))-random.nextInt(3));
            E.putLong(_NEXTTIME, PET.getLong(_NEXTTIME, 0)+PET.getLong(_TIME,FIRST_TIME)-U);
            E.putLong(_TIME, PET.getLong(_TIME,FIRST_TIME)-U);
            E.apply();
        }

    }

    /* Переход в Активность: Магазин */
    protected void shopActivity(View view){
        intent_shop = new Intent(this,Shop.class);
        startActivity(intent_shop);
    }

    protected boolean isDie(){
        if (PET.getInt(_STATUS_PLAY,0)==0 || PET.getInt(_STATUS_HANGRY,0)==0 || PET.getInt(_STATUS_SLEEP,0)==0){
            return true;
        }else{return false;}
    }
}
