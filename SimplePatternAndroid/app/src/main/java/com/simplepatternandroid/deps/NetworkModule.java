package com.simplepatternandroid.deps;

import android.content.Context;

import com.simplepatternandroid.R;
import com.simplepatternandroid.network.AppHeaderRequestInterceptor;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module
public class NetworkModule {
    File cacheFile;
    String baseUrl;

    public NetworkModule(File cacheFile, String baseUrl) {
        this.cacheFile = cacheFile;
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public AppHeaderRequestInterceptor providesAppHeaderRequestInterceptor(Context context) {
        AppHeaderRequestInterceptor interceptor = new AppHeaderRequestInterceptor(context);
        return interceptor;
    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public HttpLoggingInterceptor providesOkHttp3LoggingInterceptor(Context context) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(logLevel(context.getString(R.string.log_level)));
        return interceptor;
    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public OkHttpClient providesOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor, AppHeaderRequestInterceptor appHeaderRequestInterceptor) {
        Cache cache = null;
        try {
            cache = new Cache(cacheFile, 10 * 1024 * 1024);
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(appHeaderRequestInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .cache(cache)
                .build();
        return okHttpClient;
    }

    private okhttp3.logging.HttpLoggingInterceptor.Level logLevel(String level) {
        if (level == null) return HttpLoggingInterceptor.Level.NONE;
        switch (level) {
            case "NONE":
                return HttpLoggingInterceptor.Level.NONE;
            case "BASIC":
                return HttpLoggingInterceptor.Level.BASIC;
            case "HEADERS":
                return HttpLoggingInterceptor.Level.HEADERS;
            case "BODY":
                return HttpLoggingInterceptor.Level.BODY;
            default:
                return HttpLoggingInterceptor.Level.NONE;
        }
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        okHttpClient.interceptors();
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}
