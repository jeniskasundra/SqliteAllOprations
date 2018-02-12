package com.jeniskasundra.sqlitealloprations.activity;

import static com.jeniskasundra.sqlitealloprations.database.DBContext.COLUMN_ID;
import static com.jeniskasundra.sqlitealloprations.database.DBContext.COLUMN_NAME;
import static com.jeniskasundra.sqlitealloprations.database.DBContext.COLUMN_GENDER;
import static com.jeniskasundra.sqlitealloprations.database.DBContext.COLUMN_ADDRESS;
import static com.jeniskasundra.sqlitealloprations.database.DBContext.COLUMN_MOBILE_NO;
import static com.jeniskasundra.sqlitealloprations.database.DBContext.COLUMN_EMAIL;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jeniskasundra.sqlitealloprations.R;
import com.jeniskasundra.sqlitealloprations.database.DBManager;

/**
 * Created by Jenis Kasundra on 2/3/2018.
 */

public class StudentDetailActivity extends AppCompatActivity {

    private String id, name, gender, address, mobile, email;
    private TextView tvId, tvName, tvGender, tvAddress, tvMobile, tvEmail;
    private Button btnUpdate, btnDelete;
    private DBManager dbManager;
    private Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_detail_layout);

        //initialize database
        DBManager.initializeDB(StudentDetailActivity.this);
        dbManager = DBManager.getInstance();




        Bundle bundle = getIntent().getExtras();
        id = bundle.getString(COLUMN_ID);
        name = bundle.getString(COLUMN_NAME);
        gender = bundle.getString(COLUMN_GENDER);
        address = bundle.getString(COLUMN_ADDRESS);
        mobile = bundle.getString(COLUMN_MOBILE_NO);
        email = bundle.getString(COLUMN_EMAIL);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tvId = (TextView) findViewById(R.id.tvId);
        tvName = (TextView) findViewById(R.id.tvName);
        tvGender = (TextView) findViewById(R.id.tvGender);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        tvMobile = (TextView) findViewById(R.id.tvMobile);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        tvId.setText(id);
        tvName.setText(name);
        tvGender.setText(gender);
        tvAddress.setText(address);
        tvMobile.setText(mobile);
        tvEmail.setText(email);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(StudentDetailActivity.this,UpdateStudentActivity.class);
                i.putExtra("id",Integer.parseInt(id));
                startActivity(i);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteStudentDetail();
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void deleteStudentDetail() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(
                StudentDetailActivity.this, R.style.AppAlertDialog);
        dialog.setTitle("Delete");
        dialog.setMessage("Are you sure want to delete ?");
        dialog.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbManager.deleteImageItem(Integer.parseInt(id));
                        finish();
                    }
                });

        dialog.setNegativeButton("no",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        dialog.show();

    }

}
