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

import java.util.Date;


public class Index extends Activity {

    protected SharedPreferences PET;
    protected SharedPreferences.Editor E;
    protected Intent intent_records,intent_game;

    String _NAME;
    boolean _VIRGIN;
    int _AGE,_HARD,_MONEY;
    long _LAST,_NEXT,_BIRTH;

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

    @Override
    protected void onPause() {
        super.onPause();
        savePet();
    }

    @Override
    protected void onStop() {
        super.onStop();
        savePet();
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

    public void showMsg(String text){
        Toast toast = Toast.makeText(getApplicationContext(),
                text,
                Toast.LENGTH_LONG);
        toast.show();
    }

    public void savePet(){
        E = PET.edit();
        E.putInt("AGE", _AGE);
        E.putLong("LAST", new Date().getTime());
        E.apply();
    }

    public void loadPet(){
        PET = getSharedPreferences("PET", Context.MODE_PRIVATE);
        // <Fixed>
        _NAME = PET.getString("NAME", "Tiamon");
        _BIRTH = PET.getLong("BIRTH", 0);
        _VIRGIN = PET.getBoolean("VIRGIN",true);

        _AGE = PET.getInt("AGE", 0);
        _LAST = PET.getLong("LAST", 0);
        _NEXT = PET.getLong("NEXT", 0);
        _HARD = PET.getInt("HARD", 0);
        _MONEY = PET.getInt("MONEY",500);
    }

    public void initialization(){
        intent_records = new Intent(this, Records.class);
        intent_game = new Intent(this, Game_Zone.class);
    }
}
