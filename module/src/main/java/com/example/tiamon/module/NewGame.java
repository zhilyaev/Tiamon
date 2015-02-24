package com.example.tiamon.module;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;

public class NewGame extends Activity {
Intent intent_game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newgame);
    }

    /* Переход в Активность: Игра
     * Создание настроек */
    public void NewGame(View view) throws IOException {
        final EditText name = (EditText) findViewById(R.id.editText);
        Pet pet = new Pet(name.getText().toString());
        pet.Save();

        intent_game = new Intent(this, Game.class);
        startActivity(intent_game);
    }
}
