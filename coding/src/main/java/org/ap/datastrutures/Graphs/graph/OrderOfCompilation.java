package org.ap.datastrutures.Graphs.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

/*
https://www.geeksforgeeks.org/find-whether-it-is-possible-to-finish-all-tasks-or-not-from-given-dependencies/
https://www.pepcoding.com/resources/online-java-foundation/graphs/compilation-order-official/ojquestion
Input: 2, [[1, 0]]
Output: true
Explanation: There are a total of 2 tasks to pick. To pick task 1 you should have finished task 0. So it is possible.
Input: 2, [[1, 0], [0, 1]]
Output: false
Explanation: There are a total of 2 tasks to pick. To pick task 1 you should have finished task 0, and to pick task 0 you should also have finished task 1. So it is impossible.
Input: 3, [[1, 0], [2, 1], [3, 2]]
Output: true
Explanation: There are a total of 3 tasks to pick. To pick tasks 1 you should have finished task 0, and to pick task 2 you should have finished task 1 and to pick task 3 you should have finished task 2. So it is possible.

logic : reverse topological sort
 */
public class OrderOfCompilation {

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
        ArrayList<org.ms.ds.graph.TopologySort_DFS_16.Edge>[] graph = new ArrayList[vtces];
        for (int i = 0; i < vtces; i++) {
            graph[i] = new ArrayList<>();
        }

        int edges = Integer.parseInt(br.readLine());
        for (int i = 0; i < edges; i++) {
            String[] parts = br.readLine().split(" ");
            int v1 = Integer.parseInt(parts[0]);
            int v2 = Integer.parseInt(parts[1]);
            graph[v1].add(new org.ms.ds.graph.TopologySort_DFS_16.Edge(v1, v2));
        }

        boolean[] visited = new boolean[vtces];
        Stack<Integer> st = new Stack<>();
        for (int v = 0; v < vtces; v++) {
            if (visited[v] == false) {
                topological(graph, v, visited, st);
            }
        }

        while (st.size() > 0) {
            System.out.println(st.pop());
        }
    }

    public static void topological(ArrayList<org.ms.ds.graph.TopologySort_DFS_16.Edge>[] graph, int src, boolean[] visited, Stack<Integer> st) {
        visited[src] = true;
        for (org.ms.ds.graph.TopologySort_DFS_16.Edge e : graph[src]) {
            if (!visited[e.nbr]) {
                topological(graph, e.nbr, visited, st);
            }
        }
        st.push(src);
    }
}

