package ru.diamon.tiamon.util;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import ru.diamon.tiamon.R;
import ru.diamon.tiamon.Settings;

public class SoundService extends Service {
    MediaPlayer player;
    public static int r_id_row = R.raw.backgroud;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        if(Settings.complexity==3)r_id_row = R.raw.probuem;
        else r_id_row = R.raw.backgroud;
        
        player = MediaPlayer.create(this, r_id_row);
        player.setLooping(true);
    }

    @Override
    public void onDestroy() {
        player.stop();
    }

    @Override
    public void onStart(Intent intent, int startid) {
        player.start();
    }
}
