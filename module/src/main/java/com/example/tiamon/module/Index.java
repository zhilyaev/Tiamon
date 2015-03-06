package com.example.tiamon.module;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Index extends Activity {
    /* Найстройки: Основные */
    protected  static final String _PET = "PET"; // имя файла настройки [pet.xml]
    protected  static final String _NAME = "NAME"; // Имя Питомца
    protected  static final String _AGE = "AGE"; // Возраст питомца = время / (1000*60*60*24)  => [Кол-во дней]
    protected  static final String _TIME = "TIME";
    protected  static final String _MONEY = "MONEY";
    /*  Найстройки: Статусы  */
    protected  static final String _STATUS_HANGRY = "HANGRY";
    protected  static final String _STATUS_SLEEP = "SLEEP";
    protected  static final String _STATUS_PLAY = "PLAY";
    /*  Найстройки: Вещи  */
    protected  static final String _SHOP_BALL = "BALL";
    protected  static final String _SHOP_BED = "BED";
    protected  static final String _SHOP_MILK = "MILK";
    protected  static final String _SHOP_FISH = "FISH";

    SharedPreferences PET;
    SharedPreferences.Editor E;
    /* Переход Активностей */
    protected Intent intent_newgame,intent_records,intent_game;

    @Override // Определить переменные
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Показать экран загрузки
        setContentView(R.layout.activity_loading);
        PET = getSharedPreferences(_PET, Context.MODE_PRIVATE);
        intent_newgame = new Intent(this, NewGame.class);
        intent_records = new Intent(this, Records.class);
        intent_game = new Intent(this, Game.class);
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
