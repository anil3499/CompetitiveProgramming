package org.ap.datastrutures.Graphs.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
/*

logic : Remove from queue -> Mark as visited -> work -> add in queue

 */
public class BreadthFirstTraversal_10 {
    static class Edge {
        int src;
        int nbr;

        Edge(int src, int nbr) {
            this.src = src;
            this.nbr = nbr;
        }
    }

    static class Pair {
        int v;
        String psf;

        Pair(int v, String psf){
            this.v = v;
            this.psf = psf;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new  InputStreamReader(System.in));
        int vertex=Integer.parseInt(br.readLine());
        ArrayList<Edge> []graph=new ArrayList[vertex];
        for(int i=0;i< vertex ; i++){
            graph[i]=new ArrayList<>();
        }
        int edge =Integer.parseInt(br.readLine());
        for(int i=0;i< edge ; i++){
            String []str= br.readLine().split(" ");
            if(str.length ==2 ){
                int v1= Integer.parseInt(str[0]);
                int v2= Integer.parseInt(str[1]);
                graph[v1].add(new Edge(v1,v2));
                graph[v2].add(new Edge(v2,v1));
            }
        }

        System.out.println("enter source for level order traversal");
        int src=Integer.parseInt(br.readLine());
        ArrayDeque<Pair> deque=new ArrayDeque<>();
        deque.add(new Pair(src,src+""));
        boolean []visited=new boolean[vertex];
        while(!deque.isEmpty()){
            Pair rm=deque.removeFirst();
            if(visited[rm.v] == true){
                continue;
            }
            visited[rm.v] = true;
            System.out.println(rm.v + " @ " +rm.psf);
            for(Edge edge1 : graph[rm.v]){
                if(visited[edge1.nbr] == false){
                    deque.add(new Pair(edge1.nbr, rm.psf+edge1.nbr));
                }
            }
        }
    }
}
