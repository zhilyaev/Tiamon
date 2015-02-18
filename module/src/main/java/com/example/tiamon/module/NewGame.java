package com.example.tiamon.module;


import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class NewGame extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newgame);
        Toast.makeText(this, "Happy", Toast.LENGTH_SHORT).show();
    }
}
