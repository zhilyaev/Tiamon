package com.example.tiamon.module;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends Index {
    protected Intent intent_newgame,intent_about;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent_newgame = new Intent(this, NewGame.class);
        intent_about = new Intent(this, About.class);
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

    /* Переход в Активность: О Таймоне */
    public void AboutActivity(View view) {
        startActivity(intent_about);
    }

}
