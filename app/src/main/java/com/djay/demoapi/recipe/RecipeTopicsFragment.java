package com.djay.demoapi.recipe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.djay.demoapi.R;
import com.djay.demoapi.data.model.RecipeResults;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Fragment class for showing list of recipes extends {@link Fragment}
 *
 * @author Dhananjay Kumar
 */
public class RecipeTopicsFragment extends Fragment implements RecipeTopicsContract.View, RecipeTopicsAdapter
        .MyClickListener {

    @BindView(R.id.pb_recipe_topics)
    ProgressBar mProgressBar;

    @BindView(R.id.btn_retry)
    Button mRetry;

    @BindView(R.id.theme_fragment_relative_layout)
    RelativeLayout mRelativeLayout;

    @BindView(R.id.rv_recipe_topics)
    RecyclerView mRecyclerView;

    List<RecipeResults> mListTopics;

    private RecipeTopicsContract.Presenter mPresenter;
    private RecipeTopicsAdapter adapter;

    public RecipeTopicsFragment() {
        // Required empty public constructor
    }

    public static RecipeTopicsFragment newInstance() {
        return new RecipeTopicsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.theme_fragment, container, false);
        ButterKnife.bind(this, view);
        mPresenter.fetch();
        return view;
    }

    @Override
    public void setPresenter(RecipeTopicsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    /**
     * OnClick of Retry button
     */
    @OnClick(R.id.btn_retry)
    public void retry() {
        mProgressBar.setVisibility(View.VISIBLE);
        mRetry.setVisibility(View.GONE);
        mPresenter.fetch();
    }

    /**
     * On sorting options change
     *
     * @param button  {@link android.widget.RadioButton}
     * @param checked isChecked
     */
    @OnCheckedChanged({R.id.rb_asc, R.id.rb_desc})
    public void onRadioButtonCheckChanged(CompoundButton button, boolean checked) {
        if (checked) {
            switch (button.getId()) {
                case R.id.rb_asc:
                    mPresenter.sortList(true);
                    break;
                case R.id.rb_desc:
                    mPresenter.sortList(false);
                    break;
            }
        }
    }

    /**
     * Show list of recipe topics on response of web-api call
     *
     * @param list list of {@link RecipeResults}
     */
    @Override
    public void showTopics(List<RecipeResults> list) {
        mListTopics = list;
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecipeTopicsAdapter(getActivity(), list);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void showError() {
        mProgressBar.setVisibility(View.GONE);
        mRetry.setVisibility(View.VISIBLE);
        mRetry.setText(getString(R.string.retry));
    }

    /**
     * Shows loader while data loads
     *
     * @param active isActive
     */
    @Override
    public void setLoadingIndicator(boolean active) {
        if (!active) {
            mRetry.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.GONE);
        }
    }

    /**
     * Updates recipe topic list
     *
     * @param list list of {@link RecipeResults}
     */
    @Override
    public void updateTopics(List<RecipeResults> list) {
        mListTopics = list;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unSubscribe();
    }

    @Override
    public void onItemClick(int position, View v) {
        // Perform actions on click of recipe list items
    }

}
