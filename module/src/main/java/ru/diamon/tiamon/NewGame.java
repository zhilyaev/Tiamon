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
        savePet();
        startActivity(intent_game);
    }
}
