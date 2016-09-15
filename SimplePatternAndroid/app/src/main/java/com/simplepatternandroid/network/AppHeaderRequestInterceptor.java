package com.simplepatternandroid.network;

import android.content.Context;

import com.simplepatternandroid.util.DeviceUtil;

import java.io.IOException;

import okhttp3.*;
import okhttp3.Response;

public class AppHeaderRequestInterceptor implements Interceptor {
    public static final String X_APP_VERSION = "X-AppVersion";
    public static final String X_AUTHORIZATION = "Authorization";

    private final String appVersionName;

    public AppHeaderRequestInterceptor(Context context) {
        this.appVersionName = DeviceUtil.getAppVersionName(context);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        okhttp3.Request request = chain.request();
        okhttp3.Request.Builder builder = request.newBuilder()
                .addHeader(X_APP_VERSION, appVersionName)
                .addHeader(X_AUTHORIZATION, "Basic ZWtvc2FsZW1iYTpFa28xMjM=")
                .addHeader("accept", "application/json");
        okhttp3.Request newRequest = builder.build();
        return chain.proceed(newRequest);
    }
}
