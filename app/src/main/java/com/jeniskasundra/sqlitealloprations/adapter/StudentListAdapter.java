package com.jeniskasundra.sqlitealloprations.adapter;


import static com.jeniskasundra.sqlitealloprations.database.DBContext.COLUMN_ID;
import static com.jeniskasundra.sqlitealloprations.database.DBContext.COLUMN_NAME;
import static com.jeniskasundra.sqlitealloprations.database.DBContext.COLUMN_GENDER;
import static com.jeniskasundra.sqlitealloprations.database.DBContext.COLUMN_ADDRESS;
import static com.jeniskasundra.sqlitealloprations.database.DBContext.COLUMN_MOBILE_NO;
import static com.jeniskasundra.sqlitealloprations.database.DBContext.COLUMN_EMAIL;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jeniskasundra.sqlitealloprations.R;
import com.jeniskasundra.sqlitealloprations.activity.StudentDetailActivity;
import com.jeniskasundra.sqlitealloprations.model.Student;

import java.util.ArrayList;

/**
 * Created by Jenis Kasundra on 2/2/2018.
 */

public class StudentListAdapter extends  RecyclerView.Adapter<StudentListAdapter.ViewHolder> {

    private ArrayList<Student> studentList;
    private  Context context;
    public StudentListAdapter(Context context, ArrayList<Student> studentList) {
        this.context=context;
        this.studentList=studentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_employ_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final Student student = studentList.get(position);
        holder.id.setText(String.valueOf(student.getId()));
        holder.name.setText(student.getName());
        holder.email.setText(student.getEmail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, StudentDetailActivity.class);
                Bundle extras = new Bundle();
                extras.putString(COLUMN_ID,String.valueOf(student.getId()));
                extras.putString(COLUMN_NAME,student.getName());
                extras.putString(COLUMN_GENDER,student.getGender());
                extras.putString(COLUMN_ADDRESS,student.getAddress());
                extras.putString(COLUMN_MOBILE_NO,student.getMobile());
                extras.putString(COLUMN_EMAIL,student.getEmail());
                intent.putExtras(extras);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView name, email,id;
        public ViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.tvId);
            name = (TextView) itemView.findViewById(R.id.tvEmployName);
            email = (TextView) itemView.findViewById(R.id.tvEmployEmail);
        }
    }


}
