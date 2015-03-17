package ru.diamon.tiamon;

import android.app.Activity;
import android.os.Bundle;
import ru.diamon.tiamon.util.FieRecords;

public class Records extends Activity implements FieRecords {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
    }
}
