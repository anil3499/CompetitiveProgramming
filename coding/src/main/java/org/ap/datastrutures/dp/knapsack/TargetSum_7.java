package org.ap.datastrutures.dp.knapsack;

/*


https://leetcode.com/problems/target-sum/solution/

You are given a list of non-negative integers, a1, a2, ..., an, and a target, S.
Now you have 2 symbols + and -.
For each integer, you should choose one from + and - as its new symbol.
Input: nums is [1, 1, 1, 1, 1], S is 3.
Output: 5
Explanation:

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.

 */
public class TargetSum_7 {

    int ts(int []number , int sum){
       CountNoOfSubsetWithGivenDiff_6 countNoOfSubsetWithGivenDiff=new CountNoOfSubsetWithGivenDiff_6();
       return countNoOfSubsetWithGivenDiff.cnosswgd(number,sum);
    }

    public static void main(String[] args) {
        TargetSum_7 targetSum=new TargetSum_7();
        int []number= new int[]{1, 1, 2, 3};
        int sum=1;
        System.out.println("max value within capacity is "+ targetSum.ts(number,sum));
    }
}
