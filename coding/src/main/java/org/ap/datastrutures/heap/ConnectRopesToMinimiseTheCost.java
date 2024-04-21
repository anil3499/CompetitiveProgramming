package org.ap.datastrutures.heap;

/*
https://medium.com/@vivek_ranjan/connect-n-ropes-with-minimum-cost-a865bcf12bdc

Eg: Given 4 ropes of length { 3, 7, 9, 4 }
We can connect the ropes in the following way:
1) First, connect the ropes of length 3 and 4. Cost of connecting these ropes is 3 + 4 = 7.
Now we have ropes of length { 7, 7, 9 }
2) Then, connect the ropes with length 7 and 7. Now the cost of connecting these ropes is 7 + 7 = 14.
Now we have ropes of length { 9, 14 }
3) Finally, connect the two last ropes with cost 9 + 14 = 23.
So, Total Cost of connecting these ropes in the above order is 7 + 14 + 23 = 44.

logic :
Always choose 2 minimum elements out of current rope we have after adding last one

 */

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class ConnectRopesToMinimiseTheCost {

    private static Integer connectRopesToMinimiseTheCost(List<Integer> a) {
        PriorityQueue<Integer> queue=new PriorityQueue<>(a);
        int minCost=0;
        while (queue.size() > 1){
         int item1=queue.poll();
         int item2=queue.poll();

         minCost=minCost+item1+item2;
         queue.add(item1+item2);
        }
        return minCost;

    }

    public static void main(String[] args) {
        List<Integer> A = Arrays.asList(4, 3, 2, 6, 5, 7, 12);
        System.out.println("ConnectRopesToMinimiseTheCost " +
                connectRopesToMinimiseTheCost(A));
    }



}
