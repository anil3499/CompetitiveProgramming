package org.ap.leetcode.Dec2023_1_to_70;

import java.util.Arrays;
//https://leetcode.com/problems/container-with-most-water/
public class ContainerWithMostWater {
    public int maxArea(int[] height) {

        int n = height.length;
        if(n<2) return 0;
        if(n==2) return Math.min(height[0],height[1]);
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = height[0];
        for(int i=1;i<n;i++)
            left[i] =Math.max(height[i-1],height[i]);
        right[n-1] = height[n-1];
        for(int j= n-2;j>=0;j--)
            right[j] =Math.max(height[j+1],height[j]);

        int i = 0;
        int j = n-1;
        int water = 0;
        System.out.println(Arrays.toString(left));
        System.out.println(Arrays.toString(right));
        while(i<j) {
            //System.out.println(left[i] + " " +right[j]);
            int min = Math.min(left[i],right[j]);
            water = Math.max(water, (j-i)*min);
            //System.out.println(water +" "+ min +" "+j + "  " + i);

            if(left[i]<=right[j]) i++;
            else j--;
        }
        return water;
    }
}
