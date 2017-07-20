package com.djay.demoapi.data;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Repository class extends {@link DataSource} and provides method to access data
 *
 * @author Dhananjay Kumar
 */
public class Repository implements DataSource {

    private static final int TIMER_HTTP_OK = 5;

    @Override
    public OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(TIMER_HTTP_OK, TimeUnit.SECONDS)
                .readTimeout(TIMER_HTTP_OK, TimeUnit.SECONDS)
                .writeTimeout(TIMER_HTTP_OK, TimeUnit.SECONDS);
        // Interceptor for faking responses - remove comment for faking
//        httpClient.addInterceptor(new FakeInterceptor());

        return httpClient.build();
    }

    @Override
    public Retrofit getService() {

        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Service.URL_BASE)
                .client(getOkHttpClient())
                .build();
    }


}