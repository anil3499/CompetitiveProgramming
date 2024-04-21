package org.ap.datastrutures.Graphs.graph;

import java.util.ArrayList;
import java.util.Scanner;

public class GetAllConnectedComponentsOfGraph_4 {

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
        ArrayList<PrintAllPath_DFS_2.Edge>[]graph=new ArrayList[vertex];
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
                graph[v1].add(new PrintAllPath_DFS_2.Edge(v1, v2, wt));
                graph[v2].add(new PrintAllPath_DFS_2.Edge(v2, v1, wt));
            }
        }
        boolean []visited=new boolean[vertex];
        ArrayList<ArrayList<Integer>> list=new ArrayList<>();
        for(int i=0;i<vertex;i++){
            if(!visited[i]) {
                ArrayList<Integer> ls = new ArrayList<>();
                getComponents(graph, i, ls, visited);
                list.add(ls);
            }
        }

    }

    public static void getComponents(ArrayList<PrintAllPath_DFS_2.Edge> []graph, int src, ArrayList<Integer> ls, boolean []visited){
        ls.add(src);
        visited[src]=true;
        for(PrintAllPath_DFS_2.Edge eg  : graph[src]){
            if(!visited[eg.nbr]) {
                getComponents(graph, eg.nbr, ls, visited);
            }
        }
    }
}
