package org.ap.datastrutures.Graphs.graph;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
/*
7
9
0 1 10
1 2 10
2 3 10
0 3 40
3 4 2
4 5 3
5 6 3
4 6 8
2 5 5
0
6
 */
public class LongestSmallestPathKthLargest_DFS_3 {

    static class Pair implements Comparable<Pair>{
        String path;
        Integer weight;
        public Pair(String path, Integer weight) {
            this.path = path;
            this.weight = weight;
        }

        @Override
        public int compareTo(Pair o) {
            return this.weight-o.weight;
        }
    }

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
            int v1=Integer.parseInt(str[0].trim());
            int v2=Integer.parseInt(str[1].trim());
            int wt=Integer.parseInt(str[2].trim());
            graph[v1].add(new Edge(v1,v2,wt));
            graph[v2].add(new Edge(v2,v1,wt));
        }
        System.out.println("Please enter source node (to search)");
        int src=sc.nextInt();
        System.out.println("Please enter destination node (to search)");
        int dest=sc.nextInt();
        boolean []visited=new boolean[vertex];
        Integer weightSoFar=Integer.MIN_VALUE;
        String pathSoFar="";
        int k=3;
        longestPath(graph,src,dest,visited, src+"", weightSoFar,3);
        System.out.println("Smallest Path = " + spath + "@" + spathwt);
        System.out.println("Largest Path = " + lpath + "@" + lpathwt);
        System.out.println(k + "th largest path = " + queue.peek().path + "@" + queue.peek().weight);
    }

    public static String spath;
    public static Integer spathwt=Integer.MAX_VALUE;
    public static String lpath;
    public static Integer lpathwt=Integer.MIN_VALUE;
    public static PriorityQueue<Pair> queue=new PriorityQueue<>();

    public static void longestPath(ArrayList<Edge> []graph, int src , int dst, boolean []visited, String pathSoFar, int weightSoFar,int k){

        if(src==dst){
            if(weightSoFar < spathwt){
                spathwt=weightSoFar;
                spath=pathSoFar;
            }
            if(weightSoFar > lpathwt){
                lpathwt=weightSoFar;
                lpath=pathSoFar;
            }
            if(queue.size() < k){
                queue.add(new Pair(pathSoFar, weightSoFar));
            }else{
                if(weightSoFar > queue.peek().weight){
                    queue.remove();
                    queue.add(new Pair(pathSoFar,weightSoFar));
                }
            }
            return;
        }
        visited[src]=true;
        for(Edge eg  : graph[src]){
            if(!visited[eg.nbr]) {
                longestPath(graph, eg.nbr, dst, visited , pathSoFar+ " " + eg.nbr, weightSoFar+ eg.wt,k);
            }
        }
        visited[src]=false;
    }

}
