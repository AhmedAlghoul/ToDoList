package com.example.android.applicationlab;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ItemViewHolder> {
    List<Task> mItems;
    Context context;
    public SearchAdapter(Context context, List<Task> items) {
        this.context=context;
        mItems = items;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_search_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;
        private TextView list_text;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            list_text=itemView.findViewById(R.id.text_list);
            checkBox = itemView.findViewById(R.id.checkBox2);
        }

        public void bind(final Task item) {
            String listName="";
            if (item!=null) {
                checkBox.setText(item.getTitle());
                for (int i=0;i<ListsActivity.tasksList.size();i++){
                    if (ListsActivity.tasksList.get(i).getId().equals(item.getListId())){
                        listName=ListsActivity.tasksList.get(i).getTitle();
                    }
                }
                list_text.setText(listName);
            }


        }
    }

}
