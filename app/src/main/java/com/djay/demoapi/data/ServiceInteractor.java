package com.djay.demoapi.data;

import android.util.LruCache;

import com.djay.demoapi.data.model.Recipes;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Service interactor class provides methods to access different web-apis
 *
 * @author Dhananjay Kumar
 */

public class ServiceInteractor {
    private LruCache<String, Recipes> cache;
    private Service service;

    public ServiceInteractor(Retrofit retrofit, LruCache<String, Recipes> cache) {
        this.cache = cache;
        this.service = retrofit.create(Service.class);
    }

    /**
     * Returns different recipe topics
     *
     * @return Recipes containing list of RecipeResults
     */
    public Observable<Recipes> getTopics() {
        return Observable.concat(cachedResults(), networkResults()).first();
    }

    private Observable<Recipes> cachedResults() {
        return Observable.just(cache.get("query"))
                .filter((Recipes result) ->
                        result != null
                );
    }

    private Observable<Recipes> networkResults() {
        return service.getRecipesRx()
                .doOnNext((Recipes result) ->
                        cache.put("query", result));
    }
}
