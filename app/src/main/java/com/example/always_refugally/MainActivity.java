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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.zxing.client.android.integration.IntentIntegrator;
import com.google.zxing.client.android.integration.IntentResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    @Bind(R.id.List1_layout)
    RelativeLayout list1;

    @Bind(R.id.List2_layout)
    RelativeLayout list2;

    @Bind(R.id.List3_layout)
    RelativeLayout list3;

    @Bind(R.id.list1_plus)
    ImageButton list1_plus;

    @Bind(R.id.list1_minus)
    ImageButton list1_minus;

    @Bind(R.id.list1_name)
    TextView list1_name;

    @Bind(R.id.list1_count)
    TextView list1_count;

    @Bind(R.id.list1_pic)
    ImageView list1_pic;

    @Bind(R.id.list2_plus)
    ImageButton list2_plus;

    @Bind(R.id.list2_minus)
    ImageButton list2_minus;

    @Bind(R.id.list2_name)
    TextView list2_name;

    @Bind(R.id.list2_count)
    TextView list2_count;

    @Bind(R.id.list2_pic)
    ImageView list2_pic;

    @Bind(R.id.list3_plus)
    ImageButton list3_plus;

    @Bind(R.id.list3_minus)
    ImageButton list3_minus;

    @Bind(R.id.list3_name)
    TextView list3_name;

    @Bind(R.id.list3_count)
    TextView list3_count;

    @Bind(R.id.list3_pic)
    ImageView list3_pic;



    Intent i;
    SpeechRecognizer mRecognizer;
    ArrayList<String> mResult;
    Map<String,Integer> item;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        list1.setVisibility(View.GONE);
        list2.setVisibility(View.GONE);
        list3.setVisibility(View.GONE);
        item = new HashMap<String,Integer>();
        count = 0;



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
                putitem();
//                i = new Intent(MainActivity.this, ListActivity.class);
//
//                i.putExtra("name", editText.getText().toString());
//                startActivity(i);
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

        list1_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tmp;
                int count;
                tmp = list1_count.getText().toString();
                count = Integer.parseInt(tmp);
                count++;
                item.put(list1_name.getText().toString(),count);
                tmp = String.valueOf(count);
                list1_count.setText(tmp);
            }
        });
        list1_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tmp;
                int c;
                tmp = list1_count.getText().toString();
                c = Integer.parseInt(tmp);
                c--;
                item.put(list1_name.getText().toString(),c);
                tmp = String.valueOf(c);
                list1_count.setText(tmp);
                if(c == 0)
                {
                    item.remove(list1_name.getText().toString());
                    if(count == 3)
                    {
                        list3.setVisibility(View.GONE);
                        list1_name.setText(list2_name.getText().toString());
                        list1_count.setText(list2_count.getText().toString());
                        list2_name.setText(list3_name.getText().toString());
                        list2_count.setText(list3_count.getText().toString());
                    }
                    else if(count == 2)
                    {
                        list2.setVisibility(View.GONE);
                        list1_name.setText(list2_name.getText().toString());
                        list1_count.setText(list2_count.getText().toString());
                    }
                    else
                    {
                        list1.setVisibility(View.GONE);
                    }
                    count--;
                }
            }
        });
        list2_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tmp;
                int count;
                tmp = list2_count.getText().toString();
                count = Integer.parseInt(tmp);
                count++;
                item.put(list2_name.getText().toString(),count);
                tmp = String.valueOf(count);
                list2_count.setText(tmp);
            }
        });
        list2_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tmp;
                int c;
                tmp = list2_count.getText().toString();
                c = Integer.parseInt(tmp);
                c--;
                item.put(list2_name.getText().toString(),c);
                tmp = String.valueOf(c);
                list2_count.setText(tmp);
                if(c == 0)
                {
                    item.remove(list2_name.getText().toString());
                    if(count == 3)
                    {
                        list3.setVisibility(View.GONE);
                        list2_name.setText(list3_name.getText().toString());
                        list2_count.setText(list3_count.getText().toString());
                    }
                    else
                    {
                        list2.setVisibility(View.GONE);
                    }
                    count--;
                }
            }
        });
        list3_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tmp;
                int count;
                tmp = list3_count.getText().toString();
                count = Integer.parseInt(tmp);
                count++;
                item.put(list3_name.getText().toString(),count);
                tmp = String.valueOf(count);
                list3_count.setText(tmp);
            }
        });
        list3_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tmp;
                int c;
                tmp = list3_count.getText().toString();
                c = Integer.parseInt(tmp);
                c--;
                item.put(list3_name.getText().toString(),c);
                tmp = String.valueOf(c);
                list3_count.setText(tmp);
                if(c == 0)
                {
                    item.remove(list3_name.getText().toString());
                    list3.setVisibility(View.GONE);
                    count--;
                }
            }
        });

    }


    void putitem()
    {
        String name = editText.getText().toString();
        item.put(name, 1);
        if(count == 0)
        {
            list1.setVisibility(View.VISIBLE);
            list1_name.setText(name);
            list1_count.setText("1");
            count++;
        }
        else if(count == 1)
        {
            list2.setVisibility(View.VISIBLE);
            list2_name.setText(name);
            list2_count.setText("1");
            count++;
        }
        else if(count == 2)
        {
            list3.setVisibility(View.VISIBLE);
            list3_name.setText(name);
            list3_count.setText("1");
            count++;
        }
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
