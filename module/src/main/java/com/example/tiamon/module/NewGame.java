package com.example.tiamon.module;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Date;

public class NewGame extends Index {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newgame);
        gifView(R.id.GifHi,"cat_purr.gif");
    }

    public void CreateGame(View view){
        final EditText name = (EditText) findViewById(R.id.editText);
        E = PET.edit();
        E.putString(_NAME, name.getText().toString());
        E.putLong(_BURN, new Date().getTime());
        E.apply();

        startActivity(intent_game);
    }
}
