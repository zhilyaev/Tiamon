package com.example.tiamon.module;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Main extends Index {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!PET.contains(_NAME)) {
            // Если файл не создан, то кнопку "продолжить" диактивировать
            Button menu_btn1 = (Button) findViewById(R.id.menu_btn1);
            menu_btn1.setEnabled(false);
        }
    }

    /* Переход в Активность: Новая Игра */
    public void NewGameActivity(View view){
        startActivity(intent_newgame);
    }

    /* Переход в Активность: Рекорды */
    public void RecordsActivity(View view) {
        startActivity(intent_records);
    }

    /* Переход в Активность: Игра */
    public void GameActivity(View view) {
        startActivity(intent_game);
    }

}
