package com.djay.demoapi.recipe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.djay.demoapi.R;
import com.djay.demoapi.data.model.RecipeResults;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Adapter class for recipe list extending @{@link android.support.v7.widget.RecyclerView.Adapter}
 *
 * @author Dhananjay Kumar
 */
class RecipeTopicsAdapter extends RecyclerView.Adapter<RecipeTopicsAdapter.DataObjectHolder> {

    private static RecipeTopicsAdapter.MyClickListener sClickListener;
    private Context mContext;
    private List<RecipeResults> mList;
    // Allows to remember the last item shown on screen
    private int lastPosition = -1;

    /**
     * {@link RecipeTopicsAdapter} constructor with context and list of recipes
     *
     * @param context Context
     * @param list    {@link List} of recipes
     */
    RecipeTopicsAdapter(Context context, List<RecipeResults> list) {
        mContext = context;
        mList = list;
    }

    /**
     * sets item click listener on the RecylerView
     *
     * @param myClickListener {@link MyClickListener} instance
     */
    void setOnItemClickListener(RecipeTopicsAdapter.MyClickListener myClickListener) {
        sClickListener = myClickListener;
    }

    @Override
    public RecipeTopicsAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);

        return new DataObjectHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeTopicsAdapter.DataObjectHolder holder, int position) {

        holder.tvName.setText(mList.get(position).getTitle());
        holder.tvHref.setText(mList.get(position).getHref());
        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * To apply the animation to items
     *
     * @param viewToAnimate view to animate
     * @param position      the last position
     */
    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    /**
     * On item click listener interface
     */
    interface MyClickListener {
        void onItemClick(int position, View v);
    }

    /**
     * {@link android.support.v7.widget.RecyclerView.ViewHolder} class for Recipes list
     */
    static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_href)
        TextView tvHref;

        DataObjectHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            sClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

}
