package com.djay.demoapi.recipe;

import android.support.annotation.NonNull;
import android.util.LruCache;

import com.djay.demoapi.data.Repository;
import com.djay.demoapi.data.ServiceInteractor;
import com.djay.demoapi.data.model.RecipeResults;
import com.djay.demoapi.data.model.Recipes;
import com.djay.demoapi.schedulers.BaseSchedulerProvider;

import java.util.ArrayList;
import java.util.Collections;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Presenter class for showing recipe list extends {@link com.djay.demoapi.recipe.RecipeTopicsContract.Presenter}
 *
 * @author Dhananjay Kumar
 */
class RecipeTopicsPresenter implements RecipeTopicsContract.Presenter {

    private Repository mRepository;

    private RecipeTopicsContract.View mView;
    private ArrayList<RecipeResults> recipeResultsArrayList;
    private BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private CompositeSubscription mSubscriptions;


    /**
     * {@link RecipeTopicsPresenter} constructor with {@link Repository},
     * {@link com.djay.demoapi.recipe.RecipeTopicsContract.View} and {@link BaseSchedulerProvider}
     *
     * @param repository repository to load data
     * @param view       view to present
     * @param provider   scheduler
     */
    RecipeTopicsPresenter(@NonNull Repository repository, @NonNull RecipeTopicsContract.View view, @NonNull
            BaseSchedulerProvider provider) {
        this.mRepository = checkNotNull(repository, "repository cannot be null");
        this.mView = checkNotNull(view, "view cannot be null!");
        this.mSchedulerProvider = checkNotNull(provider, "schedulerProvider cannot be null");

        mSubscriptions = new CompositeSubscription();

        mView.setPresenter(this);
    }

    @Override
    public void fetch() {
        LruCache<String, Recipes> cache = new LruCache<>(5 * 1024 * 1024); // 5MiB
        final ServiceInteractor interactor = new ServiceInteractor(mRepository.getService(), cache);
        Subscription subscription = interactor.getTopics()
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .subscribe((Recipes listTopics) -> {
                            mView.setLoadingIndicator(false);
                            recipeResultsArrayList = listTopics.getRecipeResults();
                            mView.showTopics(listTopics.getRecipeResults());
                        },
                        (Throwable error) -> {
                            try {
                                mView.showError();
                            } catch (Throwable t) {
                                throw new IllegalThreadStateException();
                            }
                        }, () -> {
                        });
        mSubscriptions.add(subscription);
    }

    /**
     * Sorts Recipe list
     *
     * @param isAsc is ascending
     */
    @Override
    public void sortList(boolean isAsc) {
        if (isAsc)
            Collections.sort(recipeResultsArrayList, (one, two) -> one.getTitle().toLowerCase().compareTo(two
                    .getTitle().toLowerCase()));
        else
            Collections.sort(recipeResultsArrayList, (one, two) -> two.getTitle().toLowerCase().compareTo(one
                    .getTitle().toLowerCase()));
        mView.updateTopics(recipeResultsArrayList);
    }

    @Override
    public void subscribe() {
        fetch();
    }

    @Override
    public void unSubscribe() {
        mSubscriptions.clear();
    }
}
