package org.ap.datastrutures.Graphs.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PerfectFriend_7 {
    static class Edge {
        int src;
        int nbr;
        Edge(int src, int nbr) {
            this.src = src;
            this.nbr = nbr;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int vertex = Integer.parseInt(br.readLine());
        int edges = Integer.parseInt(br.readLine());

        ArrayList<Edge> []graph=new ArrayList[vertex];
        for(int i=0 ; i < vertex ; i++){
            graph[i]=new ArrayList<>();
        }
        for(int i=0 ; i < edges ; i++){
            String []parts=br.readLine().split(" ");
            int v1=Integer.parseInt(parts[0]);
            int v2=Integer.parseInt(parts[1]);
            graph[v1].add(new Edge(v1,v2));
            graph[v2].add(new Edge(v2,v1));
        }
        boolean []visited=new boolean[vertex];
        ArrayList<ArrayList<Integer>> comps=new ArrayList<>();
        for(int i=0 ; i<vertex ;i++){
            if(!visited[i]){
                ArrayList<Integer> temp=new ArrayList<>();
                perfectFriend(graph, i, visited,temp);
                comps.add(temp);
            }
        }
        int count = 0;
        for(int i = 0; i < comps.size(); i++){
            for(int j = i + 1; j < comps.size(); j++){
                count += comps.get(i).size() * comps.get(j).size();
            }
        }
        System.out.println(count);

    }

    public static  void perfectFriend(ArrayList<Edge> []graph, int src, boolean []visited, ArrayList<Integer> ls){
        visited[src]=true;
        ls.add(src);
        for(Edge e : graph[src]){
            if(!visited[e.nbr]){
                perfectFriend(graph, e.nbr, visited,ls);
            }
        }
    }
}
