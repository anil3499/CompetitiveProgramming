package org.ap.datastrutures.slidingwindow.variablesizewindow;

import java.util.HashMap;
import java.util.List;

//geeneral format
//while(j<size){
//         calculations
//          if(condition_from_prob < k)
//                  j++
//          elseif (winsize ==k)
//          {
//              ans  <<---- calculatios
//              j++
//            }
//          else if (condition >k){
//              while(condition >k)
//              {
//                  remove elements from sum
//                  i--
//             }
//              search for possible candidate here by max/min
//              j++
//
//          }
// }
public class LargestSubArrayOfSumK {
    public static int findLargetSubarrayOfSumK(int[] arr, int k) {

        //instead of window size condition is given here
        int sum = 0;
        int max_window_of_sie_K = 0;
        for (int i = 0, j = 0; j < arr.length; ) {
            sum = sum + arr[j];
            int curr_window_size = j - i + 1;

            //if sum is still less than k then bring the sum till k by increasing window
            if (sum < k) {
                j++;
            } else if (sum == k) {
                //if sum became k then update our max window of sie variable if its not max and increase window
                max_window_of_sie_K = Math.max(max_window_of_sie_K, curr_window_size);
                j++;
            } else { //sum is greater than k
                while (i < arr.length && sum > k) {
                    sum = sum - arr[i];
                    i++;
                }
            }
        }
        return max_window_of_sie_K;
    }

    public static void main(String[] args) {
        //int[] arr = new int[]{-3,4,1,3,-6,1,2,2};
        int sum = 5;
        //System.out.println(findLargetSubarrayOfSumK_forNegetives(arr, sum));
    }

   /* public static int findLargetSubarrayOfSumK_forNegetives(int[] arr, int k) { //k is target sum
        int prefixSum = 0;
        int max_window = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        // traverse the given array
        for (int i = 0,j=0; i < arr.length; i++) {
            //while (j < arr.length) {
                prefixSum += arr[j];
                if (prefixSum < k) {
                    j++;
                }
                else if (prefixSum == k) {
                    max_window = Math.max(max_window, j-i+1);
                    j++;
                }
                else if (prefixSum > k) {
                    while (prefixSum > k) {
                        prefixSum -= arr[i];
                        i++;
                    }
                    if(prefixSum == k){
                        max_window = Math.max(max_window, j-i+1);
                    }
                    j++;
                }
        }
    return max_window;
    }*/
}
