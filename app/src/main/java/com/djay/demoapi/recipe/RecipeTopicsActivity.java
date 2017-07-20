package com.djay.demoapi.recipe;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.djay.demoapi.RecipeApplication;
import com.djay.demoapi.R;
import com.djay.demoapi.data.Repository;
import com.djay.demoapi.schedulers.BaseSchedulerProvider;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Activity class for showing result of web-api call extends {@link AppCompatActivity}
 *
 * @author Dhananjay Kumar
 */
public class RecipeTopicsActivity extends AppCompatActivity {

    @Inject
    Repository mRepository;

    @Inject
    BaseSchedulerProvider mSchedulerProvider;

    /**
     * Adds fragment to this activity
     *
     * @param fragmentManager FragmentManager class instance
     * @param fragment        fragment to add
     * @param frameId         container-id
     */
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theme_activity);
        initializeDagger();
        initFragment();
    }

    /**
     * Injects Dagger dependencies
     */
    private void initializeDagger() {
        RecipeApplication app = (RecipeApplication) getApplication();
        app.getAppComponent().inject(this);
    }

    /**
     * Initialize and adds fragment to this activity
     */
    private void initFragment() {
        RecipeTopicsFragment recipeTopicsFragment = (RecipeTopicsFragment) getSupportFragmentManager().
                findFragmentById(R.id.contentFrame);
        if (recipeTopicsFragment == null) {
            recipeTopicsFragment = RecipeTopicsFragment.newInstance();
            addFragmentToActivity(getSupportFragmentManager(),
                    recipeTopicsFragment, R.id.contentFrame);
        }
        new RecipeTopicsPresenter(mRepository, recipeTopicsFragment, mSchedulerProvider);
    }
}
