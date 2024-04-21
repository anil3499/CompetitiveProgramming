package org.ap.datastrutures.dp.knapsack;

/*
AKA : target sum subset problem

https://www.geeksforgeeks.org/subset-sum-problem-dp-25/

Given a set of non-negative integers, and a value sum, determine
if there is a subset of the given set with sum equal to given sum.

Input: set[] = {3, 34, 4, 12, 5, 2}, sum = 9
Output: True
There is a subset (4, 5) with sum 9.

Input: set[] = {3, 34, 4, 12, 5, 2}, sum = 30
Output: False
There is no subset that add up to 30.


we will be creating a matrix of array(row) and sum(column)


logic :
cricket analogy
current will have 2 choices with condition run sud be greater than my capacity
1. if I contribute my run,  will team can do remaining run
2. or if I do not contribute. will check team run only
 */
public class SubsetSumProblem_2 {
    boolean sss(int number[], int sum) {
        boolean t[][]=new boolean[number.length+1][sum+1];

        for(int i=0;i<number.length+1;i++){
            for(int j=0;j<sum+1;j++){
                if(i==0 && j==0){
                    t[i][j]=true;
                }
                else if(i==0 ){
                    t[i][j]=false;
                }else if(j==0 ){
                    t[i][j]=true;
                }
            }
        }
        for(int i=1;i<number.length+1;i++){
            for(int j=1;j<sum+1;j++){
                //if item weight is lower than capacity
                if(number[i-1] <= j){
                    //first section shows item selected and second shows not selected
                    t[i][j] =   t[i-1][j-number[i-1]] || t[i-1][j];
                }else{
                    //if item weight is greater than capacity
                    t[i][j] = t[i-1][j];
                }
            }
        }
        return t[number.length][sum];

    }

    static boolean isSubsetSum(int set[],
                               int n, int sum)
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

    public static void main(String[] args) {
        SubsetSumProblem_2 subsetSumProblem=new SubsetSumProblem_2();
        int []number= new int[]{3, 34, 4, 12, 5, 2};
        int sum=9;
        System.out.println("max value within capacity is "+ subsetSumProblem.sss(number,sum));
    }

}
