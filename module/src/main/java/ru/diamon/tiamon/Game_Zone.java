package ru.diamon.tiamon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Date;
import java.util.Random;

public class Game_Zone extends Index {

    TextView TV_age, TV_name,TV_catbucks;
    ImageButton btn_shop;
    ImageView ripView;
    Intent intent_shop;
    Thread live;
    WebView petView;

    static Random random;
    final int U = 3000;
    int _status_HANGRY,_status_SLEEP,_status_PLAY;
    //protected final int FIRST_TIME = 1000*60*60*12;
    final int FIRST_TIME = 12000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        random = new Random();
        loadPet();

        petView = (WebView) findViewById(R.id.PetView);
        TV_name = (TextView) findViewById(R.id.label_name);
        TV_age = (TextView) findViewById(R.id.label_age);
        TV_catbucks = (TextView) findViewById(R.id.catbucks);
        btn_shop = (ImageButton) findViewById(R.id.btn_shop);
        ripView = (ImageView) findViewById(R.id.rip);

        btn_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_shop);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(!_VIRGIN){
            // Вместо цикла
            // Сумма Отниманий  = (S + 2d-4f) / (2d) * 5
            long csum = (((new Date().getTime()-_LAST)+2*U-4*FIRST_TIME) / (2*U)) * random.nextInt(5);

            _status_HANGRY -= csum;
            _status_SLEEP -= csum;
            _status_PLAY -= csum;
        }

        upStatus();

        TV_name.setText(_NAME);
        TV_age.setText(String.valueOf(_AGE));
        TV_catbucks.setText(String.valueOf(_MONEY));

        new Thread((Runnable) ()-> {
            while(true){

            }
        }).start();
    }

    @Override
    public void loadPet() {
        super.loadPet();
        _status_HANGRY = PET.getInt("HANGRY",0);
        _status_SLEEP = PET.getInt("SLEEP",0);
        _status_PLAY = PET.getInt("PLAY",0);
    }

    public void upStatus(){
        ProgressBar sleep = (ProgressBar) findViewById(R.id.status_sleep);
        ProgressBar play = (ProgressBar) findViewById(R.id.status_play);
        ProgressBar hangry = (ProgressBar) findViewById(R.id.status_hangry);

        sleep.setProgress(_status_SLEEP);
        play.setProgress(_status_PLAY);
        hangry.setProgress(_status_HANGRY);

        if(_status_HANGRY<=0 ||_status_SLEEP<=0 ||_status_PLAY<=0){
            petView.setEnabled(false);
            btn_shop.setEnabled(false);
            ripView.setVisibility(View.VISIBLE);
        }
    }
}
