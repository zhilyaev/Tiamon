package com.example.tiamon.module;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;

public class NewGame extends Activity {
Intent intent_game;
    /* Найстройки */
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_NAME = "NAME";
    SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newgame);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    /* Переход в Активность: Игра
     * Создание настроек */
    public void NewGame(View view) throws IOException {
        final EditText name = (EditText) findViewById(R.id.editText);
        String strname = name.getText().toString();

        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_NAME, strname);
        editor.apply();

        intent_game = new Intent(this, Game.class);
        startActivity(intent_game);
    }
}
