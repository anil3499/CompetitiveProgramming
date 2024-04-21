package org.ap.datastrutures.Graphs.graph;

import java.util.ArrayList;
import java.util.Scanner;
/*
7
8
0 1 10
1 2 10
2 3 10
0 3 10
3 4 10
 */
public class PrintAllPath_DFS_2 {

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
        System.out.println("Please enter destination node (to search)");
        int dest=sc.nextInt();
        boolean []visited=new boolean[vertex];
        printAllPath(graph,src,dest,visited, src+"");
    }

    public static void printAllPath(ArrayList<Edge> []graph, int src , int dst, boolean []visited, String pathSoFar){

        if(src==dst){
            System.out.println(pathSoFar);
            return;
        }
        visited[src]=true;
        for(Edge eg  : graph[src]){
            if(!visited[eg.nbr]) {
                printAllPath(graph, eg.nbr, dst, visited , pathSoFar+ " " + eg.nbr);
            }
        }
        visited[src]=false;
    }

}
