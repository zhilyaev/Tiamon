package ru.diamon.tiamon;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.webkit.WebView;
import ru.diamon.tiamon.util.DatabaseHelper;
import ru.diamon.tiamon.util.Index;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Loadings extends Activity {
    DatabaseHelper dbHelper;
    SQLiteDatabase sdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        WebView gif = (WebView) findViewById(R.id.LoadingGifView);
        Index.gifView(gif,"cat_walking.gif");

        dbHelper = new DatabaseHelper(this, "mydatabase.db", null, 1);
        sdb = dbHelper.getWritableDatabase();

        ContentValues newValues = new ContentValues();

        SimpleDateFormat dateFormat = new SimpleDateFormat();
        // Задайте значения для каждой строки.
        newValues.put(DatabaseHelper.CAT_NAME_COLUMN, "log");
        newValues.put(DatabaseHelper.DATE_C, String.valueOf(dateFormat.format(new Date())));
        newValues.put(DatabaseHelper.AGE_COLUMN, "Game created");
        // Вставляем данные в базу
        sdb.insert("cats", null, newValues);

        getSharedPreferences("APP", Context.MODE_PRIVATE).edit().putBoolean("FIRST",false).apply();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Thread t = new Thread(()-> {
            try {Thread.sleep(6000);}
            catch (InterruptedException e) {System.out.println("thread error");}
            startActivity(new Intent(this,Main.class));
        });
        t.start();
    }
}
