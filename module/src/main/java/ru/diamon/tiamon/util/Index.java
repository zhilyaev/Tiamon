package ru.diamon.tiamon.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import ru.diamon.tiamon.*;

import java.util.Random;


public abstract class Index extends Activity {

    protected Intent intent_records,intent_game,intent_now,intent_about;
    protected static Random random;
    protected Handler handler;
    protected static boolean isSound;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Если Музыка Играет, то выключить службу фонового звука.
        if (isSound) stopService(new Intent(this, SoundService.class));
    }

    protected void initialization(){
        intent_records = new Intent(this, Records.class);
        intent_game = new Intent(this, Game_Zone.class);
        intent_about = new Intent(this, About.class);
        random = new Random();
        handler = new Handler();
        intent_now = getIntent();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialization();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override // Назад в Меню
    public void onBackPressed() {
        startActivity(new Intent(this, Main.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.about){
            startActivity(intent_about);
        }
        return id == R.id.about || super.onOptionsItemSelected(item);
    }

    public void gifView(int R_id_webView, String drawable){
        WebView gif; //Потому что gifDecode А) Черный-фон б)Позиционирование
        gif = (WebView) findViewById(R_id_webView);
        gifView(gif,drawable);
    }
    public static void gifView(WebView gif,String drawable){
        //webkit поддерживает <center>, так что не гунди
        String htmlText = "<html><body><center><img src='file:///android_res/drawable/"+drawable+"'/></center></body></html>";
        gif.loadDataWithBaseURL(null, htmlText, "text/html", "en_US", null);
    }

    public void soul(View view){
        startActivity(new Intent(this,Settings.class));
    }

}
