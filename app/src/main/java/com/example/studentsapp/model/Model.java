package com.example.studentsapp.model;

import java.util.LinkedList;
import java.util.List;

public class Model { //singleton pattern

    private static final Model _instance=new Model();

    static public Model instance(){return _instance;}

    private Model(){
        for (int i = 0; i < 1; i++) {
//
            Student s= new Student();
            s.setAddress("address");
            s.setCb(false);
            s.setId("id:"+i);
            s.setPhone("050");
            s.setAvatarUrl("");
            s.setName("name " + i);
            addStudent(s);

        }

    }

    List<Student> data= new LinkedList<>();

    //have to be public because its inside package and we want to use it outside
    public List<Student> getAllStudents(){
        return data;
    }

    public void addStudent(Student st){
        data.add(st);
    }

    public void deleteStudent(int pos){ data.remove(pos);}
}
