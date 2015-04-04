package ru.diamon.tiamon;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import ru.diamon.tiamon.util.Index;

import java.util.Date;

public class NewGame extends Index {
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newgame);
        gifView(R.id.gifhi,"cat_purring.gif");
        name = (EditText) findViewById(R.id.editText);
    }

    @Override
    public void savePet() {
        E = PET.edit();
        E.putString("NAME", name.getText().toString());
        E.putLong("BIRTH", new Date().getTime());
        E.putBoolean("isGame",true);
        E.apply();
    }

    public void CreateGame(View view){
        savePet();
        startActivity(intent_game);
    }
}
