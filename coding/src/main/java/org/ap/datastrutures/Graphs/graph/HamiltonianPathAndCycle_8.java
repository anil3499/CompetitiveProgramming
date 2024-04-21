package org.ap.datastrutures.Graphs.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/*
Hamiltonian Path in an undirected graph is a path that visits each vertex exactly once.
A Hamiltonian cycle (or Hamiltonian circuit) is a Hamiltonian Path such that
there is an edge (in the graph) from the last vertex to the first vertex of the Hamiltonian Path.
 */
public class HamiltonianPathAndCycle_8 {

    static class Edge {
        int src;
        int nbr;
        int wt;

        Edge(int src, int nbr, int wt) {
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Please enter number of vertex");
        int vertex = sc.nextInt();
        ArrayList<Edge>[]graph=new ArrayList[vertex];
        for(int i=0 ; i<vertex ;i++){
            graph[i]=new ArrayList<>();
        }
        System.out.println("Please enter number of edges");
        int edges = sc.nextInt();
        for(int i=0 ; i<edges ;i++){
            System.out.println("Please enter the vertex relationship with weight");
            sc.nextLine();
            String sdw=sc.nextLine();
            String []str=sdw.split(" ");
            if(str.length==3) {
                int v1 = Integer.parseInt(str[0].trim());
                int v2 = Integer.parseInt(str[1].trim());
                int wt = Integer.parseInt(str[2].trim());
                graph[v1].add(new Edge(v1, v2, wt));
                graph[v2].add(new Edge(v2, v1, wt));
            }
        }
        System.out.println("Please enter source node (to search)");
        int src=sc.nextInt();
        HashSet<Integer> visited=new HashSet<>();
        hamiltonian(graph,src,visited, src+"",src);
    }

    public static void hamiltonian(ArrayList<Edge> []graph, int src , HashSet<Integer> visited,
                                   String pathSoFar ,int originalSrc){

        if(visited.size() == graph.length-1){
            System.out.println(pathSoFar);
            boolean closingEdgeFound=false;
            for(Edge e:graph[src]){
                if(e.nbr == originalSrc){
                    closingEdgeFound=true;
                    break;
                }
            }

            if(closingEdgeFound == true){
                System.out.println("*");
            }else{
                System.out.println(".");
            }
            return;
        }
        visited.add( src);
        for(Edge eg  : graph[src]){
            if(visited.contains(eg.nbr) == false) {
                hamiltonian(graph, eg.nbr, visited , pathSoFar+ " " + eg.nbr,originalSrc);
            }
        }
        visited.add( src);
    }

}
