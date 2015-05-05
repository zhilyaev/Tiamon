package ru.diamon.tiamon;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.*;
import ru.diamon.tiamon.util.Kitty;

public class Shop extends Kitty implements View.OnClickListener {

    private TextView cash,sfood,sbed,sball,spero,spaper;
    private Animation egy;
    private WebView guf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        cash = (TextView) findViewById(R.id.cash);
        sball = (TextView) findViewById(R.id.sball);
        sfood = (TextView) findViewById(R.id.sfood);
        spero = (TextView) findViewById(R.id.spero);
        spaper = (TextView) findViewById(R.id.spaper);
        sbed =(TextView) findViewById(R.id.sbed);

        int[] btns = {R.id.btn_bed,R.id.btn_mysterybox,R.id.btn_fridge,R.id.btn_ball,R.id.btn_paper,R.id.btn_yarn};
        for(int btn: btns){
            ImageButton view = (ImageButton) findViewById(btn);
            view.setOnClickListener(this);
        }
        egy = AnimationUtils.loadAnimation(this, R.anim.transformwalk);
        guf = (WebView) findViewById(R.id.walk); // uea evth
        gifView(guf, "cat_walking.gif");
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
        after();

    }

    public void after() {
        savePet();

        handler.post((Runnable) ()->{
            guf.startAnimation(egy);
            sball.setText(String.valueOf(_shop_BALL));
            sfood.setText(String.valueOf(_shop_FOOD));
            spero.setText(String.valueOf(_shop_PERO));
            spaper.setText(String.valueOf(_shop_PAPER));
            if(_shop_BED) sbed.setText("+");
            else sbed.setText("-");
            cash.setText(String.valueOf(_MONEY));
        });
    }
    public boolean before(int money) {
        if(_MONEY-money<0){Toast.makeText(this,R.string.toast_moneyistight,Toast.LENGTH_SHORT).show(); return false;}
        else{ _MONEY-=money; return true;}
    }

    @Override // Назад в Меню
    public void onBackPressed() {
        startActivity(intent_game);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ball:
                if(before(200)) _shop_BALL+=3;
                break;
            case R.id.btn_paper:
                if(before(50))
                _shop_PAPER+=2;
                break;
            case R.id.btn_yarn:
                if ( before(300))
                _shop_PERO+=7;
                break;
            case R.id.btn_bed:
                if (before(400) )
                _shop_BED = true;
                break;
            case R.id.btn_mysterybox:
                if (before(400)) {
                    ImageView q = (ImageView) findViewById(R.id.q);
                    LinearLayout lay = (LinearLayout) findViewById(R.id.btns);
                    Animation anim = AnimationUtils.loadAnimation(this, R.anim.q);
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            q.setVisibility(View.VISIBLE);
                            lay.setVisibility(View.GONE);
                        }

                        public void onAnimationEnd(Animation animation) {
                            q.setVisibility(View.GONE);
                            lay.setVisibility(View.VISIBLE);

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    q.startAnimation(anim);
                    switch(random.nextInt(10)){
                        case 1:
                            _shop_PAPER+=2;
                            break;
                        case 2:
                            _shop_BALL +=2;
                            break;
                        case 3:
                            _shop_PAPER+=3;
                            break;
                        case 4:
                            _shop_PERO +=4;
                            break;
                        case 5:
                            _shop_FOOD+=random.nextInt(3)+2;
                            break;
                        default: Toast.makeText(this,R.string.toast_loose,Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btn_fridge:
                if (before(100))
                _shop_FOOD+=6;
                break;
        }
        after();
    }
}
