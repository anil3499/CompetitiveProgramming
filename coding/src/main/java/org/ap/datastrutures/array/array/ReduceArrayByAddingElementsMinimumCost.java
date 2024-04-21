package org.ap.datastrutures.array.array;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
VISA
https://www.geeksforgeeks.org/minimize-cost-to-reduce-array-to-a-single-element-by-replacing-k-consecutive-elements-by-their-sum/
Example, let the array A = [1,2,3]

Then, we can remove 1 and 2, add both of them and keep the sum back in array.
Cost of this step would be (1+2) = 3.
So A = [3,3], Cost = 3
In second step, we can remove both elements from the array and keep the sum back in array again.
Cost of this step would be 3 + 3 = 6.
So, A = [6], Cost = 6
So total cost turns out to be 9 (6+3).

 */
public class ReduceArrayByAddingElementsMinimumCost {

    public static void main(String[] args) {
        Integer[] arr=new Integer[]{1,2,3,4,6};
        int [] ans= sumAndCost(arr);
        System.out.println("sum : "+ ans[0] +" cost : " + ans[1]);
    }

    public static int[] sumAndCost(Integer[] arr) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Arrays.asList(arr));
        int cost = 0;
        while (priorityQueue.size() > 1) {
            int first=priorityQueue.poll();
            int second=priorityQueue.poll();
            cost=cost + first+second;
            priorityQueue.add(first + second);
        }
        int []sumAndCost=new int[2];
        sumAndCost[0]=priorityQueue.poll();
        sumAndCost[1]=cost;
        return sumAndCost;
    }
}
