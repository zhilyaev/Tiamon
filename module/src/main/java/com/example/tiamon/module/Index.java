package com.example.tiamon.module;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;


public class Index extends Activity {
    /* Найстройки: Основные */
    protected  static final String _PET = "PET";   // [String]  имя файла настройки [pet.xml]
    protected  static final String _NAME = "NAME"; // [String] Имя Питомца
    protected  static final String _AGE = "AGE";  // Возраст питомца = время / (1000*60*60*24)  => [Кол-во дней]
    protected  static final String _NEXTTIME = "NEXTTIME";   // [long] Время следущего захода в милисекундах
    protected  static final String _MONEY = "MONEY"; // [int] Котобаксы
    protected  static final String _TIME = "TIME"; // Время усложнения = FIRST_TIME-(U*n) , где n - кол-во заходов
    protected  static final String _BURN = "BURN"; // Дата рождения

    protected SharedPreferences PET;
    protected SharedPreferences.Editor E;
    /* Переход Активностей */
    protected Intent intent_newgame,intent_records,intent_game,intent_about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Показать экран загрузки
        setContentView(R.layout.activity_loading);
        //Toast.makeText(this, "Загрузочный экран", Toast.LENGTH_LONG).show();
        gifView(R.id.LoadingGifView, "cat_walking.gif");
        // Определить переменные
        PET = getSharedPreferences(_PET, Context.MODE_PRIVATE);
        intent_newgame = new Intent(this, NewGame.class);
        intent_records = new Intent(this, Records.class);
        intent_game = new Intent(this, Game.class);
        intent_about = new Intent(this, About.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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

    protected void gifView(int R_id_webView, String drawable){
        WebView gif; //Потому что gifDecode А) Черный-фон б)Позиционирование
        gif = (WebView) findViewById(R_id_webView);
        //webkit поддерживает <center>, так что не гунди
        String htmlText = "<html><body><center><img src='file:///android_res/drawable/"+drawable+"'/></center></body></html>";
        gif.loadDataWithBaseURL(null, htmlText, "text/html", "en_US", null);
    }

    public void informer(String text){
        Toast toast = Toast.makeText(getApplicationContext(),
                text,
                Toast.LENGTH_LONG);
        toast.show();
    }
}
