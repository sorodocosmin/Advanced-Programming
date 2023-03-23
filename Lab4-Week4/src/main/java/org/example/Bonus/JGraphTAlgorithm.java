package org.example.Bonus;

import org.example.Compulsory.Project;
import org.example.Compulsory.Student;
import org.example.Homework.Problem;
import org.jgrapht.Graph;
import org.jgrapht.alg.matching.HopcroftKarpMaximumCardinalityBipartiteMatching;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JGraphTAlgorithm extends Algorithm{
    private Graph<Integer, DefaultEdge> graph ;
    public JGraphTAlgorithm( Problem problem){
        this.problem = problem;
        this.createGraph();
    }
    private void createGraph(){

        int nrStudents = this.problem.getListStudents().size();
        int nrProjects = this.problem.getListProjects().size();

        this.graph = new SimpleGraph<>(DefaultEdge.class);

        int nrNodes = 0;
        //add the nodes
        for( Student student : this.problem.getListStudents()){
            this.graph.addVertex(nrNodes);
            ++nrNodes;
        }

        for( Project project : this.problem.getListProjects()){
            this.graph.addVertex(nrNodes);
            ++nrNodes;
        }

        nrNodes = 0;

        int nr = 1;
        //add the edges
        for( Student student : this.problem.getListStudents()){
            for (Project project : student.getProjectsList()){
                this.graph.addEdge(nrNodes,(nrStudents + this.problem.getListProjects().indexOf(project)));
                nr++;
            }
            ++nrNodes;
        }

    }

    @Override
    Map<Student, Project> findMaximumMatchingSet() {

        Set<Integer> partition1 = new HashSet<>();

        for(int i = 0 , n = this.problem.getListStudents().size() ; i < n ; ++i){
            partition1.add(i);
        }


        Set<Integer> partition2 = new HashSet<>();

        for( int i = this.problem.getListStudents().size() , n = this.problem.getListStudents().size() + this.problem.getListProjects().size() ; i<n ; ++i){
            partition2.add(i);
        }

        HopcroftKarpMaximumCardinalityBipartiteMatching<Integer,DefaultEdge> alg = new HopcroftKarpMaximumCardinalityBipartiteMatching<>(this.graph,partition1,partition2);
        Set<DefaultEdge> edges = alg.getMatching().getEdges();

        Map<Student,Project> matchingSet = new HashMap<>();
        int nrStudents = this.problem.getListStudents().size();
        for(DefaultEdge edge : edges){
            int u = this.graph.getEdgeSource(edge);//nrOfStudent
            int v = this.graph.getEdgeTarget(edge);//nrOfProject
            matchingSet.put(this.problem.getListStudents().get(u),this.problem.getListProjects().get(v-nrStudents));
        }
        return matchingSet;
    }

    @Override
    public void printMaximMatching() {
        Map<Student,Project> matchingSet = this.findMaximumMatchingSet();
        System.out.println("The matching Set obtained used using JGraphT : (size = " + matchingSet.size() + ") : ");
        //matchingSet.entrySet().stream().forEach(System.out::println);
    }
}
