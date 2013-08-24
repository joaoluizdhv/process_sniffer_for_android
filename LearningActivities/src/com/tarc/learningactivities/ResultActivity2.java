package com.tarc.learningactivities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
 
public class ResultActivity2 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
 
        Bundle b = getIntent().getExtras();
        String[] resultArr = b.getStringArray("selectedItems");
        ListView lv = (ListView) findViewById(R.id.outputList);
 
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, resultArr);
        lv.setAdapter(adapter);
    }
}