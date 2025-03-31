package org.ap.datastrutures.slidingwindow.fixedsize;
//https://leetcode.com/problems/maximum-average-subarray-i/description
//643
public class MaximumAverageSubarrayOfSizeK {
    public static double findMaxAverage(int[] nums, int k) {
        if(nums.length == 1) return new Double(nums[0]);
        int i=0, j=0;
        double sum =0 ;
        double avg = Double.MIN_VALUE;
        int[] sums = new int[nums.length];
        while(j < nums.length) {
            sum += nums[j];

            int windowSize = j - i + 1;
            if(windowSize < k) {
                j++;
            } else {
                //ans  <<---- calculatios
                if(avg==Double.MIN_VALUE) {
                    avg = sum/k;
                } else {
                    avg = Math.max(avg, sum / k);
                }
                //calculation remove i
                sum = sum - nums[i];
                //window size maintained and slide
                i++;
                j++;
            }
            //System.out.println("windowSize "+windowSize+ " i "+i + " j "+j + " sum "+sum + " avg "+ avg);
        }
        return avg;
    }

    public static void main(String[] args) {
        int []arr={1, 4, 2, 10, 23, 3, 1, 0, 20};
        int k=4;
        findMaxAverage(arr,k);

    }

    
}
