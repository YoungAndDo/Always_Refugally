package com.example.sarks.myapplication;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import kr.co.shineware.nlp.komoran.core.analyzer.Komoran;
import kr.co.shineware.util.common.model.Pair;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        tv = (TextView) findViewById(R.id.text);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String filepath = Environment.getExternalStorageDirectory().getPath();
        Komoran komoran = new Komoran(filepath + "/models");
        List<List<Pair<String,String>>> result = komoran.analyze("안녕하세요? 좋은 아침입니다.");
        for (List<Pair<String, String>> eojeolResult : result) {
            for (Pair<String, String> wordMorph : eojeolResult) {
            }
            System.out.println();
        }
    }
}
