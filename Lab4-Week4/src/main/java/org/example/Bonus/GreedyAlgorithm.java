package org.example.Bonus;

import org.example.Compulsory.Project;
import org.example.Compulsory.Student;
import org.example.Homework.Problem;

import java.util.HashMap;
import java.util.Map;

public class GreedyAlgorithm extends Algorithm{

    public GreedyAlgorithm(Problem problem){
        this.problem = problem;
    }
     Map<Student, Project> findMaximumMatchingSet(){

        Map<Student,Project> matchingSet = new HashMap<>();

        for( Student student : this.problem.getListStudents()){
            for( Project project : student.getProjectsList()){
                if(!matchingSet.containsValue(project)){//the first project which is not already assigned to a student
                    matchingSet.put(student,project);
                    break;
                }
            }
        }
        return matchingSet;
    }
    public void printMaximMatching(){
        Map<Student,Project> matchingSet = this.findMaximumMatchingSet();
        System.out.println("The matching Set obtained used using Greedy : (size = " + matchingSet.size() + ") : ");
        //matchingSet.entrySet().stream().forEach(System.out::println);
    }
}
