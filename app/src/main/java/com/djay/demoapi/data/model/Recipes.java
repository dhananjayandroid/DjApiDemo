package com.djay.demoapi.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Model class for parsing web-api response
 *
 * @author Dhananjay Kumar
 */
public class Recipes {

    @SerializedName("results")
    @Expose
    private ArrayList<RecipeResults> recipeResults;

    public Recipes(ArrayList<RecipeResults> recipeResults) {
        this.recipeResults = recipeResults;
    }

    public ArrayList<RecipeResults> getRecipeResults() {
        return recipeResults;
    }
}
