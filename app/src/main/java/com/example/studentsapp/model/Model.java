package com.example.studentsapp.model;

import java.util.LinkedList;
import java.util.List;

public class Model {

    private static final Model _instance=new Model();

    static public Model instance(){return _instance;}

    private Model(){

    }

    List<Student> data= new LinkedList<>();

    public List<Student> getAllStudents(){
        return data;
    }

    public void addStudent(Student st){
        data.add(st);
    }

    public void deleteStudent(int pos){ data.remove(pos);}
}
