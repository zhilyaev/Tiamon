package com.example.tiamon.module;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Date;

public class Main extends Index {
    static Date date;

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

    public void showDate(View view) {
        Toast.makeText(this, String.valueOf(date.getTime()), Toast.LENGTH_LONG).show();
    }

}
