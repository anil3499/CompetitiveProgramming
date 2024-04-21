package org.ap.datastrutures.heap;

import java.util.PriorityQueue;

public class MinHeap {
    public static void main(String args[]) {
        // create priority queue
        //default order (x, y) -> x < y ? -1 : x == y ? 0 : 1
        PriorityQueue< Integer > prq = new PriorityQueue <> ();

        // insert values in the queue
        prq.add(6);
        prq.add(9);
        prq.add(5);
        prq.add(64);
        prq.add(6);

        //print values
        while (!prq.isEmpty()) {
            System.out.print(prq.poll()+" ");
        }
    }

}
