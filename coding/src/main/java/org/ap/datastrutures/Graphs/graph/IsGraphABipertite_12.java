package org.ap.datastrutures.Graphs.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

/*
A Bipartite Graph is a graph whose vertices can be divided into two independent sets,
U and V such that every edge (u, v) either connects a vertex from U to V or a vertex from V to U.
In other words, for every edge (u, v), either u belongs to U and v to V, or u belongs to V and v to U.
We can also say that there is no edge that connects vertices of same set.

In short : Al the edges should be across set means should not have a internal edge
if it is possible to divide the vertices into 2 mutually exclusive and exhaustive set such that all edge
are across set.

if graph is acyclic -> can be bipertite
if graph is cyclic with even size -> can be bipertite
if graph is cyclic with odd size -> can not be bipertite

Sample input :
3
3
0 1
1 2
2
output:
false

 */
public class IsGraphABipertite_12 {
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
        int level;

        public Pair(int v, String psf, int level) {
            this.v = v;
            this.psf = psf;
            this.level = level;
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
        int []visited = new int[vertex];
        Arrays.fill(visited,-1);
        for(int i=0 ; i<vertex ;i++){
            if(visited[i] == -1){
                boolean isBipertite = checkIfBipertite(graph , i,  visited);
                if(!isBipertite){
                    System.out.println(false);
                    return;
                }
            }
        }
        System.out.println(true);
    }

    public static boolean checkIfBipertite(ArrayList<Edge>[] graph, int src, int[] visited){
        ArrayDeque<Pair> deque=new ArrayDeque<>();
        deque.add(new Pair(src,src+"",0));
        while(!deque.isEmpty()){
            Pair rm=deque.removeFirst();
            if(visited[rm.v] != -1){
                if(visited[rm.v] != rm.level){
                    return false;
                }
            }else{
               visited[rm.v] = rm.level;
            }
            for(Edge edge1 : graph[rm.v]){
                if(visited[edge1.nbr] != -1){
                    deque.add(new Pair(edge1.nbr, rm.psf+edge1.nbr, rm.level+1));
                }
            }
        }
        return true;
    }
}
