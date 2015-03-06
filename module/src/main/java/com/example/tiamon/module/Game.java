package com.example.tiamon.module;

import android.os.Bundle;
import android.widget.TextView;


public class Game extends Index {
    TextView TV_age, TV_name;
    Thread T;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        /* Установить найстройки в layout */
        TV_name = (TextView) findViewById(R.id.label_name);
        TV_age = (TextView) findViewById(R.id.label_age);
        TV_name.setText(PET.getString(_NAME, ""));
        TV_age.setText(PET.getString(_AGE, ""));
    }

}
