package org.example.Compulsory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student implements Comparable<Student> {
    private String name;
    private List<Project> projectsList = new ArrayList<>();

    public Student (String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addProject(Project project){
        if(this.projectsList.indexOf(project)==-1){
            this.projectsList.add(project);
        }
    }

    public List<Project> getProjectsList (){
        return this.projectsList;
    }
    public int getNumberProjects(){
        return this.projectsList.size();
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Student o) {
        return this.name.compareTo(o.getName());
    }

    @Override
    public String toString() {
        return "Students{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
