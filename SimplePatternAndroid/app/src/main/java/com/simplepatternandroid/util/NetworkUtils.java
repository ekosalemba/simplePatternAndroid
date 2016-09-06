package com.simplepatternandroid.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkUtils {
    String TAG = NetworkUtils.class.getSimpleName();
    public static final int TYPE_WIFI = 1;
    public static final int TYPE_MOBILE_DATA = 2;
    public static final int TYPE_NOT_CONNECTED = 0;
    private Context context;

    public NetworkUtils(Context context) {
        this.context = context;
    }

    private int getConnectivityStatus() {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;
            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE_DATA;
        }
        return TYPE_NOT_CONNECTED;
    }

    public boolean isConnected() {
        boolean isConnected = false;
        try {
            isConnected = getConnectivityStatus() != TYPE_NOT_CONNECTED;
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return isConnected;
    }

}