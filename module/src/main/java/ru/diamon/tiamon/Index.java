package ru.diamon.tiamon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;
import ru.diamon.tiamon.util.FieBasic;


public class Index extends Activity implements FieBasic {

    protected SharedPreferences PET;
    protected SharedPreferences.Editor E;
    protected Intent intent_records,intent_game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Показать экран загрузки
        //setContentView(R.layout.activity_loading);
        //Toast.makeText(this, "Загрузочный экран", Toast.LENGTH_LONG).show();
        //gifView(R.id.LoadingGifView, "cat_walking.gif");

        // Определить переменные
        PET = getSharedPreferences(_PET, Context.MODE_PRIVATE);

        intent_records = new Intent(this, Records.class);
        intent_game = new Intent(this, Game_Zone.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // Переход в Активность: о Таймоне */
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

    /* Свой тост */
    public void informer(String text){
        Toast toast = Toast.makeText(getApplicationContext(),
                text,
                Toast.LENGTH_LONG);
        toast.show();
    }
}
