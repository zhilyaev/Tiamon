package ru.diamon.tiamon;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import ru.diamon.tiamon.util.Kitty;

import java.util.Date;

public class NewGame extends Kitty {
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newgame);
        gifView(R.id.gifhi,"cat_purring.gif");
        name = (EditText) findViewById(R.id.editText);
    }

    public void CreateGame(View view){
        delPet();
        _NAME = name.getText().toString();
        _BIRTH = new Date().getTime();
        _MONEY = _MONEY * (4 -Settings.complexity);
        _shop_FOOD = random.nextInt(4-Settings.complexity)*5;
        _shop_PERO = (4 -Settings.complexity);
        _shop_PAPER = (4 -Settings.complexity);
        _shop_BALL = (4 -Settings.complexity);
        _AGE = 1;
        savePet();
        startActivity(intent_game);
    }
}
