package com.jorgereina.shopify.tags;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.TagsViewHolder> {

    @NonNull
    @Override
    public TagsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TagsViewHolder tagsViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class TagsViewHolder extends RecyclerView.ViewHolder {

        public TagsViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
