package ru.diamon.tiamon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Date;
import java.util.Random;

public class Game_Zone extends Index {
    /* Найстройки: Статусы [INT] ∈ [0;100] */
    protected final String _STATUS_HANGRY = "HANGRY";
    protected final String _STATUS_SLEEP = "SLEEP";
    protected final String _STATUS_PLAY = "PLAY";
/* Найстройки: Состояние [BOOLEAN]
protected final String _SOST_SLEEP = "SLEEPING";
protected final String _SOST_SICK = "SICKING";*/
// diying diving loving living pulling pushing
/* Найстройки: Вещи [INT] ∈ [0;∞)
protected final String _SHOP_BALL = "BALL";
protected final String _SHOP_BED = "BED";
protected final String _SHOP_MILK = "MILK";
protected final String _SHOP_FISH = "FISH";*/

    protected TextView TV_age, TV_name;
    protected Intent intent_shop;
    //protected static Thread T,events;
    protected static Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
/* Установить найстройки в layout */
        TV_name = (TextView) findViewById(R.id.label_name);
        TV_age = (TextView) findViewById(R.id.label_age);
        TV_name.setText(PET.getString(_NAME, "Tiamon-b"));
        TV_age.setText(PET.getString(_AGE, "0"));
        random = new Random(10);
    }

    protected void live(){
// Муахаха ЛЯМБДА
    }

    @Override
    protected void onStart() {
        super.onStart();

// Сейчас > NEXTTIME
        while(new Date().getTime() > PET.getLong(_NEXTTIME,new Date().getTime())){
            E = PET.edit();
/* Изменить СТАТУСЫ */
            changeStatus(_STATUS_SLEEP, -random.nextInt(15));
            changeStatus(_STATUS_HANGRY, -random.nextInt(14));
            changeStatus(_STATUS_PLAY, -random.nextInt(13));
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

    /* Переход в Активность: Магазин */
    protected void shopActivity(View view){
        //intent_shop = new Intent(this,Shop.class);
        startActivity(intent_shop);
    }

    /* Если уже мертв */
    protected boolean isDie(){
        if (PET.getInt(_STATUS_PLAY,0)==0 || PET.getInt(_STATUS_HANGRY,0)==0 || PET.getInt(_STATUS_SLEEP,0)==0){
            return true;
        }
        else{
            gifView(R.id.PetView,"rip.png");
/* Записать рекорд */
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
            case _STATUS_HANGRY:
                bar = (ProgressBar) findViewById(R.id.status_hangry);
                break;
            case _STATUS_SLEEP:
                bar = (ProgressBar) findViewById(R.id.status_sleep);
                break;
            case _STATUS_PLAY:
                bar = (ProgressBar) findViewById(R.id.status_play);
                break;
        }

        assert bar != null;
        bar.setProgress(PET.getInt(field,0));
    }
}
