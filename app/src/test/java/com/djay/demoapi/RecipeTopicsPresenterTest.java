package com.djay.demoapi;

import com.djay.demoapi.data.model.RecipeResults;
import com.djay.demoapi.data.model.Recipes;
import com.djay.demoapi.data.Repository;
import com.djay.demoapi.schedulers.BaseSchedulerProvider;
import com.djay.demoapi.schedulers.ImmediateSchedulerProvider;
import com.djay.demoapi.recipe.RecipeTopicsContract;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import io.reactivex.Observable;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Created by Dhananjay on 20/07/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class RecipeTopicsPresenterTest {
    @Mock
    private Repository mRepository;

    @Mock
    private RecipeTopicsContract.View mView;

    private BaseSchedulerProvider mSchedulerProvider;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mSchedulerProvider = new ImmediateSchedulerProvider();
    }

    @Test
    public void fetchAllTopics() {
        RecipeResults recipeResults1 = new RecipeResults("Title 1", "Href 1");
        RecipeResults recipeResults2 = new RecipeResults("Title 2", "Href 2");
        ArrayList<RecipeResults> recipeResultsArrayList = new ArrayList<>();
        recipeResultsArrayList.add(recipeResults1);
        recipeResultsArrayList.add(recipeResults2);
        Recipes result = new Recipes(recipeResultsArrayList);

        doReturn(Observable.just(result))
        .when(mRepository.getService());

        verify(mView).showTopics(result.getRecipeResults());

    }
}
