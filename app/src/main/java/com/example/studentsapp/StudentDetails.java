package com.example.studentsapp;

import androidx.appcompat.app.AppCompatActivity;

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
    List<Student> data;
    Button btn;
    TextView name,id, phone, address;
    CheckBox cb;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        data= Model.instance().getAllStudents();
        position=(int)getIntent().getSerializableExtra("position");
        Student student = data.get(position);
        Log.d( "TAG" ,student.getName());

        name= findViewById(R.id.studentDetails_name_tv);
        name.setText(student.getName());

        id= findViewById(R.id.studentDetails_id_tv);
        id.setText(student.getId());

        phone= findViewById(R.id.studentDetails_phone_tv);
        phone.setText(student.getPhone());

        address= findViewById(R.id.studentDetails_address_tv);
        address.setText(student.getAddress());

        cb= findViewById(R.id.studentDetails_cb);
        cb.setChecked(student.getCb());

        btn = findViewById(R.id.studentDetails_edit_btn);
        btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(StudentDetails.this, EditStudent.class);
                    intent.putExtra("position", position);
                    startActivity(intent);
                }
            });

    }


    @Override
    protected void onStart() {
        super.onStart();
        Student s= data.get(position);
        name.setText(s.getName());
        id.setText(s.getId());
        phone.setText(s.getPhone());
        address.setText(s.getAddress());
        cb.setChecked(s.getCb());
    }
}