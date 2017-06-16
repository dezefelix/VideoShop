package com.felixboons.videoshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText usernameInput, firstnameInput, lastnameInput, emailInput,
            passwordInput, passwordConfirmInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initialise views
        usernameInput = (EditText) findViewById(R.id.username_edittext);
        firstnameInput = (EditText) findViewById(R.id.first_name_edittext);
        lastnameInput = (EditText) findViewById(R.id.last_name_edittext);
        emailInput = (EditText) findViewById(R.id.email_edittext);
        passwordInput = (EditText) findViewById(R.id.password_edittext);
        passwordConfirmInput = (EditText) findViewById(R.id.password_confirm_edittext);
        Button registerBtn = (Button) findViewById(R.id.register_button);

        //set listener
        registerBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (validateInputs(new EditText[]{usernameInput, firstnameInput, lastnameInput, emailInput,
                passwordInput, passwordConfirmInput}) && validatePasswords()) {

            //post data to DB using Volley


            //continue to LoginActivity
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }
    }

    //validate if input fields aren't empty
    public boolean validateInputs(EditText[] fields) {
        for (int i = 0; i < fields.length; i++) {
            EditText currentField = fields[i];
            if (currentField.getText().toString().trim().length() <= 0) {
                Toast.makeText(this, "One or more fields are empty.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    //validate if passwords are equal & within 8-20
    public boolean validatePasswords() {

        //get input values
        String password1 = passwordInput.getText().toString();
        String password2 = passwordConfirmInput.getText().toString();

        if (password1.length() < 8 ||
                password1.length() > 20) {
            Toast.makeText(this, "Password must be 8 - 20 characters.", Toast.LENGTH_SHORT).show();

            //empty password fields
            passwordInput.setText("");
            passwordConfirmInput.setText("");
            return false;

        } else if (!password1.equals(password2)) {
            Toast.makeText(this, "Passwords are not equal.", Toast.LENGTH_SHORT).show();

            //empty password fields
            passwordInput.setText("");
            passwordConfirmInput.setText("");
            return false;
        } else {
            return true;
        }
    }
}
