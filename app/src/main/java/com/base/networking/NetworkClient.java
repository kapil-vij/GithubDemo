/*
 * Copyright © 2017 Nedbank. All rights reserved.
 */

package com.base.networking;

import android.content.Context;

import com.demo.search.BuildConfig;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;


public class NetworkClient {
    private static final String[] ACCEPTED_PROTOCOLS = new String[]{"TLSv1.2", "TLSv1.1"};

    private static final String API_BASE_URL = "https://api.github.com/";
    private static NetworkClient instance;
    private LiveNetworkMonitor mLiveNetworkMonitor;
    private Retrofit retrofit;
    private Context context;

    public static NetworkClient getInstance(Context context, LiveNetworkMonitor liveNetworkMonitor) {
        if (instance == null) {
            instance = new NetworkClient();
            instance.context = context;
            instance.mLiveNetworkMonitor = liveNetworkMonitor;
        }

        return instance;
    }

    public static NetworkClient getExistingInstance() {
        return instance;
    }

    public  <T> T create(final Class<T> service) {
        if (retrofit == null) {
            retrofit = createRetrofit();
        }

        return retrofit.create(service);
    }

    public OkHttpClient createOkHttpClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.retryOnConnectionFailure(true);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }

        builder.addInterceptor(new NetworkMonitorInterceptor(mLiveNetworkMonitor));
        builder.addInterceptor(new ResponseCodeInterceptor(context));

        builder.readTimeout(1, TimeUnit.MINUTES);
        builder.writeTimeout(1, TimeUnit.MINUTES);
        builder.connectTimeout(1, TimeUnit.MINUTES);

        return builder.build();
    }

    private Retrofit createRetrofit() {
        final OkHttpClient client = createOkHttpClient();

        final String baseUrl = API_BASE_URL;

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CacheAdapterFactory.create())
                .client(client);

        return builder.build();
    }

    public void reload() {
        retrofit = null;
    }
}
