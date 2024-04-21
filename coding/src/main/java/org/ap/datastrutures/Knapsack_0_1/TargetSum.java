package org.ap.datastrutures.Knapsack_0_1;

// Initialization
//
//recursion
//    Choice Diagram
//                       Wt[n-1]   n-1 because arr size is n
//                  /               \
//            wt[n-1] <= W          wt[n-1] > W
//              /       \               |
//             Yes      No              No
//
// if sum of a arr is odd then we cannot create 2 equal partitions, its always correct
//minimum diffrence of 2 partition's sum should be minimum
public class TargetSum {
    public static int dp[][] ;
    public int subsetsum(int arr[], int W,int n) {
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < W + 1; j++) {
                if (arr[i - 1] <= j) {
                    //dp[i][j] = max(dp[i-1][j-arr[n-1]], dp[i-1][j]);
                    dp[i][j] = dp[i-1][j-arr[n-1]] +  dp[i-1][j];
                } else {
                    dp[i][j] =  dp[i-1][j];
                }
            }
        }
        return dp[n][W];
    }
    public static void main(String[] args) {
        int arr[] = {2,3,7,8,10};
        int W = 1;  //s1=s2
        int n=5;  //len of arr
        int sum = 0;
        for ( int i =0; i<n; i++){
            sum = sum + arr[i];
        }

        //s1-s2 = 1  -- diff
        //s1+s2 = sum
        //2s1 = sum+diff
        //s1 = (sum+diff)/2
        int s1 = (sum+W)/2;
        W=s1;



        dp = new int[n+1][W+1];
        //initalize
        for (int i =0; i<n+1; i++){
            for (int j=0; j<W+1; j++){
                if (i==0){
                    dp[i][j] = 0;  //0 as False
                }
                if (j==0){
                    dp[i][j] = 1;  //1 as True
                }
            }
        }

        int result = new TargetSum().subsetsum(arr,sum,n); //just to get the vector range
        //here in this case we will passing complete sum so we can get all combnations

        System.out.println("result:  "+result);
    }
}

