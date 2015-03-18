package ru.diamon.tiamon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import ru.diamon.tiamon.util.FieStatus;

import java.util.Date;
import java.util.Random;

public class Game_Zone extends Index implements FieStatus {

    protected TextView TV_age, TV_name,TV_catbucks;
    protected Intent intent_shop;
    protected static Thread T,events;
    protected static Random random;
    protected final int U = 3000;
    //protected final int FIRST_TIME = 1000*60*60*12;
    protected final int FIRST_TIME = 12000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        random = new Random();

        TV_name = (TextView) findViewById(R.id.label_name);
        TV_age = (TextView) findViewById(R.id.label_age);
        TV_catbucks = (TextView) findViewById(R.id.catbucks);

        // Ты у меня первый
        if (PET.getBoolean(_VIRGIN, false)){
            E = PET.edit();
            E.putLong(_NEXTTIME, new Date().getTime()+FIRST_TIME);
            E.putLong(_TIME, FIRST_TIME-U);
            E.putInt(_MONEY, 500);
            E.putInt(_AGE, 0);
            E.putInt(_status_SLEEP,42 + random.nextInt(42));
            E.putInt(_status_PLAY,42 + random.nextInt(42));
            E.putInt(_status_HANGRY,42 + random.nextInt(42));
            E.putBoolean(_VIRGIN, false);
            E.apply();
        }

        changeStatus(_status_SLEEP,0);
        changeStatus(_status_PLAY,0);
        changeStatus(_status_HANGRY,0);

        TV_name.setText(PET.getString(_NAME, ""));
        TV_age.setText(String.valueOf(PET.getInt(_AGE, 0)));
        TV_catbucks.setText(String.valueOf(PET.getInt(_MONEY, 500)));
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void shopActivity(View view){
        //intent_shop = new Intent(this,Shop.class);
        //startActivity(intent_shop);
        changeStatus(_status_HANGRY,-10);
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
        TextView TV = null;
        switch (field) {
            case _status_HANGRY:
                bar = (ProgressBar) findViewById(R.id.status_hangry);
                TV = (TextView) findViewById(R.id.TV_hangry);
                break;
            case _status_SLEEP:
                bar = (ProgressBar) findViewById(R.id.status_sleep);
                TV = (TextView) findViewById(R.id.TV_sleep);
                break;
            case _status_PLAY:
                bar = (ProgressBar) findViewById(R.id.status_play);
                TV = (TextView) findViewById(R.id.TV_play);
                break;
        }

        assert bar != null;
        assert TV != null;

        TV.setText(String.valueOf(PET.getInt(field,0)));
        bar.setProgress(PET.getInt(field,0));
    }
}
