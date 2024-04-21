package org.ap.datastrutures.dp.knapsack;

/*
https://www.geeksforgeeks.org/count-of-subsets-with-sum-equal-to-x/

Input: arr[] = {1, 2, 3, 3}, X = 6
Output: 3
All the possible subsets are {1, 2, 3},
{1, 2, 3} and {3, 3}

Input: arr[] = {1, 1, 1, 1}, X = 1
Output: 4
 */

public class CountOfSubsetSum_4 {

    int coss(int number[], int sum) {
        int t[][]=new int[number.length+1][sum+1];

        for(int i=0;i<number.length+1;i++){
            for(int j=0;j<sum+1;j++){
                if(i==0 && j==0){
                    t[i][j]=1;
                }
                else if(i==0 ){
                    t[i][j]=0;
                }else if(j==0 ){
                    t[i][j]=1;
                }
            }
        }
        for(int i=1;i<number.length+1;i++){
            for(int j=1;j<sum+1;j++){
                //if item weight is lower than capacity
                if(number[i-1] <= j){
                    //first section shows item selected and second shows not selected
                    t[i][j] =   t[i-1][j-number[i-1]] + t[i-1][j];
                }else{
                    //if item weight is greater than capacity
                    t[i][j] =t[i-1][j];
                }
            }
        }
        return t[number.length][sum];

    }

    private int countSubsetsRecursive(int[] num, int sum, int currentIndex) {
        // base checks
        if (sum == 0)
            return 1;

        if(num.length == 0 || currentIndex >= num.length)
            return 0;

        // recursive call after selecting the number at the currentIndex
        // if the number at currentIndex exceeds the sum, we shouldn't process this
        int sum1 = 0;
        if( num[currentIndex] <= sum )
            sum1 = countSubsetsRecursive(num, sum - num[currentIndex], currentIndex + 1);

        // recursive call after excluding the number at the currentIndex
        int sum2 = countSubsetsRecursive(num, sum, currentIndex + 1);

        return sum1 + sum2;
    }

    public static void main(String[] args) {
        CountOfSubsetSum_4 countOfSubsetSum=new CountOfSubsetSum_4();
        int []number= new int[]{1, 2, 3, 3};
        int sum=6;
        System.out.println("max value within capacity is "+ countOfSubsetSum.coss(number,sum));
    }

}
