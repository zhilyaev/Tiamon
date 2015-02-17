package com.example.tiamon.module;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Main extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void NewGameActivity(View view){
        setContentView(R.layout.activity_newgame);
    }

    public void BackMenu(View view) {setContentView(R.layout.activity_main);}

    public void RecordsActivity(View view) {setContentView(R.layout.activity_records);}

    public void GameActivity(View view) {
        setContentView(R.layout.activity_game);
    }

  // protected void CloseApp(View view){  }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.about) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
