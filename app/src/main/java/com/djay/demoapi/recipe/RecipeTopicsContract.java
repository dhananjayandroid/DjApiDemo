package com.djay.demoapi.recipe;


import com.djay.demoapi.BasePresenter;
import com.djay.demoapi.BaseView;
import com.djay.demoapi.data.model.RecipeResults;

import java.util.List;

/**
 * Interface acting as a contract between recipe-topic's view and presenter
 *
 * @author Dhananjay Kumar
 */
public interface RecipeTopicsContract {

    interface Presenter extends BasePresenter {
        void fetch();

        void sortList(boolean isAsc);
    }

    interface View extends BaseView<Presenter> {
        void showTopics(List<RecipeResults> list);

        void showError();

        void setLoadingIndicator(boolean active);

        void updateTopics(List<RecipeResults> list);
    }
}
