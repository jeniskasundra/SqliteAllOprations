package com.jeniskasundra.sqlitealloprations.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jeniskasundra.sqlitealloprations.R;
import com.jeniskasundra.sqlitealloprations.database.DBManager;
import com.jeniskasundra.sqlitealloprations.model.Student;

public class AddStudentActivity extends AppCompatActivity {

    EditText edtName,edtAddress,edtMobile,edtEmail;
    Spinner spnGender;
    Button btnClear,btnAdd;
    private DBManager dbManager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edtName=(EditText) findViewById(R.id.edtName);
        edtAddress=(EditText) findViewById(R.id.edtAddress);
        edtMobile=(EditText) findViewById(R.id.edtMobile);
        edtEmail=(EditText) findViewById(R.id.edtEmail);
        spnGender=(Spinner) findViewById(R.id.spGender);
        btnClear=(Button) findViewById(R.id.btnClear);
        btnAdd=(Button)findViewById(R.id.btnAdd);

        //initialize database
        DBManager.initializeDB(AddStudentActivity.this);
        dbManager = DBManager.getInstance();


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=edtName.getText().toString();
                String gender=spnGender.getSelectedItem().toString();
                String address=edtAddress.getText().toString();
                String mobile=edtMobile.getText().toString();
                String email=edtEmail.getText().toString();


                if(name !=null && gender!=null && address!=null && mobile!=null && email!=null)
                {
                    Student student=new Student(0,name,gender,address,mobile,email);
                    dbManager.addStudentDetail(student);
                    Toast.makeText(AddStudentActivity.this,"Student Detail Add Successfully...",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddStudentActivity.this,StudentListActivity.class));
                    finish();
                }
                else
                {
                    Toast.makeText(AddStudentActivity.this,"Please fill all the field..",Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearData();
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }


    private void clearData()
    {
        edtName.setText("");
        edtAddress.setText("");
        edtMobile.setText("");
        edtEmail.setText("");

    }
}
