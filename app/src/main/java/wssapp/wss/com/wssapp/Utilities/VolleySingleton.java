package wssapp.wss.com.wssapp.Utilities;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {


    private static VolleySingleton instanciaVolley;
    private RequestQueue mRequestQueue;
    private static Context mCtx;
    private static String ip;

    private VolleySingleton(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();

    }

    public static synchronized VolleySingleton getInstanciaVolley(Context context) {
        if (instanciaVolley == null) {
            instanciaVolley = new VolleySingleton(context);
        }
        return instanciaVolley;
    }

    public static String getIp() {
        return ip;
    }

    public static void setIp(String ip) {
        VolleySingleton.ip = ip;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }
    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
