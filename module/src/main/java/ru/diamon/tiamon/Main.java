package ru.diamon.tiamon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import ru.diamon.tiamon.util.Kitty;

public class Main extends Kitty {
    protected Intent intent_newgame;
    private Button btn_continue;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_continue = (Button) findViewById(R.id.menu_continue);
        btn_continue.setOnClickListener(view -> startActivity(intent_game));
    }

    @Override
    public void initialization() {
        super.initialization();
        intent_newgame = new Intent(this, NewGame.class);
    }

    @Override
    public void savePet() {
        // Ничего не сохраняем
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Продолжить?
        if (_AGE==0) { btn_continue.setEnabled(false); }
    }

    public void NewGameActivity(View view){
        startActivity(intent_newgame);
    }
    public void RecordsActivity(View view) {
        startActivity(intent_records);
    }
    public void AboutActivity(View view) {
        startActivity(intent_about);
    }
    private int clicker = 0;
    public void egg(View view){
        ImageView egg = (ImageView) findViewById(R.id.egg);
        Animation egy = AnimationUtils.loadAnimation(this, R.anim.transformegg);
        clicker++;
        if (clicker==10){
            egg.setVisibility(View.VISIBLE);
            egg.startAnimation(egy);
        }
    }
}
