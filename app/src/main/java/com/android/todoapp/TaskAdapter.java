package com.android.todoapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.todoapp.models.Task;
import com.android.todoapp.my_interface.IClickItemTaskListener;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.DataViewHolder>{
    private List<Task> tasks;
    private Context context;
    private IClickItemTaskListener iClickItemTaskListener;

    public TaskAdapter(Context context, List<Task> tasks, IClickItemTaskListener iClickItemTaskListener) {
        this.context = context;
        this.tasks = tasks;
        this.iClickItemTaskListener = iClickItemTaskListener;
    }

    @Override
    public int getItemCount() {
        return tasks == null? 0 : tasks.size();
    }

    @Override
    public TaskAdapter.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        switch (viewType) {
            case 1:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_completed_item, parent, false);
                break;
            case 2:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, parent, false);
                break;
            default:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, parent, false);
                break;
        }

        return new DataViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position) {
        if(tasks.get(position).getState()) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public void onBindViewHolder(TaskAdapter.DataViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Task task = tasks.get(position);
        String startTime = task.getStartTime();
        String endTime = task.getEndTime();
        String taskTitle = task.getTitle();
        boolean taskState = task.getState();

        holder.taskTime.setText(startTime + "-" + endTime);
        holder.taskTitle.setText(taskTitle);
        holder.taskState.setOnCheckedChangeListener(null);
        holder.taskState.setChecked(taskState);

        holder.taskState.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               task.setState(!task.getState());
               notifyItemChanged(position);
            }
        });


        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemTaskListener.onClickItemTask(task);
            }
        });
    }

    public static class DataViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layoutItem;
        private TextView taskTime, taskTitle;
        private CheckBox taskState;

        public DataViewHolder(View itemView) {
            super(itemView);

            layoutItem = (LinearLayout) itemView.findViewById(R.id.layoutItem);
            taskTime = (TextView) itemView.findViewById(R.id.timeline);
            taskTitle = (TextView) itemView.findViewById(R.id.taskTitle);
            taskState = (CheckBox) itemView.findViewById(R.id.taskState);
        }
    }
}
