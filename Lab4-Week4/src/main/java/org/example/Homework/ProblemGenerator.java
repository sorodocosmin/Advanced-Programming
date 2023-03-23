package org.example.Homework;

import com.github.javafaker.Faker;
import org.example.Compulsory.Project;
import org.example.Compulsory.Student;

import java.util.ArrayList;
import java.util.List;

public class ProblemGenerator {
    private static final int MAX_NR_STUDENTS = 2_000;
    private static final int MAX_NR_PROJECTS = 2_000;
    public static Problem random (){
        int randomNrStudents = 1+(int)(Math.random()*MAX_NR_STUDENTS);
        int randomNrProjects = 1+(int)(Math.random()*MAX_NR_PROJECTS);
        //int randomNrStudents = MAX_NR_STUDENTS;
        //int randomNrProjects = MAX_NR_PROJECTS;
        System.out.println("Nr students : " + randomNrStudents);
        System.out.println("Nr projects : " + randomNrProjects);

        List<Project> listProjects = new ArrayList<>();

        //create a random number of projects
        for(int i=0 ; i < randomNrProjects ; ++i){
            Project project = new Project(new Faker().job().title() + (i) );
            listProjects.add(project);
        }

        List<Student> listStudent = new ArrayList<>();


        for(int i=0 ; i < randomNrStudents ; ++i){
            //create a random number of students
            Student student = new Student(new Faker().name().fullName() + (i));
            int randomNumberProjectPreferred = 1+(int)(Math.random()*(randomNrProjects/2));
            //add a random number of projects for this student
            for(int j=0; j< randomNumberProjectPreferred ; ++j){
                int randomIdProject = (int)(Math.random()*randomNrProjects);
                student.addProject(listProjects.get(randomIdProject));
            }
            listStudent.add(student);
        }

        Problem problem = new Problem(listStudent,listProjects);

        return problem;
    }
}
