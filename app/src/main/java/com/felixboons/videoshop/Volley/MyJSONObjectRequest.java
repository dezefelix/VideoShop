package com.felixboons.videoshop.Volley;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Felix on 5-6-2017.
 */

public class MyJSONObjectRequest extends JsonObjectRequest {

    public static final String TOKENPREFERENCE = "TOKEN";
    private Context context;

    public MyJSONObjectRequest(int method,
                               String url,
                               JSONObject jsonRequest,
                               Response.Listener<JSONObject> listener,
                               Response.ErrorListener errorListener,
                               Context context) {
        super(method, url, jsonRequest, listener, errorListener);
        this.context = context;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {

        //get token from SharedPreference
        SharedPreferences tokenPref = context.getSharedPreferences(TOKENPREFERENCE, Context.MODE_PRIVATE);
        String token = tokenPref.getString("token", "");

        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-type", "application/json; charset=utf-8");
        headers.put("Auth", token);
        return headers;
    }

    @Override
    public RetryPolicy getRetryPolicy() {
        return super.getRetryPolicy();
    }
}
