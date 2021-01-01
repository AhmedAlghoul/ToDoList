package com.example.android.applicationlab;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static com.example.android.applicationlab.DailyListActivity.tasksToDelete;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ItemViewHolder> {
    List<Task> mItems;
    Context context;
    private OnClickTask onClickTask;

    public TasksAdapter(Context context, List<Task> items) {
        this.context = context;
        mItems = items;
    }

    public interface OnClickTask {
        void onClickTask(Task task, int position);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_task_item, parent, false);
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

    public void setOnClickTask(TasksAdapter.OnClickTask onClickTask) {
        this.onClickTask = onClickTask;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
        }

        public void bind(final Task item) {
            checkBox.setText(item.getTitle());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickTask.onClickTask(item, getAdapterPosition());
                }
            });
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        tasksToDelete.add(item.getId());
                    } else {
                        tasksToDelete.remove(item.getId());
                    }
                    Toast.makeText(context, "" + tasksToDelete.size(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

}
