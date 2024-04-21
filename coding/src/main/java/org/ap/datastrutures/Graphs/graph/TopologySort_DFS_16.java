package org.ap.datastrutures.Graphs.graph;

import java.io.*;
import java.util.*;
/*
Topological Sort algorithm is a permutation of vertices for a directed acyclic graph
if for all directed edges uv, u appears before v in the graph
use when task are dependent to each other

inout :
7
7
0 1
1 2
2 3
0 3
4 5
5 6
4 6

output:
4
5
6
0
1
2
3
 */
public class TopologySort_DFS_16 {
    static class Edge {
        int src;
        int nbr;

        Edge(int src, int nbr) {
            this.src = src;
            this.nbr = nbr;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int vtces = Integer.parseInt(br.readLine());
        ArrayList<Edge>[] graph = new ArrayList[vtces];
        for (int i = 0; i < vtces; i++) {
            graph[i] = new ArrayList<>();
        }

        int edges = Integer.parseInt(br.readLine());
        for (int i = 0; i < edges; i++) {
            String[] parts = br.readLine().split(" ");
            int v1 = Integer.parseInt(parts[0]);
            int v2 = Integer.parseInt(parts[1]);
            graph[v1].add(new Edge(v1, v2));
        }

        boolean[] visited = new boolean[vtces];
        Stack<Integer> st = new Stack<>();
        for(int v = 0; v < vtces; v++){
            if(visited[v] == false){
                topological(graph, v, visited, st);
            }
        }
        while(st.size() > 0){
            System.out.println(st.pop());
        }
    }

    public static void topological(ArrayList<Edge>[] graph, int src, boolean[] visited, Stack<Integer> st) {
        visited[src] = true;
        for (Edge e : graph[src]) {
            if (!visited[e.nbr]) {
                topological(graph, e.nbr, visited, st);
            }
        }
        st.push(src);
    }
}









