package com.djay.demoapi;

import android.util.LruCache;

import com.google.gson.Gson;
import com.djay.demoapi.data.model.RecipeResults;
import com.djay.demoapi.data.model.Recipes;
import com.djay.demoapi.data.ServiceInteractor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.observers.TestSubscriber;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by Dhananjay on 20/07/2017.
 */

public class ServiceInteractorTest {

    @Mock
    private LruCache<String, Recipes> mCache;

    @Before
    public void setup(){

        MockitoAnnotations.initMocks(this);
        when(mCache.get(anyString())).thenReturn(null);
    }

    @Test
    public void mockService() {

        RecipeResults recipeResults = new RecipeResults("Title 1", "Href 1");
        ArrayList<RecipeResults> recipeResultsArrayList = new ArrayList<>();
        recipeResultsArrayList.add(recipeResults);
        Recipes result = new Recipes(recipeResultsArrayList);

        MockWebServer mockService = new MockWebServer();
        mockService.enqueue(new MockResponse().setBody(new Gson().toJson(result)));

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(mockService.url("dfdf/"))
                .build();

        TestSubscriber<Recipes> subscriber = new TestSubscriber<>();
        ServiceInteractor serviceInteractor = new ServiceInteractor(retrofit, mCache);
        serviceInteractor.getTopics().subscribe(subscriber);

        subscriber.assertNoErrors();
        subscriber.assertCompleted();
    }

    @Test
    public void callServiceTest() {

        RecipeResults recipeResults1 = new RecipeResults("Title 1", "Href 1");
        RecipeResults recipeResults2 = new RecipeResults("Title 2", "Href 2");
        ArrayList<RecipeResults> recipeResultsArrayList = new ArrayList<>();
        recipeResultsArrayList.add(recipeResults1);
        recipeResultsArrayList.add(recipeResults2);
        Recipes result = new Recipes(recipeResultsArrayList);

        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.enqueue(new MockResponse().setBody(new Gson().toJson(result)));

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(mockWebServer.url("http://www.recipepuppy.com/"))
                .build();

        TestSubscriber<Recipes> subscriber = new TestSubscriber<>();
        ServiceInteractor serviceInteractor = new ServiceInteractor(retrofit, mCache);
        serviceInteractor.getTopics().subscribe(subscriber);

        subscriber.assertNoErrors();
        subscriber.assertCompleted();
    }


}
