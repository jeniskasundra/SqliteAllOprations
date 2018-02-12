package com.jeniskasundra.sqlitealloprations.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.jeniskasundra.sqlitealloprations.R;
import com.jeniskasundra.sqlitealloprations.database.DBManager;
import com.jeniskasundra.sqlitealloprations.model.Student;

public class UpdateStudentActivity extends AppCompatActivity {

    EditText edtName,edtAddress,edtMobile,edtEmail;
    Spinner spnGender;
    Button btnClear,btnUpdate;
    private DBManager dbManager;
    int id;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        id=getIntent().getIntExtra("id",0);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edtName=(EditText) findViewById(R.id.edtName);
        edtAddress=(EditText) findViewById(R.id.edtAddress);
        edtMobile=(EditText) findViewById(R.id.edtMobile);
        edtEmail=(EditText) findViewById(R.id.edtEmail);
        spnGender=(Spinner) findViewById(R.id.spGender);
        btnClear=(Button) findViewById(R.id.btnClear);
        btnUpdate=(Button)findViewById(R.id.btnUpdate);

        //initialize database
        DBManager.initializeDB(UpdateStudentActivity.this);
        dbManager = DBManager.getInstance();

        //get student detail from database
        Student student=dbManager.getStudent(id);

        //set data into fild
        edtName.setText(student.getName());
        edtAddress.setText(student.getAddress());
        edtMobile.setText(student.getMobile());
        edtEmail.setText(student.getEmail());
        String compareValue = student.getGender();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.array_gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnGender.setAdapter(adapter);
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            spnGender.setSelection(spinnerPosition);
        }


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=edtName.getText().toString();
                String gender=spnGender.getSelectedItem().toString();
                String address=edtAddress.getText().toString();
                String mobile=edtMobile.getText().toString();
                String email=edtEmail.getText().toString();


                if(name !=null && gender!=null && address!=null && mobile!=null && email!=null)
                {
                    Student student=new Student(id,name,gender,address,mobile,email);
                    dbManager.updateStudent(student);
                    Toast.makeText(UpdateStudentActivity.this,"Student Detail Update Successfully...",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                {
                    Toast.makeText(UpdateStudentActivity.this,"Please fill all the field..",Toast.LENGTH_SHORT).show();
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
