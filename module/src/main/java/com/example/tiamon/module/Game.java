package com.example.tiamon.module;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;


public class Game extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Toast.makeText(this, "In Game", Toast.LENGTH_SHORT).show();
    }



}
