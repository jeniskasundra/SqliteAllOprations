package com.jeniskasundra.sqlitealloprations.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jeniskasundra.sqlitealloprations.R;
import com.jeniskasundra.sqlitealloprations.database.DBManager;

/**
 * Created by Qtonz on 2/10/2018.
 */

public class MainActivity extends AppCompatActivity {

    Button btnAddStudent,btnViewStudent,btnClearData;
    private DBManager dbManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize database
        DBManager.initializeDB(MainActivity.this);
        dbManager = DBManager.getInstance();

        btnAddStudent=(Button) findViewById(R.id.btnAddStudent);
        btnViewStudent=(Button) findViewById(R.id.btnViewStudent);
        btnClearData=(Button) findViewById(R.id.btnClearData);


        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AddStudentActivity.class));
            }
        });


        btnViewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,StudentListActivity.class));
            }
        });

        btnClearData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbManager.clearData();
                Toast.makeText(MainActivity.this,"All Student Data Clear Successfully..",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
