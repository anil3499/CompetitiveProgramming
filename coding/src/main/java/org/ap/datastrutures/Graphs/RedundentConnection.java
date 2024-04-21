package org.ap.datastrutures.Graphs;
//Redudant Conenction Leetcode Disjoint Set Union by Rank
// algorithm disjoint set data structure
public class RedundentConnection {
    public static void main(String[] args) {
        int[][] graph = {{1,2},{2,3},{3,4},{1,4},{1,5}};
    }
    public static int [] findNodeWhichCreatingcycle(int[][]graph){
        //its given that we have 1-n nodes/vertex, and n edges
        int[] arr = new int[graph.length+1];
        //since nodes starting from 1
        for(int i=0; i<graph.length; i++)
            arr[i] =i; //1 is parent of 1, 2 is parent of 2

        for(int[] edge: graph) {
            // {{1,2},{2,3},{3,4},{1,4},{1,5}};
            int ele1 = edge[0]; //1
            int ele2 = edge[1]; //2

            //first check they are already conencted or not
            int p1 = findParent(ele1, arr);
            int p2 = findParent(ele2, arr);
            if (p1 == p2)
                return edge; // if parent same then we can return current edge whcih causing cycle
            else {
                //we can do ds[p1]=p2 s well
                arr[p2] = p1;
            }
        }
            return new int[]{};
    }
        public static int findParent(int element, int[] arr){
        if(arr[element] == element)
            return element;
        return findParent(arr[element],arr);
        }

    public static int [] findNodeWhichCreatingcycleOptimized(int[][]graph){
        //its given that we have 1-n nodes/vertex, and n edges
        int[] arr = new int[graph.length+1];
        int[] rank = new int[graph.length+1];
        //since nodes starting from 1
        for(int i=0; i<graph.length; i++) {
            arr[i] = i; //1 is parent of 1, 2 is parent of 2
            rank[i] = 1;
        }
        for(int[] edge: graph) {
            // {{1,2},{2,3},{3,4},{1,4},{1,5}};
            int ele1 = edge[0]; //1
            int ele2 = edge[1]; //2

            //first check they are already conencted or not
            int p1 = findParent(ele1, arr);
            int p2 = findParent(ele2, arr);
            if (p1 == p2)
                return edge; // if parent same then we can return current edge whcih causing cycle
            else {
                if(rank[p1] < rank[p2]) {
                    arr[p1] = p2;
                }else if(rank[p1] > rank[p2]) {
                    arr[p2] = p1;
                }
                else{
                    //can chooose anyone here
                    arr[p1] = p2;
                    rank[p2]++;
                }
            }
        }
        return new int[]{};
    }
}
