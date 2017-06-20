package com.felixboons.videoshop;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.felixboons.videoshop.Domain.Customer;
import com.felixboons.videoshop.Volley.MyJSONObjectRequest;
import com.felixboons.videoshop.Volley.MyVolleyRequestQueue;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, Response.ErrorListener, Response.Listener<JSONObject> {

    private EditText emailInput, passwordInput;
    private RequestQueue queue;
    private ProgressDialog pd;

    private SharedPreferences tokenPref;
    private SharedPreferences.Editor tokenPrefEditor;
    public static final String TOKENPREFERENCE = "TOKEN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //initialise views
        emailInput = (EditText) findViewById(R.id.email_edittext);
        passwordInput = (EditText) findViewById(R.id.password_edittext);
        Button loginBtn = (Button) findViewById(R.id.login_button);
        Button registerBtn = (Button) findViewById(R.id.register_button);
        TextView forgotPassBtn = (TextView) findViewById(R.id.forgot_pass_textview);

        //set listeners
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
        forgotPassBtn.setOnClickListener(this);

        //initialise queue
        queue = MyVolleyRequestQueue.getInstance(this.getApplicationContext()).getRequestQueue();

        //create SharedPreference
        tokenPref = getSharedPreferences(TOKENPREFERENCE, Context.MODE_PRIVATE);
        tokenPrefEditor = tokenPref.edit();

        //load credentials if available
        if (getIntent() != null) {
            emailInput.setText(getIntent().getStringExtra("email"));
            passwordInput.setText(getIntent().getStringExtra("password"));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                //check input fields & credentials
                if (validateInput()) {
                    try {
                        login();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                break;

            case R.id.register_button:
                //continue to register screen
                Intent iRegister = new Intent(this, RegisterActivity.class);
                startActivity(iRegister);
                break;

            case R.id.forgot_pass_textview:
                //continue to register screen
                Toast.makeText(this, "Should have remembered your password, dummy.", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public boolean validateInput() {
        if (emailInput.getText().toString().trim().equals("") ||
                passwordInput.getText().toString().trim().equals("")) {
            Toast.makeText(this, "One or more fields are empty.", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    //create JSON body
    public JSONObject createBody() throws JSONException {

        //get input values
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString();

        //create payload
        JSONObject payload = new JSONObject();
        payload.put("email", email);
        payload.put("password", password);
        return payload;
    }

    //login procedure
    public void login() throws JSONException {
        showProgressDialog();
        sendPostRequest();
    }

    //send post request to DB using Volley
    public void sendPostRequest() throws JSONException {
        String loginURL = "https://video-shop-server.herokuapp.com/api/v1/login";
        final MyJSONObjectRequest req = new MyJSONObjectRequest(
                Request.Method.POST,
                loginURL,
                createBody(),
                this,
                this,
                this);
        req.setTag("VolleyTAG");
        queue.add(req);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Could not log in.", Toast.LENGTH_SHORT).show();
        //cancel ProgressDialog
        pd.cancel();
        //clear current password
        passwordInput.setText("");
    }

    @Override
    public void onResponse(JSONObject response) {

        //save JWT in application
        try {
            String token = response.getString("token");
            tokenPrefEditor.putString("token", token);
            tokenPrefEditor.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //create customer
        int id = response.optInt("customerID");
        Customer c = new Customer(id);

        //continue to home screen
        Intent iHome = new Intent(this, HomescreenActivity.class);
        iHome.putExtra("customer", c);
        startActivity(iHome);

        //cancel ProgressDialog
        pd.cancel();
    }

    //show ProgressDialog while logging in
    public void showProgressDialog() {
        pd = new ProgressDialog(this);
        pd.setMessage("Logging in...");
        pd.show();
    }
}
