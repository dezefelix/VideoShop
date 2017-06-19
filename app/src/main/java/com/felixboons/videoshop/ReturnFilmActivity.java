package com.felixboons.videoshop;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.felixboons.videoshop.Domain.Customer;
import com.felixboons.videoshop.Domain.Rental;
import com.felixboons.videoshop.Volley.MyJSONObjectRequest;
import com.felixboons.videoshop.Volley.MyVolleyRequestQueue;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class ReturnFilmActivity extends AppCompatActivity implements ListView.OnItemClickListener, Response.Listener<JSONObject>, Response.ErrorListener {

    private RentalAdapter adapter;
    private ArrayList<Rental> rentals = new ArrayList<>();

    private RequestQueue queue;

    private ProgressDialog pd;

    public static final String TOKENPREFERENCE = "TOKEN";
    private Customer c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_film);

        //initialise views
        ListView filmListview = (ListView) findViewById(R.id.listview);

        //initialise ArrayList
        rentals = new ArrayList<>();

        //initialise queue
        queue = MyVolleyRequestQueue.getInstance(this.getApplicationContext()).getRequestQueue();

        //initialise & set adapter
        adapter = new RentalAdapter(this, rentals);
        filmListview.setAdapter(adapter);

        //set listener
        filmListview.setOnItemClickListener(this);

        //show ProgressDialog
        showProgressDialog();
        try {
            //get customer from intent
            c = (Customer) getIntent().getSerializableExtra("customer");

            sendGetFilmRequest();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void sendGetFilmRequest() throws JSONException {
        pd.cancel();
        String getFilmsURL = "https://video-shop-server.herokuapp.com/api/v1/rentals/" + c.getCustomerId();
        final MyJSONObjectRequest req = new MyJSONObjectRequest(
                Request.Method.GET,
                getFilmsURL,
                createBody(),
                this,
                this
        );
        queue.add(req);
    }

    //create JSON body
    public JSONObject createBody() throws JSONException {

        //get token from SharedPreference
        SharedPreferences tokenPref = getSharedPreferences(TOKENPREFERENCE, Context.MODE_PRIVATE);
        String token = tokenPref.getString("token", "");

        //create payload
        JSONObject payload = new JSONObject();
        payload.put("Auth", token);
        return payload;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

        //create alert dialog to return a rental (or not)
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Return '" + rentals.get(position).getTitle() + "'?")
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //does nothing
                    }
                })
                .setPositiveButton("RETURN", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //remove country from DB
                        try {
                            returnFilm(rentals.get(position));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //remove country from listview
                        rentals.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                })
                .show();
    }

    public void returnFilm(final Rental rental) throws JSONException {
        String URL = "https://video-shop-server.herokuapp.com/api/v1/rentals/" + rental.getCustomerId() +
                "/" + rental.getInventoryId();
        Log.i(this.getClass().getSimpleName(), "Deleting: " + URL);
        final MyJSONObjectRequest req = new MyJSONObjectRequest(
                Request.Method.PUT,
                URL,
                createBody(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pd.cancel();
                        //toast feedback to user
                        Toast.makeText(ReturnFilmActivity.this, "Film is returned.", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        error.printStackTrace();
                        Toast.makeText(ReturnFilmActivity.this, "Could not return film.", Toast.LENGTH_SHORT).show();
                    }
                });
        req.setTag("MyVolleyActivityTAG");
        queue.add(req);
    }

    //show ProgressDialog while retrieving film data
    public void showProgressDialog() {
        pd = new ProgressDialog(this);
        pd.setMessage("Retrieving data...");
        pd.show();
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            rentals.clear();
            JSONArray rentalsArray = response.getJSONArray("rentals");

            for (int i = 0; i < rentalsArray.length(); i++) {
                JSONObject rental = rentalsArray.getJSONObject(i);
                int rentalId = rental.optInt("rental_id");
                int customerId = rental.optInt("customer_id");
                int inventoryId = rental.optInt("inventory_id");
                String title = rental.optString("title");
                Date rentalDate = parseDate(rental.optString("rental_date"));
                Date returnDate = parseDate(rental.optString("return_date"));
                int rentalDuration = rental.optInt("rental_duration");

                Rental c = new Rental(rentalId, customerId, inventoryId, rentalDuration,
                        rentalDate, returnDate, title);

                this.rentals.add(c);
                adapter.notifyDataSetChanged();
            }
            pd.cancel();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Could not retrieve my rented films.", Toast.LENGTH_SHORT).show();
    }


    //parse ISO8601 time format to Date object
    public Date parseDate(String unparsedDate) {
        DateTime dt = ISODateTimeFormat.dateTime().parseDateTime(unparsedDate);
        return dt.toDate();
    }
}
