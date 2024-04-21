package org.ap.datastrutures.dp.knapsack;

import java.util.Arrays;

/*
https://www.geeksforgeeks.org/partition-problem-dp-18/

Partition problem is to determine whether a given set can be partitioned into two subsets
such that the sum of elements in both subsets is same.

arr[] = {1, 5, 11, 5}
Output: true
The array can be partitioned as {1, 5, 5} and {11}

arr[] = {1, 5, 3}
Output: false
The array cannot be partitioned into equal sum sets.

step 1:
s={x1...}
s={x2...}
s+s=2s
so if no. is not even means return false as it can never be a equal

step 2 :
sum all the element and divide that by 2

step 3: now use subset sum algo to find subarray withe given sum


 */
public class EqualSumPartition_3 {

    boolean esp(int number[]) {
        int totalSum=Arrays.stream(number).sum();
        int sum=totalSum/2;
        boolean t[][]=new boolean[number.length+1][sum+1];

        if(totalSum%2 != 0) {
            return false;
        }else {
            for (int i = 0; i < number.length + 1; i++) {
                for (int j = 0; j < sum + 1; j++) {
                    if (i == 0) {
                        t[i][j] = false;
                    } else if (j == 0) {
                        t[i][j] = true;
                    }
                }
            }
            for (int i = 1; i < number.length + 1; i++) {
                for (int j = 1; j < sum + 1; j++) {
                    //if item weight is lower than capacity
                    if (number[i - 1] <= j) {
                        //first section shows item selected and second shows not selected
                        t[i][j] = t[i - 1][j - number[i - 1]] || t[i - 1][j];
                    } else {
                        //if item weight is greater than capacity
                        t[i][j] = t[i - 1][j];
                    }
                }
            }
            return t[number.length][sum];
        }

    }

    //using recursion
    static boolean isSubsetSum(int set[], int n, int sum)
    {
        if (sum == 0)
            return true;
        if (n == 0)
            return false;

        // If last element is greater than
        // sum, then ignore it
        if (set[n - 1] > sum)
            return isSubsetSum(set, n - 1, sum);

        /* else, check if sum can be obtained
        by any of the following
            (a) including the last element
            (b) excluding the last element */
        return isSubsetSum(set, n - 1, sum)
                || isSubsetSum(set, n - 1, sum - set[n - 1]);
    }

    static boolean espr(int set[])
    {
      int sum = Arrays.stream(set).sum();
      if(sum %2 !=0){
          return false;
      }
      return isSubsetSum(set , set.length, sum/2);
    }

    public static void main(String[] args) {
        EqualSumPartition_3 equalSumPartition=new EqualSumPartition_3();
        int []number= new int[]{1, 5, 11, 5};
        System.out.println("max value within capacity is "+ equalSumPartition.esp(number));
        System.out.println("max value within capacity is (recursion)"+ equalSumPartition.espr(number));
    }

}
