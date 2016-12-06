package com.example.always_refugally;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.zxing.client.android.integration.IntentIntegrator;
import com.google.zxing.client.android.integration.IntentResult;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.my_toolbar)
    Toolbar myToolbar;

    @Bind(R.id.search_tap)
    EditText editText;

    @Bind(R.id.user_name)
    TextView name;

    @Bind(R.id.location)
    TextView location;

    @Bind(R.id.button)
    Button btn_search;

    @Bind(R.id.setLocation)
    Button btn_location;

    @Bind(R.id.button_voice)
    Button voice;

    @Bind(R.id.button_barcode)
    Button barcode;

    Intent i;
    SpeechRecognizer mRecognizer;
    ArrayList<String> mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(myToolbar);

        i = getIntent();
        String my_addr = i.getExtras().getString("my_address");
        String user_name = i.getExtras().getString("name");
        if(my_addr != null){
            location.setText(my_addr);
        }
        if(user_name != null){
            name.setText(user_name);
        }

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(MainActivity.this, ListActivity.class);

                i.putExtra("name", editText.getText().toString());
                startActivity(i);
            }
        });

        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(MainActivity.this, MapActivity.class);
                startActivity(i);
            }
        });

        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                i.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR");
                i.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
                i.putExtra(RecognizerIntent.EXTRA_PROMPT, "상품명을 말하세요.");

                startActivityForResult(i, 1000);
            }
        });

        barcode.setOnClickListener(new View.OnClickListener() {
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
            if(data == null)
            {
                return;
            }
            mResult = data.getStringArrayListExtra(key);
            String[] result = new String[mResult.size()];
            mResult.toArray(result);
            editText.setText("" + result[0]);
        }
        else // 바코드 인식 결과
        {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

            String barcode = result.getContents();
            if(barcode.equals("10000")) {
                editText.setText("빼빼로");
            }else if(barcode.equals("20000")){
                editText.setText("콜라");
            }
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
