package com.example.tiamon.module;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class Records extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        Toast.makeText(this, "Records", Toast.LENGTH_SHORT).show();
    }
}
