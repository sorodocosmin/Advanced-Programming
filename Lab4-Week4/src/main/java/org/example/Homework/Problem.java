package org.example.Homework;

import org.example.Compulsory.Student;
import org.example.Compulsory.Project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Problem {
    private List<Student> listStudents;
    private List<Project> listProjects;

    public  Problem(){
        this.listStudents = new ArrayList<>();
        this.listProjects = new ArrayList<>();
    }
    public Problem (List<Student> listStudents, List<Project> listProjects){
        this.listStudents = listStudents;
        this.listProjects = listProjects;
    }

    public void addStudent( Student student){
        this.listStudents.add(student);
        for( Project project : student.getProjectsList()){
            if(this.listProjects.indexOf(project)==-1){//there is a new project
                this.listProjects.add(project);
            }
        }
    }

    public List<Student> getListStudents(){
        return this.listStudents;
    }
    public List<Project> getListProjects(){
        return this.listProjects;
    }

    public void printStudentsWithLowerPreferences(){
        double averageNrProjects = this.listStudents.stream()
                .mapToInt(Student::getNumberProjects)
                .average().getAsDouble();

        System.out.println("Students which have a number of preferences lower than the average nr of preferences : ");
        this.listStudents.stream()
                .filter( p -> p.getNumberProjects() < averageNrProjects )
                .forEach(System.out::println);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for( Student student : this.listStudents){
            result.append(student);
            result.append("has the following preferred projects  : \n");
            for( Project project : student.getProjectsList()){
                result.append("\t");
                result.append(project);
                result.append("\n");
            }
        }
        return result.toString();
    }
}
