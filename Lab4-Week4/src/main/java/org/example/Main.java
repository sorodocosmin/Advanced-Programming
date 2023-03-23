package org.example;

import org.example.Bonus.Algorithm;
import org.example.Bonus.Graph4jAlgorithm;
import org.example.Bonus.GreedyAlgorithm;
import org.example.Bonus.JGraphTAlgorithm;
import org.example.Homework.Problem;
import org.example.Compulsory.Project;
import org.example.Compulsory.Student;
import org.example.Homework.ProblemGenerator;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        //exCompulsory();
        exBonusSet();

    }

    public static void exBonusSet(){
        var students = IntStream.rangeClosed(0,3)
                .mapToObj(i -> new Student("S" + i))
                .toArray(Student[]::new);
        var projects = IntStream.rangeClosed(0,3)
                .mapToObj(i -> new Project("P" + i))
                .toArray(Project[]::new);

        students[0].addProject(projects[0]);
        students[0].addProject(projects[1]);

        students[1].addProject(projects[0]);
        students[1].addProject(projects[2]);
        students[1].addProject(projects[3]);

        students[2].addProject(projects[1]);

        Problem p = new Problem();

        IntStream.rangeClosed(0,3).forEach( i-> p.addStudent(students[i]));

        Algorithm alg = new Graph4jAlgorithm(p);

        ((Graph4jAlgorithm) alg).printMaximumIndependentSet();
        ((Graph4jAlgorithm) alg).printMinimumSet();




    }
    public static void exCompulsory(){

        var students = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new Student("S" + i))
                .toArray(Student[]::new);
        var projects = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new Project("P" + i))
                .toArray(Project[]::new);

        IntStream.rangeClosed(0, 2).forEach(i -> students[0].addProject(projects[i]));
        IntStream.rangeClosed(0, 1).forEach(i -> students[1].addProject(projects[i]));
        students[2].addProject(projects[0]);

        List<Student> studentList = new LinkedList<>();

        Problem p1 = new Problem();
        p1.addStudent(students[0]);
        p1.addStudent(students[1]);
        p1.addStudent(students[2]);

        Algorithm algGraph4j = new Graph4jAlgorithm(p1);
        algGraph4j.printMaximMatching();
        ((Graph4jAlgorithm) algGraph4j).printMinimumSet();
        ((Graph4jAlgorithm) algGraph4j).printMaximumIndependentSet();


        //studentList.addAll(Arrays.asList(students));
        studentList.add(students[1]);
        studentList.add(students[0]);
        studentList.add(students[2]);

        List<Student> sortedStudents = studentList.stream().sorted().collect(Collectors.toList());

        //sortedStudents.forEach(System.out::println);

        System.out.println("Students Unsorted");
        for (var student : studentList) {
            System.out.println(student);
        }
        System.out.println("Students Sorted");
        for (var student : sortedStudents) {
            System.out.println(student);
        }


        Set<Project> projectList = new TreeSet<>();

        projectList.add(projects[2]);
        projectList.add(projects[1]);
        projectList.add(projects[0]);

        //Set<Project> sortedProjects = projectList.stream().sorted().collect(Collectors.toSet());

        System.out.println("Projects sorted:");
        for (var project : projectList) {
            System.out.println(project);
        }
    }
}