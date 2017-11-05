package com.example.oyeleke.alc.network;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;
import com.example.oyeleke.alc.application.App;

/**
 * Created by oyeleke on 10/29/17.
 */

public class VolleySingleton {

    private static VolleySingleton mInstance = null;
    private RequestQueue requestQueue;


    private VolleySingleton(){
        requestQueue = Volley.newRequestQueue(App.getAppContext());
        VolleyLog.DEBUG = false;
    }

    public static VolleySingleton getmInstance(){
        if(mInstance == null){
            mInstance = new VolleySingleton();
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        return  this.requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req){
        getRequestQueue().add(req);
    }
}
