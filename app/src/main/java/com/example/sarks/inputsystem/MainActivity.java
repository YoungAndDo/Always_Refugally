package com.example.sarks.inputsystem;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import com.google.zxing.client.android.integration.IntentIntegrator;
import com.google.zxing.client.android.integration.IntentResult;

public class MainActivity extends AppCompatActivity {

    Intent i;
    SpeechRecognizer mRecognizer;
    Button barcode;
    Button voice;
    TextView tv;
    private ArrayList<String> mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        barcode = (Button) findViewById(R.id.button_barcode);
        voice = (Button) findViewById(R.id.button_voice);
        tv = (TextView) findViewById(R.id.editText);

        i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR");
        i.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, "상품명을 말하세요.");

        voice.setOnClickListener(new View.OnClickListener() { // 음성인식 버튼 클릭
            @Override
            public void onClick(View view) {
                //mRecognizer.startListening(i);
                startActivityForResult(i, 1000);
                tv.setText("음성인식중");
            }
        });

        barcode.setOnClickListener(new View.OnClickListener() { // 바코드인식 버튼 클릭
            @Override
            public void onClick(View view) {
                IntentIntegrator.initiateScan(MainActivity.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1000) { // 음성 인식 결과
            String key = RecognizerIntent.EXTRA_RESULTS;
            mResult = data.getStringArrayListExtra(key);
            String[] result = new String[mResult.size()];
            mResult.toArray(result);
            tv.setText("" + result[0]);
        }
        else // 바코드 인식 결과
        {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            tv.setText(result.getContents() + " [" + result.getFormatName() + "]");
        }
    }

    @Override
    public void finish() // 어플종료시 음성인식부 종료
    {
        if(mRecognizer!= null){
            mRecognizer.destroy();
            mRecognizer = null;
        }
        super.finish();
    }
}
