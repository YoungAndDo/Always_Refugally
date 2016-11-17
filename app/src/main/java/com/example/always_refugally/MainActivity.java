package com.example.always_refugally;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] product_array = getResources().getStringArray(R.array.product_name);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, product_array);
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.search_tap);
        textView.setAdapter(adapter);
    }
}
