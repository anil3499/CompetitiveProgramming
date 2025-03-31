package org.ap.datastrutures.stack;
//https://leetcode.com/problems/trapping-rain-water/
///https://www.youtube.com/watch?v=FbGG2qpNp4U&t=1511s
/**
 * Input: arr[]   = {3, 0, 2, 0, 4}
 * Output: 7
 * Input: arr[] = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]
 * Output: 6
 * for each element
 * 1. find max in left subarray and right subarray
 * 2. then apply this formula
 *    water[i] = min(MaxL,MaxR) - array[i]
 * 3. sum water[i]
 *
*/
public class TrappingRainWater {
    public int trap(int[] height) {
        int n=height.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = 0;

        for(int i=1 ;i<n;i++){
            left[i] = Math.max(left[i-1],height[i-1]);
        }

        right[n-1] = 0;
        for(int i=n-2;i>=0; i--){
            right[i] = Math.max(right[i+1],height[i+1]);
        }

        printArray(left);
        printArray(right);
        printArray(height);
        int water = 0;
        for(int i=0; i<n; i++){
            int minele = Math.min(left[i],right[i]);
            int ele = minele - height[i];
            if(ele>0)
                water+=ele;
        }
        return water;
    }
    public void printArray(int[] arr){
        System.out.print("[");
        for(int i=0;i<arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println("]");
    }
}
