package com.example.tiamon.module;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;


public class Game extends Activity {
    /* Найстройки */
    private  static final String APP_PREFERENCES = "mysettings"; // имя файла настройки [mysettings.xml]
    private  static final String APP_PREFERENCES_NAME = "NAME"; // Имя Питомца
    private  static final String APP_PREFERENCES_AGE = "AGE"; // Возраст питомца = время / (1000*60*60*24)  => [Кол-во дней]
    private  static final String APP_PREFERENCES_TIME = "TIME"; // Время в милисекундах со дня рождения
    /* Поля */
    TextView age, name;
    SharedPreferences mSettings;
    /* toogle */
    private static boolean TOOGLE = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        name = (TextView) findViewById(R.id.label_name);
        age = (TextView) findViewById(R.id.label_age);

        name.setText(mSettings.getString(APP_PREFERENCES_NAME, ""));
        age.setText(mSettings.getString(APP_PREFERENCES_AGE, ""));
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(APP_PREFERENCES_AGE, age.getText().toString());
        editor.apply();
    }
}
