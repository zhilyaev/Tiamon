package ru.diamon.tiamon;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import ru.diamon.tiamon.util.Kitty;

public class Shop extends Kitty {

    TextView cash;
    ImageButton btn_box,btn_yarn,btn_fridge;

    @Override
    protected void onStart() {
        super.onStart();
        cash.setText(String.valueOf(_MONEY));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        btn_box = (ImageButton) findViewById(R.id.btn_mysterybox);
        btn_fridge = (ImageButton) findViewById(R.id.btn_fridge);
        btn_yarn = (ImageButton) findViewById(R.id.btn_yarn);
        cash = (TextView) findViewById(R.id.cash);

        btn_box.setOnClickListener(view ->{
            _MONEY-=400;
            savePet();
        });
        btn_yarn.setOnClickListener(view ->{
            _MONEY-=200;
            savePet();
        });
    }

    @Override
    public void savePet() {
        E = PET.edit();
        E.putInt("MONEY",_MONEY);
        E.apply();

        cash.setText(String.valueOf(_MONEY));
    }
}
