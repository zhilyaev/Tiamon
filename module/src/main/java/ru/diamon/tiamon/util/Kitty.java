package ru.diamon.tiamon.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

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
    protected long _LAST,_BIRTH,_AGE,_HARD;
    protected int _status_HANGRY,_status_SLEEP,_status_PLAY,_MONEY;

   public void savePet(){
       // ОДЗ
       if(_status_HANGRY<0)_status_HANGRY=0;
       if(_status_SLEEP < 0)_status_SLEEP=0;
       if(_status_PLAY<0)_status_PLAY = 0;
       if(_status_HANGRY>100)_status_HANGRY=100;
       if(_status_SLEEP>100)_status_SLEEP=100;
       if(_status_PLAY>100)_status_PLAY=100;

       E = PET.edit();
       E.putString("NAME",_NAME);
       E.putLong("AGE",_AGE);
       E.putInt("MONEY", _MONEY);
       E.putLong("BIRTH",_BIRTH);
       E.putLong("LAST", _LAST);
       E.putLong("HARD",_HARD);
       E.putInt("SLEEP",_status_SLEEP);
       E.putInt("PLAY",_status_PLAY);
       E.putInt("HANGRY",_status_HANGRY);
       E.apply();
   }

    public void loadPet(){
        PET = getSharedPreferences("PET", Context.MODE_PRIVATE);
        _NAME = PET.getString("NAME", "Tiamon");
        _AGE = PET.getLong("AGE",0);
        _MONEY = PET.getInt("MONEY",500);
        _BIRTH = PET.getLong("BIRTH", 0);
        _LAST = PET.getLong("LAST",0);
        _HARD = PET.getLong("HARD", 15000);
        _status_HANGRY = PET.getInt("HANGRY",42+random.nextInt(42));
        _status_SLEEP = PET.getInt("SLEEP",42+random.nextInt(42));
        _status_PLAY   = PET.getInt("PLAY",42+random.nextInt(42));
    }

    public void delPet(){
        E = PET.edit();
        E.putString("NAME","Tiamon");
        E.putLong("AGE",0);
        E.putInt("MONEY", 500);
        E.putLong("BIRTH",0);
        E.putLong("LAST", 0);
        E.putLong("HARD",15000);
        E.putInt("SLEEP",42+random.nextInt(42));
        E.putInt("PLAY",42+random.nextInt(42));
        E.putInt("HANGRY",42+random.nextInt(42));
        E.apply();
    }
}
