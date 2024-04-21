package org.ap.datastrutures.Graphs.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

/*
1 level of neighbor is getting infected in 1 unit of time
thn find how many will get infected in given time period
 */
public class SpreadInfection_13 {
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
        int timeUnit;

        Pair(int v, int timeUnit){
            this.v = v;
            this.timeUnit = timeUnit;
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
            if(str.length ==2 ){
                int v1= Integer.parseInt(str[0]);
                int v2= Integer.parseInt(str[1]);
                graph[v1].add(new Edge(v1,v2));
                graph[v2].add(new Edge(v2,v1));
            }
        }
        System.out.println("enter source for level order traversal");
        int src = Integer.parseInt(br.readLine());
        System.out.println("enter time unit");
        int t = Integer.parseInt(br.readLine());
        int count = 0;
        ArrayDeque<Pair> deque=new ArrayDeque<>();
        deque.add(new Pair(src,1));
        int []visited=new int[vertex];
        while(!deque.isEmpty()){
            Pair rm=deque.removeFirst();
            if(visited[rm.v] > 0){
                continue;
            }
            visited[rm.v] = rm.timeUnit;
            if(rm.timeUnit > t){
                break;
            }
            count++;

            for(Edge edge1 : graph[rm.v]){
                if(visited[edge1.nbr] == 0){
                    deque.add(new Pair(edge1.nbr, rm.timeUnit+1));
                }
            }
        }
        System.out.println(count);
    }
}
