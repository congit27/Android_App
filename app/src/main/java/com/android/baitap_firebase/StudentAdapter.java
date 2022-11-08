package com.android.baitap_firebase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder>{
    private List<Student> students;

    public StudentAdapter(List<Student> students) {
        this.students = students;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);

        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = students.get(position);
        if(student == null) {
            return;
        }
        holder.studentCode.setText("Mã sinh viên: " + student.getCode());
        holder.studentName.setText(student.getName());
    }

    @Override
    public int getItemCount() {
        if(students != null) {
            return students.size();
        }
        return 0;
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        private TextView studentCode, studentName;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            studentCode = itemView.findViewById(R.id.studentCode);
            studentName = itemView.findViewById(R.id.studentName);
        }
    }
}
