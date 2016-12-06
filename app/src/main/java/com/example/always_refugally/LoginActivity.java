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

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.sign_in)Button sign_in;
    @Bind(R.id.sign_up)Button sign_up;
    @Bind(R.id.id)EditText login_id;
    @Bind(R.id.pw)EditText login_pw;

    UserDBDAO userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!login_id.getText().toString().isEmpty()
                        && !login_pw.getText().toString().isEmpty()){

                    userDB = new UserDBDAO(LoginActivity.this);
                    User user = userDB.selectById(login_id.getText().toString());
                    if(user != null && user.getPw().equals(login_pw.getText().toString())){
                        Toast.makeText(LoginActivity.this, "Sign In Success", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        i.putExtra("user_id", user.getUser_id());
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "Sign In Failure", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "Failure", Toast.LENGTH_LONG).show();
                }
            }
        });
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });
    }
}