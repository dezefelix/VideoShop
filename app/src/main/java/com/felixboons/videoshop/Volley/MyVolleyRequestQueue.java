package com.felixboons.videoshop.Volley;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;

/**
 * Created by Felix on 5-6-2017.
 */

public class MyVolleyRequestQueue {

    private static MyVolleyRequestQueue instance;
    private static Context context;
    private RequestQueue requestQueue;

    private MyVolleyRequestQueue(Context context) {
        this.context = context;
        this.requestQueue = getRequestQueue();
    }

    public static synchronized MyVolleyRequestQueue getInstance(Context context) {
        if (MyVolleyRequestQueue.context == null) {
            instance = new MyVolleyRequestQueue(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            Cache cache = new DiskBasedCache(context.getCacheDir(), 10 * 1024 * 1024);
            Network network = new BasicNetwork(new HurlStack());
            requestQueue = new RequestQueue(cache, network);
            requestQueue.start();
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
