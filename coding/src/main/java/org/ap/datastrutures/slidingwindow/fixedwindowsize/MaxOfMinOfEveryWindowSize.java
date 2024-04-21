package org.ap.datastrutures.slidingwindow.fixedwindowsize;/*
package ap.dynamicprogramming.slidingwindow.fixedwindowsize;

public class MaxOfMinOfEveryWindowSize {
    public int solve(int arr[], int sum){
        int i = 0;
        int j=0;
        int max = Integer.MIN_VALUE;
        int k = j-i+1; //window size;
        while (j<k){
            k = j-i+1;
            sum = sum + arr[j];
            if (sum < k)
                j++;
            else if (sum ==k){

                    max = max(max,j-i+1);
                    j++
            }else if (sum >K) {
                while(sum >K) {
                    sum = sum - arr[i];
                    i++;
                }
                j++
            }
        }
        return max;
    }
}
*/
