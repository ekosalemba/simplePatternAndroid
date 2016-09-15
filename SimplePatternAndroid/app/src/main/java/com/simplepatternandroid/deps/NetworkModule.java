package com.simplepatternandroid.deps;

import android.content.Context;

import com.simplepatternandroid.R;
import com.simplepatternandroid.network.AppHeaderRequestInterceptor;
import com.simplepatternandroid.util.SSLUtils;

import java.io.File;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

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

    public static final int CACHE_DIR_SIZE_30MB = 30 * 1024 * 1024;

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public Cache providesCache(Context context) {
        return new Cache(context.getExternalCacheDir(), CACHE_DIR_SIZE_30MB);
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
    public OkHttpClient providesOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor, AppHeaderRequestInterceptor appHeaderRequestInterceptor, Cache cache) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(appHeaderRequestInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .cache(cache)
                .build();
        return okHttpClient;

    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    @Named("unsecureOkhttp")
    public OkHttpClient providesUnsecureOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor, AppHeaderRequestInterceptor appHeaderRequestInterceptor, Cache cache) {
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            try {
                sslContext.init(null, new TrustManager[]{SSLUtils.trustManager}, null);
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .sslSocketFactory(sslSocketFactory)
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String s, SSLSession sslSession) {
                            return true;
                        }
                    })
                    .addInterceptor(appHeaderRequestInterceptor)
                    .addInterceptor(httpLoggingInterceptor)
                    .cache(cache)
                    .build();
            return okHttpClient;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(appHeaderRequestInterceptor)
                .addInterceptor(httpLoggingInterceptor)
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
    Retrofit provideRetrofit(@Named("unsecureOkhttp") OkHttpClient okHttpClient, Context context) {
        okHttpClient.interceptors();
        return new Retrofit.Builder()
                .baseUrl(context.getString(R.string.server_url))
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}
