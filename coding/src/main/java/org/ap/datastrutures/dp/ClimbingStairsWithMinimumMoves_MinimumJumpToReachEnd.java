package org.ap.datastrutures.dp;
/*
ClimbingStairsWithMinimumMoves_MinimumJumpToReachEnd.JPG

dynamic programming : n^2
https://www.geeksforgeeks.org/minimum-number-of-jumps-to-reach-end-of-a-given-array/

https://www.youtube.com/watch?v=Zobz9BXpwYE&list=PL-Jc9J83PIiG8fE6rj9F5a6uyQ5WPdqKy&index=5

step1: storage and meaning
step2: identify direction
step3: travel and solve

logic :
take Dp array of n+1
will start from last
nth to nth will take zero steps
keep decrease and check the right side element in range (range is current element value)
and min of right in range + 1
 */
public class ClimbingStairsWithMinimumMoves_MinimumJumpToReachEnd {

    public static void solveNoJumpDP(Integer []arr, int n){
        Integer []dp=new Integer[n+1];
        // Minimum number of jumps
        // needed to reach last
        // element from last elements
        // itself is always 0
        dp[n]=0;
        // Start from the second
        // element, move from right
        // to left and construct the
        // jumps[] array where jumps[i]
        // represents minimum number of
        // jumps needed to reach arr[m-1]
        // from arr[i]
        for(int i=n-1 ; i >=0 ;i--){
            if(arr[i] > 0){
                int min=Integer.MAX_VALUE;
                for(int j=1 ; j<= arr[i] && (i+j) < dp.length ; j++){
                    if(dp[i + j] !=null) {
                        min = Math.min(min, dp[i + j]);
                    }
                }
                if(min != Integer.MAX_VALUE){
                    dp[i]=min+1;
                }
            }
        }
        System.out.println(dp[0]);
    }

    public static void main(String[] args) {
        Integer []arr=new Integer[]{3,3,0,2,1,2,4,2,0,0};
        solveNoJumpDP(arr,arr.length);
    }
}
