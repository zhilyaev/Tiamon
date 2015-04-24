package ru.diamon.tiamon.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.io.File;

abstract public class Kitty extends Index {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Загрузить
        loadPet();
    }

    protected SharedPreferences PET;
    protected SharedPreferences.Editor E;

    protected String _NAME;
    protected int _HARD,_MONEY;
    protected long _LAST,_BIRTH,_AGE;
    protected int _status_HANGRY,_status_SLEEP,_status_PLAY;
    protected boolean _category_sleep,_category_play,_category_ill;

    abstract public void savePet();

    public void loadPet(){
        PET = getSharedPreferences("PET", Context.MODE_PRIVATE);
        _NAME = PET.getString("NAME", "Tiamon");
        _BIRTH = PET.getLong("BIRTH", 0);
        _LAST = PET.getLong("LAST",0);
        _AGE = PET.getLong("AGE",0);
    }

    public void delPet(){
        File file = new File("/data/data/ru.diamon.tiamon/shared_prefs/PET.xml");
        if (file.exists()){file.delete();}
        file = new File ("/data/data/ru.diamon.tiamon/shared_prefs/PET.bak");
        if (file.exists()){file.delete();}
        loadPet();
        E = PET.edit();
        E.putLong("AGE",0);
        E.apply();
    }
}
