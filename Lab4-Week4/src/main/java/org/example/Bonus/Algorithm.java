package org.example.Bonus;

import org.example.Compulsory.Project;
import org.example.Compulsory.Student;
import org.example.Homework.Problem;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Algorithm {
    protected Problem problem ;

    abstract Map<Student, Project> findMaximumMatchingSet ();
    public abstract void printMaximMatching();
}
