package com.example.oyeleke.alc.network;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by oyeleke on 10/29/17.
 */

public class NetworkRequest {
    private RequestQueue queue;
    private RequestCallback callback;
    private String TAG ;
    private StringRequest req;

    public NetworkRequest(String TAG){
        queue = VolleySingleton.getmInstance().getRequestQueue();
        this.TAG = TAG;
    }

    public NetworkRequest setCallback(RequestCallback callback){
        this.callback = callback;
        return this;
    }

    public String getTAG() {
        return TAG;
    }

    public void setTAG(String TAG) {
        this.TAG = TAG;
    }

    public  void execute(String url, int requestMethod){
        execute(url,requestMethod,null);
    }

    public void execute(String url, int requestMethod, final Map<String, String>params){
        callback.onRequestStart(TAG);
        req = new StringRequest(requestMethod, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            callback.onRequestCompleted(TAG, response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onRequestError(TAG,error);
            }
        }
        ){
            @Override
            protected  Response<String>parseNetworkResponse(
                    NetworkResponse response
            ){
                try {
                    String res = new String(response.data,"UTF-8");
                    return Response.success(res, HttpHeaderParser.parseCacheHeaders(response));
                }catch (UnsupportedEncodingException e){
                    e.printStackTrace();
                    return Response.error(new ParseError(response));
                }
            }
            @Override
            protected Map<String, String>getParams() throws AuthFailureError{
                if(params != null)
                    return params;
                else
                    return super.getParams();
            }
        };
        int socketTimeout = 70000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        req.setRetryPolicy(policy);
        req.setTag(TAG);
        queue.add(req);
    }
    public void cancelRequest(){
        if(req != null){
            req.cancel();
            queue.cancelAll(TAG);
            callback.onRequestCancelled();
        }
    }



    public interface RequestCallback {
        void onRequestStart(String tag);
        void onRequestCompleted(String tag, String response)throws JSONException;
        void onRequestError(String tag, VolleyError volleyError);
        void onRequestCancelled();
    }
}
