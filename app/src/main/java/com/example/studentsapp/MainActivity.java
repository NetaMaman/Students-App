package com.example.studentsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Student> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data= Model.instance().getAllStudents();
        RecyclerView list = findViewById(R.id.mainActivity_studentsrecycler_list);
        list.setHasFixedSize(true);

        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(new StudentRecyclerAdapter());


    }

    class StudentViewHolder extends RecyclerView.ViewHolder{
        TextView nameTv;
        TextView idTv;
        CheckBox cb;
        public StudentViewHolder(@NonNull View itemView) { //save the row view and the references to all the elements inside of them
            super(itemView);

            nameTv = itemView.findViewById(R.id.mainActivity_name_tv);
            idTv = itemView.findViewById(R.id.mainActivity_id_tv);
            cb= itemView.findViewById(R.id.mainActivity_cb);
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = (int) cb.getTag();
                    Student st = data.get(pos);
                    st.cb= cb.isChecked();
                }
            });

        }

        public void bind(Student st, int pos) {
            nameTv.setText(st.name);
            idTv.setText(st.id);
            cb.setChecked(st.cb);
            cb.setTag(pos);
        }
    }

    class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentViewHolder>{


        @NonNull
        @Override
        public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //create a row view, for the first rows
           View view = getLayoutInflater().inflate(R.layout.student_list_row, null); //if we want to put it somewhere

            return new StudentViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) { //connect between the data and the row, every time when i put data on a row
            Student st = data.get(position);
            holder.bind(st, position);
        }

        @Override
        public int getItemCount() {
//            return data.size();
            return data.size();
        }
    }
}