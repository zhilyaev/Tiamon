package ru.diamon.tiamon.util;

import ru.diamon.tiamon.R;

import java.util.Date;

import static java.lang.Thread.sleep;

public class Live implements Runnable {
    private static final int U = 3000;
    private static final int FIRST_TIME = 1000*60*60*12;

    private static long getN(long S){
        return (S+2*U-4*FIRST_TIME) / (2*U);
    }

    protected void live(){

        T = new Thread(() -> {
            while (true) {
                try {sleep(PET.getLong(_NEXTTIME, 0));}
                catch (InterruptedException e) {e.printStackTrace();}

                changeStatus(_status_SLEEP, -random.nextInt(15));
                changeStatus(_status_HANGRY, -random.nextInt(14));
                changeStatus(_status_PLAY, -random.nextInt(13));

                if (isDie()) break;

                /* Время на Следущий заход */
                E = PET.edit();
                // NEXTTIME = Сейчас + Время разрыва
                E.putLong(_NEXTTIME, new Date().getTime() + PET.getLong(_TIME, FIRST_TIME) - U);
                E.putLong(_TIME, PET.getLong(_TIME, FIRST_TIME) - U);
                // AGE = (Сейчас-Родился) / 24 часа = {дни}
                E.putLong(_AGE, (new Date().getTime() - PET.getLong(_BURN, 0)) % (FIRST_TIME * 2));
                E.apply();

            }// Цикл
        });

        events = new Thread(() ->
        {
            while (true) {
                try {
                    sleep(U * 2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (random.nextInt(777) + 1 == 777) {
                    informer(String.valueOf(R.string.event_lottery));
                    E = PET.edit();
                    E.putLong(_MONEY, PET.getInt(_MONEY, 0) + 777);
                    E.apply();
                }

                if (isDie()) break;
            }// Цикл
        });

        /* Я сказала, СТАРТУЕМ! */
        T.start();
        events.start();
    }

    @Override
    public void run() {

    }
}
