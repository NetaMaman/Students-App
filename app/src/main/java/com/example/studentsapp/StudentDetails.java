package com.example.studentsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
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
            TextView name= findViewById(R.id.studentDetails_name_tv);
            TextView id= findViewById(R.id.studentDetails_id_tv);
            TextView phone= findViewById(R.id.studentDetails_phone_tv);
            TextView address= findViewById(R.id.studentDetails_address_tv);
            CheckBox cb= findViewById(R.id.studentDetails_cb);

            name.setText(student.getName());
            id.setText(student.getId());
            phone.setText(student.getPhone());
            address.setText(student.getAddress());
            cb.setChecked(student.getCb());
        }
    }
}