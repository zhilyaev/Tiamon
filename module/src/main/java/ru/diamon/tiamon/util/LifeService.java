package ru.diamon.tiamon.util;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class LifeService extends Service {
    Thread life;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        life = new Thread((Runnable) ()->{

        });
    }
}
