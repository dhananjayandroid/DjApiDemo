package com.djay.demoapi.data;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Interface providing DataSource to application
 *
 * @author Dhananjay Kumar
 */

interface DataSource {

    Retrofit getService();

    OkHttpClient getOkHttpClient();
}
