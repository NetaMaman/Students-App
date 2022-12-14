package com.example.studentsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import java.util.List;


public class AddStudentToList extends AppCompatActivity {
    Button cancelBtn;
    Button saveBtn;
    EditText name;
    EditText studentId;
    EditText phone;
    EditText address;
    List<Student> data;
    CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_student);
        data= Model.instance().getAllStudents();

        cancelBtn = findViewById(R.id.addActivity_CancelBtn);
        saveBtn=findViewById(R.id.addActivity_SaveBtn);
        name=findViewById(R.id.EditActivity_name_et);
        studentId=findViewById(R.id.EditActivity_id_et);
        phone=findViewById(R.id.EditActivity_phone_et);
        address=findViewById(R.id.EditActivity_address_et);
        checkBox=findViewById(R.id.EditActivity_CheckBox);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student=new Student();
                student.setName(name.getText().toString());
                student.setPhone(phone.getText().toString());
                student.setId(studentId.getText().toString());
                student.setAddress(address.getText().toString());
                student.setCb(checkBox.isChecked());
                student.setAvatarUrl("");
                data.add(student);
                finish();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
