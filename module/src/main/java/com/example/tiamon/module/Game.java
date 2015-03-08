package com.example.tiamon.module;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;
import java.util.Random;

import static java.lang.Thread.sleep;


public class Game extends Index{
    TextView TV_age, TV_name;
    static Thread T;
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
            public void run()
            {


                while(true){
                    try {sleep(PET.getLong(_NEXTTIME,FIRST_TIME));}
                    catch (InterruptedException e) {e.printStackTrace();}

                    E = PET.edit();
                    E.putInt(_STATUS_SLEEP, PET.getInt(_STATUS_SLEEP,new Random(5*2).nextInt(100))-random.nextInt(5));
                    E.putInt(_STATUS_HANGRY, PET.getInt(_STATUS_HANGRY,new Random(4*2).nextInt(100))-random.nextInt(4));
                    E.putInt(_STATUS_PLAY, PET.getInt(_STATUS_PLAY,new Random(3*2).nextInt(100))-random.nextInt(3));
                    E.apply();


                }

            }
        });

       T.start();
    }

    @Override
    protected void onStart() {
        super.onStart();

        while(date.getTime() > PET.getLong(_NEXTTIME,0)){
            E = PET.edit();
            E.putInt(_STATUS_HANGRY, PET.getInt(_STATUS_HANGRY,random.nextInt(100))-random.nextInt(5));
            E.putInt(_STATUS_SLEEP, PET.getInt(_STATUS_SLEEP,random.nextInt(100))-random.nextInt(4));
            E.putInt(_STATUS_PLAY, PET.getInt(_STATUS_PLAY,random.nextInt(100))-random.nextInt(3));
            E.putLong(_NEXTTIME, PET.getLong(_NEXTTIME, 0)+PET.getLong(_TIME,FIRST_TIME)-U);
            E.putLong(_TIME, PET.getLong(_TIME,FIRST_TIME)-U);
            E.apply();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        E = PET.edit();
        E.apply();
    }
}
