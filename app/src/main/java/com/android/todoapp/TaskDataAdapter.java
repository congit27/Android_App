package com.android.todoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.todoapp.model.Task;
import com.android.todoapp.my_interface.IClickItemTaskListener;

import java.util.List;

public class TaskDataAdapter extends RecyclerView.Adapter<TaskDataAdapter.DataViewHolder> {

    private List<Task> tasks;
    private IClickItemTaskListener iClickItemTaskListener;

    public TaskDataAdapter(List<Task> tasks, IClickItemTaskListener listener) {
        this.tasks = tasks;
        this.iClickItemTaskListener = listener;
    }

    @Override
    public int getItemCount() {
        return tasks == null? 0 : tasks.size();
    }

    @Override
    public TaskDataAdapter.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        switch (viewType) {
            case 1:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_completed_item, parent, false);
                break;
            case 2:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, parent, false);
                break;
            default:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_completed_item, parent, false);
                break;
        }

        return new DataViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position) {
        if(tasks.get(position).isCompleted()) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public void onBindViewHolder(TaskDataAdapter.DataViewHolder holder, int position) {
        Task task = tasks.get(position);
        String startTime = task.getStartTime();
        String deadTime = task.getEndTime();
        String titleTask = task.getTitle();
        boolean state = task.isCompleted();

        holder.startTimeTv.setText(startTime);
        holder.deadTimeTv.setText("To " + deadTime);
        holder.taskTitleTv.setText(titleTask);
        holder.stateCb.setChecked(state);

        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemTaskListener.onClickItemTask(task);
            }
        });
    }

    public static class DataViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layoutItem;
        private TextView startTimeTv, deadTimeTv, taskTitleTv;
        private CheckBox stateCb;

        public DataViewHolder(View itemView) {
            super(itemView);

            layoutItem = (LinearLayout) itemView.findViewById(R.id.layoutItem);
            startTimeTv = (TextView) itemView.findViewById(R.id.startTime);
            deadTimeTv = (TextView) itemView.findViewById(R.id.deadTime);
            taskTitleTv = (TextView) itemView.findViewById(R.id.taskTitle);
            stateCb = (CheckBox) itemView.findViewById(R.id.stateCb);
        }
    }
}
