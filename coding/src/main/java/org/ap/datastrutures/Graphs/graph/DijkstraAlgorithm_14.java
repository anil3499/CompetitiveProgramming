package org.ap.datastrutures.Graphs.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;

/*
Only change as compare to BSF is using priority queue instead of normal
so vertex with less weight will process first

 */
public class DijkstraAlgorithm_14 {
    static class Edge {
        int src;
        int nbr;
        int weight;

        Edge(int src, int nbr,int weight) {
            this.src = src;
            this.nbr = nbr;
            this.weight=weight;
        }
    }

    static class Pair implements Comparable<Pair>{
        int v;
        String psf;
        int wsf;

        Pair(int v, String psf,int wsf){
            this.v = v;
            this.psf = psf; //path so far
            this.wsf=wsf; //weight so far
        }


        @Override
        public int compareTo(Pair o) {
            return this.wsf - o.wsf;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int vertex=Integer.parseInt(br.readLine());
        ArrayList<Edge>[]graph=new ArrayList[vertex];
        for(int i=0;i< vertex ; i++){
            graph[i]=new ArrayList<>();
        }
        int edge =Integer.parseInt(br.readLine());
        for(int i=0;i< edge ; i++){
            String []str= br.readLine().split(" ");
            if(str.length ==3 ){
                int v1= Integer.parseInt(str[0]);
                int v2= Integer.parseInt(str[1]);
                int w= Integer.parseInt(str[2]);
                graph[v1].add(new Edge(v1,v2,w));
                graph[v2].add(new Edge(v2,v1,w));
            }
        }

        System.out.println("enter source for level order traversal");
        int src=Integer.parseInt(br.readLine());
        PriorityQueue<Pair> deque=new PriorityQueue<>();
        deque.add(new Pair(src,src+"",0));
        boolean []visited=new boolean[vertex];
        while(!deque.isEmpty()){
            Pair rm=deque.remove();
            if(visited[rm.v] == true){
                continue;
            }
            visited[rm.v] = true;
            System.out.println(rm.v + "via" + rm.psf +" @ " +rm.wsf);
            for(Edge edge1 : graph[rm.v]){
                if(visited[edge1.nbr] == false){
                    deque.add(new Pair(edge1.nbr, rm.psf+edge1.nbr ,rm.wsf+edge1.weight));
                }
            }
        }
    }
}
