package com.felixboons.videoshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText usernameInput, passwordInput;
    private Button loginBtn, registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //initialise views
        usernameInput = (EditText) findViewById(R.id.username_edittext);
        passwordInput = (EditText) findViewById(R.id.password_edittext);
        loginBtn = (Button) findViewById(R.id.login_button);
        registerBtn = (Button) findViewById(R.id.register_button);

        //set listeners
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                //check input fields & credentials
                if (usernameInput.getText().toString().trim().equals("") ||
                        passwordInput.getText().toString().trim().equals("")) {
                    Toast.makeText(this, "One or more fields are empty.", Toast.LENGTH_SHORT).show();
                } else {

                }


                //save JWT in application


                //continue to home screen
//                Intent i = new Intent(this, HomeActivity.class);
//                startActivity(i);
                break;

            case R.id.register_button:
                //continue to register screen
                Intent i = new Intent(this, RegisterActivity.class);
                startActivity(i);
                break;
        }
    }
}
