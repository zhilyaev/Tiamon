package com.example.tiamon.module;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;


public class Game extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button sleep = (Button) findViewById(R.id.button8);
        sleep.setText("vvv");
    }



}
