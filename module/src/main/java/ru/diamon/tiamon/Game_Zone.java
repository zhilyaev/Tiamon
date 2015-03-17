package ru.diamon.tiamon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import ru.diamon.tiamon.util.FieStatus;

import java.util.Date;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Game_Zone extends Index implements FieStatus {

    protected TextView TV_age, TV_name;
    protected Intent intent_shop;
    protected static Thread T,events;
    protected static Random random;
    protected final int U = 3000;
    protected final int FIRST_TIME = 1000*60*60*12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        TV_name = (TextView) findViewById(R.id.label_name);
        TV_age = (TextView) findViewById(R.id.label_age);
        TV_name.setText(PET.getString(_NAME, ""));
        TV_age.setText(PET.getString(_AGE, ""));
        random = new Random(10);
    }

    protected void live(){

        T = new Thread((Runnable) () -> {
            while (true) {
                try {sleep(PET.getLong(_NEXTTIME, FIRST_TIME));}
                catch (InterruptedException e) {e.printStackTrace();}

                changeStatus(_status_SLEEP, -random.nextInt(15));
                changeStatus(_status_HANGRY, -random.nextInt(14));
                changeStatus(_status_PLAY, -random.nextInt(13));

                if (isDie()) break;

                /* Время на Следущий заход */
                E = PET.edit();
                // NEXTTIME = Сейчас + Время разрыва
                E.putLong(_NEXTTIME, new Date().getTime() + PET.getLong(_TIME, FIRST_TIME) - U);
                E.putLong(_TIME, PET.getLong(_TIME, FIRST_TIME) - U);
                // AGE = (Сейчас-Родился) / 24 часа = {дни}
                E.putLong(_AGE, (new Date().getTime() - PET.getLong(_BURN, 0)) % (FIRST_TIME * 2));
                E.apply();

            }// Цикл
        });

        events = new Thread((Runnable) () ->
        {
            while (true) {
                try {
                    sleep(U * 2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (random.nextInt(777) + 1 == 777) {
                    informer(String.valueOf(R.string.event_lottery));
                    E = PET.edit();
                    E.putLong(_MONEY, PET.getInt(_MONEY, 0) + 777);
                    E.apply();
                }

                if (isDie()) break;
            }// Цикл
        });

        /* Я сказала, СТАРТУЕМ! */
        T.start();
        events.start();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Сейчас > NEXTTIME
        while(new Date().getTime() > PET.getLong(_NEXTTIME,new Date().getTime())){
            E = PET.edit();
            changeStatus(_status_SLEEP, -random.nextInt(15));
            changeStatus(_status_HANGRY, -random.nextInt(14));
            changeStatus(_status_PLAY, -random.nextInt(13));
            // TIME -= 3000
            int FIRST_TIME = 1000 * 60 * 60 * 12;
            int u = 1000 * 3;
            E.putLong(_TIME, PET.getLong(_TIME, FIRST_TIME)- u);
            // NEXTTIME += TIME (время разрыва)
            E.putLong(_NEXTTIME, PET.getLong(_NEXTTIME, 0)+PET.getLong(_TIME, FIRST_TIME)- u);
            E.apply();
        }

        if (!isDie()) live(); // Спасибо, что живой!

    }

    protected void shopActivity(View view){
        //intent_shop = new Intent(this,Shop.class);
        startActivity(intent_shop);
    }

    /* Если уже мертв */
    protected boolean isDie(){
        if (PET.getInt(_status_PLAY,0)==0 || PET.getInt(_status_HANGRY,0)==0 || PET.getInt(_status_SLEEP,0)==0){
            return true;
        }
        else{
            gifView(R.id.PetView,"rip.png");
            // Записать рекорд */
            return false;
        }
    }

    /* Прогресс бар */
    protected void changeStatus(final String field, int x){
        E = PET.edit();
        if (PET.getInt(field,0)+x>100){E.putInt(field, 100);}
        else{E.putInt(field, PET.getInt(field,0)+x);}
        E.apply();

        ProgressBar bar = null;
        switch (field) {
            case _status_HANGRY:
                bar = (ProgressBar) findViewById(R.id.status_hangry);
                break;
            case _status_SLEEP:
                bar = (ProgressBar) findViewById(R.id.status_sleep);
                break;
            case _status_PLAY:
                bar = (ProgressBar) findViewById(R.id.status_play);
                break;
        }

        assert bar != null;
        bar.setProgress(PET.getInt(field,0));
    }
}
