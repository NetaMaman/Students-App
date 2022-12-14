package com.example.studentsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Student> data;
    Button add;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView list = findViewById(R.id.mainActivity_studentsrecycler_list);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(this));

        data= Model.instance().getAllStudents();

        add= findViewById(R.id.mainActivity_add_student_btn);

        StudentRecyclerAdapter adapter= new StudentRecyclerAdapter();
        list.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                Log.d("TAG", "Row was clicked " +pos);
            }
        }); //our func

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, AddStudentToList.class);
                startActivity(intent);
            }
        });
    }

    class StudentViewHolder extends RecyclerView.ViewHolder{
        public OnItemClickListener listener;
        TextView nameTv;
        TextView idTv;
        CheckBox cb;
        int position;
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
                    st.setCb(cb.isChecked());
                }
            });


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    position= getAdapterPosition();
                    listener.onItemClick(position);
                    Intent intent= new Intent(MainActivity.this, StudentDetails.class);
                    intent.putExtra("position", position); //where user is an instance of User object
                    startActivity(intent);
                }
            });

        }

        public void bind(Student st, int pos) {
            this.position=pos;
            nameTv.setText(st.getName());
            idTv.setText(st.getId());
            cb.setChecked(st.getCb());
            cb.setTag(pos);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int pos);
    }

    class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentViewHolder>{
        OnItemClickListener listener;
        void setOnItemClickListener(OnItemClickListener listener){
            this.listener=listener;
        }

        @NonNull
        @Override
        public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //create a row view, for the first rows
           View view = getLayoutInflater().inflate(R.layout.student_list_row, null); //if we want to put it somewhere, (parent,false)
            StudentViewHolder holder= new StudentViewHolder(view);
            holder.listener=listener;
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) { //connect between the data and the row, every time when i put data on a row
            Student st = data.get(position);
            holder.bind(st, position);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
}