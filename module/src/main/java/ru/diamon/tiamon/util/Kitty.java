package ru.diamon.tiamon.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

abstract public class Kitty extends Index {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadPet();
    }

    protected SharedPreferences PET;
    protected SharedPreferences.Editor E;

    protected String _NAME;
    protected int _HARD,_MONEY;
    protected long _LAST,_BIRTH,_AGE;
    protected int _status_HANGRY,_status_SLEEP,_status_PLAY;

    abstract public void savePet();

    public void loadPet(){
        PET = getSharedPreferences("PET", Context.MODE_PRIVATE);
        _NAME = PET.getString("NAME", "Tiamon");
        _BIRTH = PET.getLong("BIRTH", 0);
        _LAST = PET.getLong("LAST",0);
        _AGE = PET.getLong("AGE",0);
    }
}
