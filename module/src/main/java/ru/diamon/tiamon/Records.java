package ru.diamon.tiamon;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import ru.diamon.tiamon.util.Index;

public class Records extends Index {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        ListView lv = (ListView) findViewById(R.id.listView);

        final String[] catnames = new String[] {
                "Orange", "Bars", "Murzik", "Simba"
        };
        // используем адаптер данных
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,	android.R.layout.simple_list_item_1, catnames);
        lv.setAdapter(adapter);


    }

}
