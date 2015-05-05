package ru.diamon.tiamon;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import ru.diamon.tiamon.util.DatabaseHelper;
import ru.diamon.tiamon.util.Index;

public class Records extends Index {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        ListView lv = (ListView) findViewById(R.id.listView);

        DatabaseHelper dbHelper = new DatabaseHelper(this, "mydatabase.db", null, 1);
        SQLiteDatabase sdb = dbHelper.getWritableDatabase();
        String[] cols = new String[] {DatabaseHelper.CAT_NAME_COLUMN, DatabaseHelper.DATE_C, DatabaseHelper.AGE_COLUMN};
        Cursor crs = sdb.query("cats", cols, null, null,null,null,DatabaseHelper.AGE_COLUMN) ;
        String[] inlist = new String[crs.getCount()];

        crs.moveToLast();// Извращенья
        int i = 0;
        while(crs.moveToPrevious()){
            String uname = crs.getString(crs.getColumnIndex(DatabaseHelper.CAT_NAME_COLUMN));
            String uage = crs.getString(crs.getColumnIndex(DatabaseHelper.AGE_COLUMN));
            String udate = crs.getString(crs.getColumnIndex(DatabaseHelper.DATE_C));
            inlist[i] = uname + " : " +uage + " : "+ udate;
            i++;
        }


        // используем адаптер данных
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,	android.R.layout.simple_list_item_1, inlist);
        lv.setAdapter(adapter);

    }

}
