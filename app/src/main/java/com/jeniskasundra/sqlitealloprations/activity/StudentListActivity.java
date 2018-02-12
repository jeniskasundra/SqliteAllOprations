package com.jeniskasundra.sqlitealloprations.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jeniskasundra.sqlitealloprations.R;
import com.jeniskasundra.sqlitealloprations.adapter.StudentListAdapter;
import com.jeniskasundra.sqlitealloprations.database.DBManager;
import com.jeniskasundra.sqlitealloprations.model.Student;

import java.util.ArrayList;

/**
 * Created by Jenis Kasundra on 2/2/2018.
 */
public class StudentListActivity extends AppCompatActivity {

    private RecyclerView rvListEmploy;
    private String TAG = StudentListActivity.class.getSimpleName();
    private ArrayList<Student> studentList;
    private StudentListAdapter studentListAdapter;
    private DBManager dbManager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rvListEmploy = (RecyclerView) findViewById(R.id.rvEmployListList);
        studentList = new ArrayList<Student>();

        //initialize database
        DBManager.initializeDB(StudentListActivity.this);
        dbManager = DBManager.getInstance();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        new GetDataTask().execute();
    }

    private class GetDataTask extends AsyncTask<Void, Void, Void> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(StudentListActivity.this);
            dialog.setTitle("Please Wait...");
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            studentList=dbManager.getAllStudent();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (dialog.isShowing())
                dialog.dismiss();
            studentListAdapter = new StudentListAdapter(StudentListActivity.this,studentList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            rvListEmploy.setLayoutManager(mLayoutManager);
            rvListEmploy.setItemAnimator(new DefaultItemAnimator());
            rvListEmploy.setAdapter(studentListAdapter);
        }
    }

}
