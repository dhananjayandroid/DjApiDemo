package com.djay.demoapi;

import com.djay.demoapi.recipe.RecipeTopicsActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Application Component interface for dependency injection with Dagger
 *
 * @author Dhananjay Kumar
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(RecipeTopicsActivity recipeTopicsActivity);
}
