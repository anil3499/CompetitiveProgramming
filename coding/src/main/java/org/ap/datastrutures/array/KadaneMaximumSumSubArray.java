package org.ap.datastrutures.array;

public class KadaneMaximumSumSubArray {
    //Kedane Algorithms have simple 3 steps

    /**
     *  We will have 2 variable
     *     i) currSum
     *     ii) maxSum
     *   Just remember 3 step
     *    always sum = sum  + arr[i]
     *    update maxSum = Math.max(maxSum,sum)
     *    if(sum<0) //due to negetives in array
     *        then  sum=0
     */
    public int findMaxSumSubArray(int[]  arr) {
        int currSum =0, maxSum=0;
        for(int i=0; i< arr.length; i++) {
            currSum+=arr[i];
            maxSum = Math.max(maxSum,currSum);

            //to handle negetive sum, whever sum become negetive that means we cant consider that sub array so we will maek sum 0
            if(currSum < 0)
                currSum = 0;
        }

        return maxSum;
    }
}
