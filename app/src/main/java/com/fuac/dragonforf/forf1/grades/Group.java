package com.fuac.dragonforf.forf1.grades;

import java.util.ArrayList;

public class Group {
    private ArrayList<Student> studentsInGroup;
    private String name;

    public Group(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Student> getStudentsInGroup() {
        return studentsInGroup;
    }

    public void setStudentsInGroup(ArrayList<Student> studentsInGroup) {
        this.studentsInGroup = studentsInGroup;
    }
}
