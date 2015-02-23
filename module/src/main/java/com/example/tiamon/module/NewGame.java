package com.example.tiamon.module;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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
        FileOutputStream fos = new FileOutputStream("pet.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        final EditText name = (EditText) findViewById(R.id.editText);
        Pet pet = new Pet(name.toString());
        /* Запись Объекта */
        oos.writeObject(pet);
        oos.flush();
        oos.close();
        // Переход
        intent_game = new Intent(this, Game.class);
        startActivity(intent_game);
    }
}
