package com.djay.demoapi;

import com.djay.demoapi.data.Repository;
import com.djay.demoapi.schedulers.BaseSchedulerProvider;
import com.djay.demoapi.schedulers.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Application Module class for dependency injection
 *
 * @author Dhananjay Kumar
 */
@Module
public class AppModule {

    RecipeApplication mRecipeApplication;

    public AppModule(RecipeApplication recipeApplication) {
        mRecipeApplication = recipeApplication;
    }

    @Singleton
    @Provides
    Repository provideRepository() {
        return new Repository();
    }

    @Singleton
    @Provides
    BaseSchedulerProvider provideSchedulerProvider() {
        return new SchedulerProvider();
    }

}
