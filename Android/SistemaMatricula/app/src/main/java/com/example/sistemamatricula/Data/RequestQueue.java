package com.example.sistemamatricula.Data;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.toolbox.Volley;

public class RequestQueue {

    private static RequestQueue instance;
    private static Context ctx;
    private com.android.volley.RequestQueue requestQueue;

    private RequestQueue(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized RequestQueue getInstance(Context context) {
        if (instance == null) {
            instance = new RequestQueue(context);
        }

        return instance;
    }

    public com.android.volley.RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }

        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setRetryPolicy(new DefaultRetryPolicy(3000, 2, 1));
        getRequestQueue().add(req);
    }
}
