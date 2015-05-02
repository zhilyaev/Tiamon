package ru.diamon.tiamon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import ru.diamon.tiamon.util.Index;

public class Loadings extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSharedPreferences("APP", Context.MODE_PRIVATE).edit().putBoolean("FIRST",false).apply();
        setContentView(R.layout.activity_loading);
        WebView gif = (WebView) findViewById(R.id.LoadingGifView);
        Index.gifView(gif,"cat_walking.gif");
        Thread t = new Thread((Runnable) ()-> {
            try {Thread.sleep(10000);}
            catch (InterruptedException e) {System.out.println("thread error");}
            startActivity(new Intent(this,Main.class));
        });
        t.start();
    }
}
