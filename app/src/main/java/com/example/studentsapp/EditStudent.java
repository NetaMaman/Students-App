package com.example.studentsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import java.util.List;

public class EditStudent extends AppCompatActivity {
    Button cancelBtn, deleteBtn, saveBtn;
    int position;
    TextView name, studentId, phone, address;
    CheckBox cb;
    List<Student> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_or_add_student);
        data = Model.instance().getAllStudents();

        cancelBtn = findViewById(R.id.activityEditStudent_cancelBtn);
        deleteBtn = findViewById(R.id.activityEditStudent_deleteBtn);
        saveBtn = findViewById(R.id.activityEditStudent_saveBtn);


        position = (int) getIntent().getSerializableExtra("position");

        Student student = data.get(position);
        name = findViewById(R.id.EditActivity_name_et);
        name.setText(student.getName());

        studentId = findViewById(R.id.EditActivity_id_et);
        studentId.setText(student.getId());

        phone = findViewById(R.id.EditActivity_phone_et);
        phone.setText(student.getPhone());

        address = findViewById(R.id.EditActivity_address_et);
        address.setText(student.getAddress());

        cb = findViewById(R.id.EditActivity_CheckBox);
        cb.setChecked(student.getCb());


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              finish();
            }
        });

//        deleteBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.get(position).setName(name.getText().toString());
                data.get(position).setId(studentId.getText().toString());
                data.get(position).setPhone(phone.getText().toString());
                data.get(position).setAddress(address.getText().toString());
                data.get(position).setCb(cb.isChecked());
                Log.d("TAG", "student after edit" + data.get(position));
                finish();
            }
        });

    }
}