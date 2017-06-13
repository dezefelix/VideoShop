package com.felixboons.videoshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.felixboons.videoshop.Volley.MyJSONObjectRequest;
import com.felixboons.videoshop.Volley.MyVolleyRequestQueue;

import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener,
        Response.Listener, Response.ErrorListener{

    private EditText usernameInput, firstNameInput, lastNameInput, emailInput,
            passwordInput, passwordConfirmInput;
    private Button registerBtn;

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initialise views
        usernameInput = (EditText) findViewById(R.id.username_edittext);
        firstNameInput = (EditText) findViewById(R.id.first_name_edittext);
        lastNameInput = (EditText) findViewById(R.id.last_name_edittext);
        emailInput = (EditText) findViewById(R.id.email_edittext);
        passwordInput = (EditText) findViewById(R.id.password_edittext);
        passwordConfirmInput = (EditText) findViewById(R.id.password_confirm_edittext);
        registerBtn = (Button) findViewById(R.id.register_button);

        //set listener
        registerBtn.setOnClickListener(this);

        //initialise request queue
        queue = MyVolleyRequestQueue.getInstance(this.getApplicationContext()).getRequestQueue();
    }

    @Override
    public void onClick(View v) {
        //check input fields
        if (!validateInput(new EditText[]{usernameInput, firstNameInput, lastNameInput, emailInput,
                passwordInput, passwordConfirmInput})) {
            Toast.makeText(this, "One or more fields are empty.", Toast.LENGTH_SHORT).show();
        } else if (!comparePasswords()){

            //save newly created account in DB through Volley
            String URL = "";
            final MyJSONObjectRequest req = new MyJSONObjectRequest(
                    Request.Method.POST,
                    URL,
                    null,
                    this,
                    this);
            req.setTag("VolleyTag");
            queue.add(req);
        }
    }

    //checks if EditText fields aren't empty
    public boolean validateInput(EditText[] fields) {
        for (int i = 0; i < fields.length; i++) {
            EditText currentField = fields[i];
            if (currentField.getText().toString().trim().equals("")) {
                return false;
            }
        }
        return true;
    }

    public boolean comparePasswords() {
        if (!passwordInput.getText().toString().equals(passwordConfirmInput.getText().toString())) {
            Toast.makeText(this, "Passwords are not the same.", Toast.LENGTH_SHORT).show();
            passwordInput.setText("");
            passwordConfirmInput.setText("");
            passwordInput.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onResponse(Object response) {

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}
