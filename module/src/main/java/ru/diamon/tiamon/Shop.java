package ru.diamon.tiamon;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import ru.diamon.tiamon.util.Kitty;

public class Shop extends Kitty {

    TextView cash;
    ImageButton btn_box,btn_yarn,btn_fridge;
    Animation egy;
    WebView guf;

    @Override
    protected void onStart() {
        super.onStart();
        egy = AnimationUtils.loadAnimation(this, R.anim.transformwalk);
        guf = (WebView) findViewById(R.id.walk); // uea evth
        gifView(guf,"cat_walking.gif");
        egy.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                guf.setVisibility(View.VISIBLE);
            }

            public void onAnimationEnd(Animation animation) {
                  guf.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        guf.startAnimation(egy);
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
            before(400);
            after();
        });
        btn_yarn.setOnClickListener(view ->{
            before(200);
            after();
        });
    }

    public void after() {
        E = PET.edit();
        E.putInt("MONEY",_MONEY);
        E.putBoolean("BED", _shop_BED);
        E.putInt("BALL", _shop_BALL);
        E.putInt("PERO",_shop_PERO);
        E.putInt("PAPER",_shop_PAPER);
        E.apply();

        guf.startAnimation(egy);
        cash.setText(String.valueOf(_MONEY));
    }
    public void before(int money) {
        if(_MONEY-money<0) Toast.makeText(this,R.string.toast_moneyistight,Toast.LENGTH_SHORT).show();
        else _MONEY-=money;
    }

    @Override // Назад в Меню
    public void onBackPressed() {
        startActivity(intent_game);
    }

}
