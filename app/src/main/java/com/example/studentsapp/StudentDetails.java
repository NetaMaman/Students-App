package com.example.studentsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import java.io.Serializable;
import java.util.List;

public class StudentDetails extends AppCompatActivity {
    List<Student> data=Model.instance().getAllStudents();
    Student student;
    Button btn,back;
    TextView name,id, phone, address;
    CheckBox cb;
    int position;
    int REQUEST_CODE=1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        position=(int)getIntent().getSerializableExtra("position");
        student = data.get(position);
        Log.d( "TAG" ,student.getName());

        name= findViewById(R.id.studentDetails_name_tv);
        id= findViewById(R.id.studentDetails_id_tv);
        phone= findViewById(R.id.studentDetails_phone_tv);
        address= findViewById(R.id.studentDetails_address_tv);
        cb= findViewById(R.id.studentDetails_cb);
        back=findViewById(R.id.studentDetails_back_Btn);
        btn = findViewById(R.id.studentDetails_edit_btn);

        bind(student);
        btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(StudentDetails.this, EditStudent.class);
                    intent.putExtra("position", position);
                    startActivityForResult(intent, REQUEST_CODE);
                }
            });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String result = data.getStringExtra("result");
        if(result.equals("delete")) {
            finish();
        }
    }


    public void bind(Student s){
        name.setText(s.getName());
        id.setText(s.getId());
        phone.setText(s.getPhone());
        address.setText(s.getAddress());
        cb.setChecked(s.getCb());
    }

    @Override
    protected void onStart() {
        super.onStart();
        bind(student);

    }
}