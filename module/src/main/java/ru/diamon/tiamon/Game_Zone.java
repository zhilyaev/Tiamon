package ru.diamon.tiamon;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.*;
import ru.diamon.tiamon.util.DatabaseHelper;
import ru.diamon.tiamon.util.Kitty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Game_Zone extends Kitty {

    TextView tv_age, tv_name,tv_catbucks,tv_sleep,tv_play,tv_hangry;
    ImageButton btn_shop,btn_sleep,btn_feed,btn_play;
    Button btn_save;
    ImageView ripView;
    Intent intent_shop;
    ProgressBar bar_sleep,bar_play,bar_hangry;
    WebView petView;
    Thread life,events;

    private String bd_age,bd_name;

    private int U;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        U = Settings.complexity*3000;

        // Инициализация вьюшек
        petView = (WebView) findViewById(R.id.PetView);
        tv_name = (TextView) findViewById(R.id.label_name);
        tv_age = (TextView) findViewById(R.id.label_age);
        tv_catbucks = (TextView) findViewById(R.id.catbucks);
        btn_shop = (ImageButton) findViewById(R.id.btn_shop);
        ripView = (ImageView) findViewById(R.id.rip);
        tv_sleep = (TextView) findViewById(R.id.TV_sleep);
        tv_play = (TextView) findViewById(R.id.TV_play);
        tv_hangry = (TextView) findViewById(R.id.TV_hangry);
        bar_sleep = (ProgressBar) findViewById(R.id.bar_sleep);
        bar_play = (ProgressBar) findViewById(R.id.bar_play);
        bar_hangry = (ProgressBar) findViewById(R.id.bar_hangry);
        btn_sleep = (ImageButton) findViewById(R.id.btn_sleep);
        btn_feed = (ImageButton) findViewById(R.id.btn_feed);
        btn_play = (ImageButton) findViewById(R.id.btn_play);
        btn_save = (Button) findViewById(R.id.btn_save);
        // Клики
        // В рекорды
        btn_save.setOnClickListener(view -> {
            DatabaseHelper dbHelper = new DatabaseHelper(this, "mydatabase.db", null, 1);
            SQLiteDatabase sdb = dbHelper.getWritableDatabase();
            ContentValues newValues = new ContentValues();

            SimpleDateFormat dateFormat = new SimpleDateFormat();
            // Задайте значения для каждой строки.
            newValues.put(DatabaseHelper.CAT_NAME_COLUMN, bd_name);
            newValues.put(DatabaseHelper.DATE_C, String.valueOf(dateFormat.format(new Date())));
            newValues.put(DatabaseHelper.AGE_COLUMN, bd_age);
            // Вставляем данные в базу
            sdb.insert("cats", null, newValues);
            view.setVisibility(View.GONE);
            Toast.makeText(this,"OK",Toast.LENGTH_SHORT).show();
        });
        btn_sleep.setOnClickListener(view -> {
            _status_SLEEP+=random.nextInt(20);
            gifView(petView,"sleep.gif");
            MediaPlayer mp3 = MediaPlayer.create(this, R.raw.sleep);
            mp3.start();
            savePet();
            bar_sleep.setProgress(_status_SLEEP);
            tv_sleep.setText(String.valueOf(_status_SLEEP));
            after(mp3.getDuration()/1000+2);
        });
        btn_feed.setOnClickListener(view -> {
            _status_HANGRY+=random.nextInt(20);
            gifView(petView,"alarm.gif");
            savePet();
            tv_hangry.setText(String.valueOf(_status_HANGRY));
            bar_hangry.setProgress(_status_HANGRY);
            after(10);
        });
        btn_play.setOnClickListener(view -> {
            _status_PLAY+=random.nextInt(20);
            gifView(petView,"hihi.gif");
            MediaPlayer mp3 = MediaPlayer.create(this, R.raw.lol);
            mp3.start();
            savePet();
            tv_play.setText(String.valueOf(_status_PLAY));
            bar_play.setProgress(_status_PLAY);
            after(mp3.getDuration());
        });
        btn_shop.setOnClickListener(view -> startActivity(intent_shop));
        ripView.setOnClickListener(view -> startActivity(intent_records));
        // Определение основных потоков
        life = new Thread((Runnable) ()->{
            while (!(_status_HANGRY==0 || _status_SLEEP==0 || _status_PLAY==0)) {
                _AGE = (new Date().getTime()-_BIRTH)/1000;
                _status_PLAY -= random.nextInt(10);
                _status_HANGRY -= random.nextInt(10);
                _status_SLEEP -= random.nextInt(10);
                // Усложнить
                if(_HARD>U){_HARD -= U;}
                // Сохранить
                savePet();
                //Загрузить
                loadPet();
                // Обновить
                updateLayout();
                // Костыль, чтобы не спать
                if(_status_HANGRY==0 || _status_SLEEP==0 || _status_PLAY==0) break;
                // Уснуть
                try {Thread.sleep(_HARD);}
                catch (InterruptedException e) {System.out.println("thread error");}
            }
            // Удалить Питомца
            delPet();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        tv_name.setText(_NAME);
        gifView(petView,"hi.gif");
        // Уже играли
        if(_LAST!=0){
            // Сумма Отниманий  = (S + 2d-4f) / (2d) * 10
            long csum = (((new Date().getTime()-_LAST)+2*U-4*15000) / (2*U)) * random.nextInt(10);
            _status_HANGRY -= csum;
            _status_SLEEP -= csum;
            _status_PLAY -= csum;
            _LAST = 0;
        }
        savePet();
        updateLayout();
        life.start(); // Я сказада, стартуем!
    }

    public void updateLayout(){
        handler.post((Runnable)()->{
            // Сменить Кнопку кормления
            if(_status_HANGRY<=20) btn_feed.setImageDrawable(getResources().getDrawable(R.drawable.cat_hungry));
            else btn_feed.setImageDrawable(getResources().getDrawable(R.drawable.cat_food));
            // Обновить вьюшки
            tv_sleep.setText(String.valueOf(_status_SLEEP));
            tv_play.setText(String.valueOf(_status_PLAY));
            tv_hangry.setText(String.valueOf(_status_HANGRY));
            bar_sleep.setProgress(_status_SLEEP);
            bar_play.setProgress(_status_PLAY);
            bar_hangry.setProgress(_status_HANGRY);
            tv_catbucks.setText(String.valueOf(_MONEY));
            tv_age.setText(String.valueOf(_AGE));
            // Относительно Статусов
            if(_status_HANGRY==0 || _status_SLEEP==0 || _status_PLAY==0){
                ripView.setVisibility(View.VISIBLE);
                petView.setVisibility(View.GONE);
                btn_shop.setEnabled(false);
                btn_feed.setEnabled(false);
                btn_sleep.setEnabled(false);
                btn_play.setEnabled(false);
                bd_age = String.valueOf(_AGE);
                bd_name = _NAME;
                btn_save.setVisibility(View.VISIBLE);
            }else if(_status_HANGRY+_status_PLAY+_status_SLEEP>=270){
                _MONEY+=1000;
                gifView(petView,"love.gif");
            }else if((_status_HANGRY+_status_PLAY+_status_SLEEP<=30) || _status_HANGRY<=10 || _status_PLAY<=10 || _status_SLEEP<=10){
                gifView(petView,"sorry.gif");
            }else{
                gifView(petView,"hi.gif");
            }
        });
    }

    private void after(int t){
        Thread bug = new Thread((Runnable)()->{
            try {Thread.sleep(t*1000);}
            catch (InterruptedException e) {System.out.println("thread error");}
            updateLayout();
        });
        bug.start();
    }

    @Override
    protected void initialization() {
        super.initialization();
        intent_shop = new Intent(this, Shop.class);
    }
}
