package org.ap.datastrutures.metrix_chain_multiplication;
/**
 * Given a string s, partition s such that every substring of the partition is a palindrome
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * Example 1:
 *
 * Input: s = "aab"
 * Output: 1
 * Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
 * Example 2:
 *
 * Input: s = "a"
 * Output: 0
 * */
public class PalindromePartitioning_II {
    public boolean isPalindrome(String str, int left,int right){
        if (left >= right)
            return true;
        while(left < right){
            if(str.toCharArray()[left] != str.toCharArray()[right])
              return false;
            left++;
            right--;
        }
        return true;
    }
    public int mcm(String arr,int i,int j){
        if (i>j || i==j){ // i==1 also not allowed beause array with 1 len also not useful for this MCM
            return 0;
        }
        if(isPalindrome(arr,i,j))
            return 0;

        int min = Integer.MAX_VALUE;
        for(int  k=i; k<=j-1; k++) {
            int tempans = 1+ mcm(arr, i, k) + mcm(arr, k + 1, j); //+1 because we have done one partition by dividing i...j to i..k and k+1...j
            min = Math.min(min,tempans);
        }
        return min;

    }
    public int mcm_memoize(String arr,int i,int j){
        if (i>j || i==j){ // if string is empty or size 1 then also we can create 0 partition only so return will be 0
            return 0;
        }
        if(isPalindrome(arr,i,j)){ //if string is already a palindrome then return 0
            return 0;
        }
        if (dp[i][j]==-1) {
            int min = Integer.MAX_VALUE;
            for (int k = i; k <= j - 1; k++) {
                int tempans = 1 + mcm_memoize(arr, i, k) + mcm_memoize(arr, k + 1, j);
                min = Math.min(min,tempans);

            }
            dp[i][j] = min;
        }
        return dp[i][j];

    }
    public int mcm_memoize_more_optimized(String arr,int i,int j){
        if (i>j || i==j){ // if string is empty or size 1 then also we can create 0 partition only so return will be 0
            return 0;
        }
        if(isPalindrome(arr,i,j)==true){ //if string is already a palindrome then return 0
            return 0;
        }
        if (dp[i][j]==-1) {

            int min = Integer.MAX_VALUE;
            for (int k = i; k <= j - 1; k++) {
                int left;
                if (dp[i][k]!=-1)
                        left = dp[i][k];
                else {
                    left  = mcm_memoize(arr, i, k);
                    dp[i][k] = left;
                }
                int right;
                if (dp[k+1][j]!=-1)
                    right = dp[k+1][j];
                else {
                    right  = mcm_memoize(arr, k+1, j);
                    dp[k+1][j] = right;
                }
                int tempans = 1 +  left + right; //converted functions calls into left aand right and checked in dp before calling
                if (min > tempans) {
                    min = tempans;
                }
            }
            dp[i][j] = min;
        }
        return dp[i][j];

    }
    static int[][] dp;
    public static void main(String[] args) {
        String arr = "nitin";
        //Crate min partition such as all resultant string can be palindrome
        //for ex we can do after n and after iti so with 2 partition 3 strings we will have all string palindrome
        //btw here ans will be zero
        int n = arr.length();
        dp = new int[n+1][n+1];
        //initialize
        for (int i =0; i<n; i++){
            for (int j =0 ; j<n; j++){
                dp[i][j] = -1;
            }
        }
        int res =new PalindromePartitioning_II().mcm_memoize(arr,0,n-1); //for this i and j value is changes i become 0
        System.out.println(res);

        //find i and j valie
        //basecondition
    }
}
/**
 * Palindrome Partitioning Leetcode -https://leetcode.com/problems/palindrome-partitioning-ii/
 * Youtube - https://www.youtube.com/watch?v=szKVpQtBHh8&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=36
 * */
