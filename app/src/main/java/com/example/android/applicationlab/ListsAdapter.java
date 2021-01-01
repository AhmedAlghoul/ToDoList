package com.example.android.applicationlab;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListsAdapter extends RecyclerView.Adapter<ListsAdapter.ItemViewHolder> {
    List<ListItem> mItems;
    Context context;
    public ListsAdapter(Context context,List<ListItem> items) {
        this.context=context;
        mItems = items;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_item, parent, false);
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
        private TextView list_title;
        private TextView number;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            list_title = itemView.findViewById(R.id.list_title);
            number = itemView.findViewById(R.id.number_of_lists);
        }

        @SuppressLint("SetTextI18n")
        public void bind(final ListItem item) {
            list_title.setText(item.getTitle());
                number.setText((item.getNumber())+" tasks");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ListsActivity.currentListId=item.getId();
                    ListsActivity.currentListTitle=item.getTitle();
                    Intent intent=new Intent(context,DailyListActivity.class);
                    intent.putExtra("list",item);
                    ListsActivity.initTaskData();
                    intent.putExtra("listId",item.getId());
                    context.startActivity(intent);


                }
            });

        }
    }


}
