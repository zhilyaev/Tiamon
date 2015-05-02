package ru.diamon.tiamon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Switch;
import ru.diamon.tiamon.util.Index;
import ru.diamon.tiamon.util.SoundService;


public class Settings extends Index {

    Switch btn_sound;
    RadioGroup rg;
    ImageView digg;
    SharedPreferences APP;

    public static int complexity = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        btn_sound = (Switch) findViewById(R.id.switch_music);
        digg = (ImageView) findViewById(R.id.digview);
        rg = (RadioGroup) findViewById(R.id.group);

        btn_sound.setChecked(isSound);
        APP = getSharedPreferences("APP", Context.MODE_PRIVATE);
        complexity = APP.getInt("COMPLEXITY", 3);
        btn_sound.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) startService(new Intent(this, SoundService.class));
            else stopService(new Intent(this, SoundService.class));
            isSound = isChecked;
        });

        switch (complexity){
            case 1: digg.setImageDrawable(getResources().getDrawable(R.drawable.shadow_fly));rg.check(R.id.radioButton1);  break;
            case 2: digg.setImageDrawable(getResources().getDrawable(R.drawable.shadow_cat));rg.check(R.id.radioButton2);  break;
            case 3: digg.setImageDrawable(getResources().getDrawable(R.drawable.shadow_lion));rg.check(R.id.radioButton3); break;
        }
        rg.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (i) {
                case R.id.radioButton1:
                    complexity = 1;
                    digg.setImageDrawable(getResources().getDrawable(R.drawable.shadow_fly));
                    break;
                case R.id.radioButton2:
                    complexity = 2;
                    digg.setImageDrawable(getResources().getDrawable(R.drawable.shadow_cat));
                    break;
                case R.id.radioButton3:
                    complexity = 3;
                    digg.setImageDrawable(getResources().getDrawable(R.drawable.shadow_lion));
                    break;
                default:
                    break;
            }
            APP.edit().putInt("COMPLEXITY",complexity).apply();
        });

    }

}
