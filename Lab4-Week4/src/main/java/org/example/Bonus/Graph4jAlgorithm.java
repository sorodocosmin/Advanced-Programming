package org.example.Bonus;

import org.example.Compulsory.Project;
import org.example.Compulsory.Student;
import org.example.Homework.Problem;
import org.graph4j.Edge;
import org.graph4j.Graph;
import org.graph4j.GraphBuilder;
import org.graph4j.alg.matching.HopcroftKarpMaximumMatching;
import org.graph4j.util.Matching;
import org.graph4j.util.VertexSet;

import java.util.HashMap;
import java.util.Map;

public class Graph4jAlgorithm extends Algorithm{

    private Graph graph;

    public Graph4jAlgorithm(Problem problem){
        this.problem = problem;
        this.createGraph();
    }

    private void createGraph(){

        int nrStudents = this.problem.getListStudents().size();
        int nrProjects = this.problem.getListProjects().size();
        this.graph = GraphBuilder.empty().estimatedNumVertices(nrStudents + nrProjects).buildGraph();

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
    Map<Student, Project> findMaximumMatchingSet(){

        HopcroftKarpMaximumMatching alg = new HopcroftKarpMaximumMatching(this.graph);

        Matching maximumSet = alg.getMatching();
        //System.out.println( "maximum set is : \n " + maximumSet);
       // System.out.println("The minimum cover is \n" + alg.getMinimumVertexCover());

        int nrStudents = this.problem.getListStudents().size();
        int nrEdges = maximumSet.numEdges();
        int [][] edges = maximumSet.edges();

        Map<Student,Project> matchingSet = new HashMap<>();

        for(int i=0 ; i < nrEdges ; ++i){
            matchingSet.put(this.problem.getListStudents().get(edges[i][0]),this.problem.getListProjects().get(edges[i][1]-nrStudents));
        }

         return matchingSet;

    }
    public void printMaximMatching(){
        Map<Student,Project> matchingSet = this.findMaximumMatchingSet();
        System.out.println("The matching Set obtained used using Graph4J : (size = " + matchingSet.size() + ") : ");
       // matchingSet.entrySet().stream().forEach(System.out::println);
    }

    public void printMinimumSet(){

        HopcroftKarpMaximumMatching alg = new HopcroftKarpMaximumMatching(this.graph);

        int nrStudents = this.problem.getListStudents().size();

        VertexSet minimumSet = alg.getMinimumVertexCover();
        System.out.println("The minimum cardinality formed is : (size : " + minimumSet.size() + " ) :" );
        for( int i : minimumSet){
            if(i < nrStudents){
                System.out.println(this.problem.getListStudents().get(i));
            }
            else{
                System.out.println(this.problem.getListProjects().get(i-nrStudents));
            }
        }
    }

    public void printMaximumIndependentSet(){

        HopcroftKarpMaximumMatching alg = new HopcroftKarpMaximumMatching(this.graph);

        int nrStudents = this.problem.getListStudents().size();

        VertexSet minimumSet = alg.getMaximumStableSet();
        System.out.println("The maximum independent set is : (size : " + minimumSet.size() + " ) :" );
        for( int i : minimumSet){
            if(i < nrStudents){
                System.out.println(this.problem.getListStudents().get(i));
            }
            else{
                System.out.println(this.problem.getListProjects().get(i-nrStudents));
            }
        }
    }
}
