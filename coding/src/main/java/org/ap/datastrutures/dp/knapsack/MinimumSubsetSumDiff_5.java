package org.ap.datastrutures.dp.knapsack;

/*
https://www.geeksforgeeks.org/partition-a-set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum/
Given a set of integers, the task is to divide it into two sets S1 and S2 such that the absolute difference between their sums is minimum.

Input:  arr[] = {1, 6, 11, 5}
Output: 1
Explanation:
Subset1 = {1, 5, 6}, sum of Subset1 = 12
Subset2 = {11}, sum of Subset2 = 11

if we divide the set in 2 subset then diff will definitely be b/w 0 to sum of set
min=range - 2s



 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MinimumSubsetSumDiff_5 {

    int mssd(int number[]) {
      int INT_MIN=Integer.MAX_VALUE;
      SubsetSumProblem_2 subsetSumProblem=new SubsetSumProblem_2();
      int sum = Arrays.stream(number).sum();
      List<Integer> validSum=new ArrayList<>();
      for(int i=0;i<=sum;i++){
        if(subsetSumProblem.sss(number,i)){
            validSum.add(i);
        }
      }
      validSum= validSum.stream().limit(validSum.size()/2).collect(Collectors.toList());
      for(Integer i:validSum){
          INT_MIN=Integer.min(INT_MIN,sum-2*i);
      }
      return INT_MIN;
    }

    //using recursion
    public int canPartition(int[] num) {
        return this.canPartitionRecursive(num, 0, 0, 0);
    }
    //using recursion
    private int canPartitionRecursive(int[] num, int currentIndex, int sum1, int sum2) {
        // base check
        if (currentIndex == num.length)
            return Math.abs(sum1 - sum2);

        // recursive call after including the number at the currentIndex in the first set
        int diff1 = canPartitionRecursive(num, currentIndex+1, sum1+num[currentIndex], sum2);

        // recursive call after including the number at the currentIndex in the second set
        int diff2 = canPartitionRecursive(num, currentIndex+1, sum1, sum2+num[currentIndex]);

        return Math.min(diff1, diff2);
    }

    public static void main(String[] args) {
        MinimumSubsetSumDiff_5 minimumSubsetSumDiff = new MinimumSubsetSumDiff_5();
        int[] number = new int[]{1, 6, 11, 5};
        System.out.println("max value within capacity is " + minimumSubsetSumDiff.mssd(number));
    }

}
