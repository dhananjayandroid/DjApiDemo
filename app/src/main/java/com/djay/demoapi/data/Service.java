package com.djay.demoapi.data;

import com.djay.demoapi.data.model.Recipes;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Interface for web-api calls with @{@link retrofit2.Retrofit}
 *
 * @author Dhananjay Kumar
 */
interface Service {
    String URL_BASE = "http://www.recipepuppy.com/";

    @GET("api/")
    Observable<Recipes> getRecipesRx();
}

