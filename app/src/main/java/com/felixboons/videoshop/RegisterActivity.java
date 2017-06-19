package com.felixboons.videoshop;

import android.app.ProgressDialog;
import android.content.Intent;
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

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, Response.Listener<JSONObject>, Response.ErrorListener {

    private EditText firstnameInput, lastnameInput, emailInput,
            passwordInput, passwordConfirmInput;

    private String email, password;

    private RequestQueue queue;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initialise views
        firstnameInput = (EditText) findViewById(R.id.first_name_edittext);
        lastnameInput = (EditText) findViewById(R.id.last_name_edittext);
        emailInput = (EditText) findViewById(R.id.email_edittext);
        passwordInput = (EditText) findViewById(R.id.password_edittext);
        passwordConfirmInput = (EditText) findViewById(R.id.password_confirm_edittext);
        Button registerBtn = (Button) findViewById(R.id.register_button);

        //set listener
        registerBtn.setOnClickListener(this);

        //initialise queue
        queue = MyVolleyRequestQueue.getInstance(this.getApplicationContext()).getRequestQueue();
    }

    public void sendPostRequest() throws JSONException {
        String getFilmsURL = "https://video-shop-server.herokuapp.com/api/v1/register";
        final MyJSONObjectRequest req = new MyJSONObjectRequest(
                Request.Method.POST,
                getFilmsURL,
                createBody(),
                this,
                this
        );
        queue.add(req);
    }

    public JSONObject createBody() throws JSONException {

        //get input values
        String firstname = firstnameInput.getText().toString().trim();
        String lastname = lastnameInput.getText().toString().trim();
        email = emailInput.getText().toString().trim();
        password = passwordInput.getText().toString();

        //create payload
        JSONObject payload = new JSONObject();
        payload.put("email", email);
        payload.put("password", password);
        payload.put("firstname", firstname);
        payload.put("lastname", lastname);
        return payload;
    }

    @Override
    public void onClick(View v) {
        if (validateInputs(new EditText[]{firstnameInput, lastnameInput, emailInput,
                passwordInput, passwordConfirmInput}) && validatePasswords()) {

            //post data to DB using Volley
            try {
                showProgressDialog();
                sendPostRequest();
            } catch (JSONException e) {
                e.printStackTrace();
            }
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

    @Override
    public void onResponse(JSONObject response) {
        pd.cancel();
        Toast.makeText(this, "Account created.", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this,LoginActivity.class);
        i.putExtra("email", email);
        i.putExtra("password", password);
        startActivity(i);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Could not create account.", Toast.LENGTH_SHORT).show();
    }

    //show ProgressDialog while registering
    public void showProgressDialog() {
        pd = new ProgressDialog(this);
        pd.setMessage("Logging in...");
        pd.show();
    }
}
