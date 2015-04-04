package ru.diamon.tiamon;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Records extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        // получаем экземпляр элемента ListView
        ListView lv = (ListView) findViewById(R.id.listView);

        final String[] catnames = new String[] {
                "Рыжик", "Барсик", "Мурзик", "Мурка", "Васька",
                "Томасина", "Кристина", "Пушок", "Дымка", "Кузя",
                "Китти", "Масяня", "Симба"
        };
        // используем адаптер данных
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,	android.R.layout.simple_list_item_1, catnames);

        lv.setAdapter(adapter);
    }
}
