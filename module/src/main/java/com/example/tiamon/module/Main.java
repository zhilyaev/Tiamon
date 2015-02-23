package com.example.tiamon.module;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Main extends Activity {
    Intent intent_newgame, intent_records, intent_game, intent_about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /* Переход в Активность: Новая Игра */
    public void NewGameActivity(View view){
        intent_newgame = new Intent(this, NewGame.class);
        startActivity(intent_newgame);
    }

    /* Переход в Активность: Рекорды */
    public void RecordsActivity(View view) {
        intent_records = new Intent(this, Records.class);
        startActivity(intent_records);
    }

    /* Переход в Активность: Игра */
    public void GameActivity(View view) {
        intent_game = new Intent(this, Game.class);
        startActivity(intent_game);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        /* Переход в Активность: о Таймоне */
        if (id == R.id.about) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
