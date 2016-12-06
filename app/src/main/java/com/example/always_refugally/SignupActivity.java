package com.example.always_refugally;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.always_refugally.DB.User;
import com.example.always_refugally.DBDAO.UserDBDAO;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity {
    @Bind(R.id.sign_up_btn)Button btn_sign_up;
    @Bind(R.id.input_id)EditText input_id;
    @Bind(R.id.input_name)EditText input_name;
    @Bind(R.id.input_pw)EditText input_pw;

    UserDBDAO userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!input_id.getText().toString().isEmpty() &&
                        !input_name.getText().toString().isEmpty()
                        && !input_pw.getText().toString().isEmpty()){
                    User user = new User();
                    user.setUser_id(input_id.getText().toString());
                    user.setName(input_name.getText().toString());
                    user.setPw(input_pw.getText().toString());
                    userDB = new UserDBDAO(SignupActivity.this);
                    userDB.insert(user);
                    Toast.makeText(SignupActivity.this, "Sign Up Success", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }else {
                    Toast.makeText(SignupActivity.this, "Failure", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
