package org.ap.datastrutures.slidingwindow.fixedwindowsize;

import static java.lang.Math.max;

//geeneral format
//while(j<size){
//         calculations
//          if(winsize < k)
//                  j++
//          elseif (winsize ==k)
//          {
//              ans  <<---- calculatios
//              calculation remove i
//              window size maintained and slide
//          }
// }

public class MaxSumSubArrayOfSizeK {
    public int solve(int[] arr, int sizeK){
        //how to get window size j-i+1
        int mx = 0;
        int sum =0;
        for (int i =0,j=0; j<arr.length; ){

            sum = sum + arr[j];

            if (sizeK > j-i+1){
                j++;
            } else if(sizeK == j-i+1) {
                mx = max(mx,sum);
                sum = sum - arr[i];
                i++;
                j++;
            }
        }
        return mx;
    }
    public static void main(String[] args) {

    }
}
