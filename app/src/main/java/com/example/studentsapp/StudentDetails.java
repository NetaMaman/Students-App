package com.example.studentsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.studentsapp.model.Student;

public class StudentDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        if(getIntent().getExtras() != null) {
            Student student = (Student) getIntent().getSerializableExtra("student");
            Log.d( "TAG" ,student.getName());
            TextView tv= findViewById(R.id.studentDetails_name_tv);
            tv.setText(student.getName());
        }
    }
}