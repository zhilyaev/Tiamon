package ru.diamon.tiamon.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;
import ru.diamon.tiamon.Game_Zone;
import ru.diamon.tiamon.Main;
import ru.diamon.tiamon.R;
import ru.diamon.tiamon.Records;

import java.util.Random;


public abstract class Index extends Activity {

    protected SharedPreferences PET;
    protected SharedPreferences.Editor E;
    protected Intent intent_records,intent_game;
    protected static Random random;

    protected String _NAME;
    protected int _HARD,_MONEY;
    protected long _LAST,_NEXT,_BIRTH,_AGE;
    protected int _status_HANGRY,_status_SLEEP,_status_PLAY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialization();
        loadPet();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override // В главное меню
    public void onBackPressed() {
        startActivity(new Intent(this, Main.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.about) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected final void gifView(int R_id_webView, String drawable){
        WebView gif; //Потому что gifDecode А) Черный-фон б)Позиционирование
        gif = (WebView) findViewById(R_id_webView);
        //webkit поддерживает <center>, так что не гунди
        String htmlText = "<html><body><center><img src='file:///android_res/drawable/"+drawable+"'/></center></body></html>";
        gif.loadDataWithBaseURL(null, htmlText, "text/html", "en_US", null);
    }

    // Показать сообщение
    public void showMsg(String text){
        new Thread((Runnable)()->{

            Toast toast = Toast.makeText(
                    getApplicationContext(),
                    text,
                    Toast.LENGTH_LONG
            );
            toast.show();
        }).start();
    }

    abstract public void savePet();

    public void loadPet(){
        PET = getSharedPreferences("PET", Context.MODE_PRIVATE);
        _NAME = PET.getString("NAME", "Tiamon");
        _BIRTH = PET.getLong("BIRTH", 0);
        _LAST = PET.getLong("LAST",0);
        _AGE = PET.getLong("AGE",0);
    }

    // захламлять код
    public void initialization(){
        intent_records = new Intent(this, Records.class);
        intent_game = new Intent(this, Game_Zone.class);
        random = new Random();
    }
}
